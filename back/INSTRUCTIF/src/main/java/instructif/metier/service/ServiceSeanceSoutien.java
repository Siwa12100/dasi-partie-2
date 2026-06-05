package instructif.metier.service;

import instructif.dao.IntervenantDao;
import instructif.dao.JpaUtil;
import instructif.dao.SeanceSoutienDao;
import instructif.metier.modele.Eleve;
import instructif.metier.modele.Intervenant;
import instructif.metier.modele.Niveau;
import instructif.metier.modele.SeanceSoutien;
import instructif.metier.modele.Statut;
import instructif.metier.modele.Theme;
import instructif.util.Message;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class ServiceSeanceSoutien {
    private static final String MAIL_EXPEDITEUR = "instructif@insa-lyon.fr";

    public ServiceSeanceSoutien() {
    }

    public SeanceSoutien recupererSeanceSoutien(Long idSeanceSoutien) {
        SeanceSoutienDao seanceSoutienDao = new SeanceSoutienDao();
        SeanceSoutien seance = null;
        try {
            JpaUtil.creerContextePersistance();
            seance = seanceSoutienDao.trouverParId(idSeanceSoutien);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return seance;
    }

    public SeanceSoutien recupererSoutienAttribue(Long idIntervenant) {
        SeanceSoutienDao seanceSoutienDao = new SeanceSoutienDao();
        SeanceSoutien seance = null;
        try {
            JpaUtil.creerContextePersistance();
            seance = seanceSoutienDao.trouverDemandeAttribuee(idIntervenant);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return seance;
    }

    public SeanceSoutien demanderSoutien(Eleve eleve, Theme theme, String description) {
        SeanceSoutienDao seanceSoutienDao = new SeanceSoutienDao();
        IntervenantDao intervenantDao = new IntervenantDao();
        SeanceSoutien seance = null;
        try {
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();

            LocalDateTime maintenant = LocalDateTime.now();
            seance = new SeanceSoutien(maintenant, description, Statut.EnAttente, eleve, theme);
            seanceSoutienDao.creer(seance);

            List<Intervenant> disponibles = intervenantDao.trouverDisponiblesOrderByNbInterventions();
            int classeValeur = eleve.getClasse().getValeur();
            Intervenant choisi = disponibles.stream()
                .filter(i -> i.getNiveauMin().getValeur() >= classeValeur
                          && classeValeur >= i.getNiveauMax().getValeur())
                .findFirst()
                .orElse(null);

            if (choisi != null) {
                seance.setIntervenant(choisi);
                // La séance reste EnAttente : l'intervenant doit l'accepter avant qu'elle démarre.
                String login = choisi.getPrenom().substring(0, 1).toLowerCase() + choisi.getNom().toLowerCase();
                String lienVisio = "https://servif.insa-lyon.fr/InteractIF/visio.html"
                    + "?eleve=" + eleve.getMail()
                    + "&intervenant=" + login;
                seance.setLienVisio(lienVisio);
                String heure = maintenant.format(DateTimeFormatter.ofPattern("HH:mm"));
                JpaUtil.validerTransaction();
                Message.envoyerNotification(
                    choisi.getTelephone(),
                    "Bonjour " + choisi.getPrenom() + ". Merci de prendre en charge la demande de soutien en \""
                    + theme.getMatiere().getNom() + "\" demandée à " + heure
                    + " par " + eleve.getPrenom() + " en classe de " + classeToString(eleve.getClasse()) + "."
                    + "\nLien visio : " + lienVisio
                );
            } else {
                seance.setStatut(Statut.Rejetee);
                JpaUtil.validerTransaction();
                Message.envoyerMail(
                        MAIL_EXPEDITEUR,
                        eleve.getMail(),
                        "Demande rejetée",
                        "Demande rejetée car pas d'intervenant dispo."
                );
            }

        } catch (Exception ex) {
            JpaUtil.annulerTransaction();
            seance = null;
            ex.printStackTrace();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return seance;
    }


        public List<SeanceSoutien> obtenirHistoriqueEleve(Eleve eleve) {
        SeanceSoutienDao seanceSoutienDao = new SeanceSoutienDao();
        List<SeanceSoutien> historique = null;
        try {
            JpaUtil.creerContextePersistance();
            historique = seanceSoutienDao.trouverParEleve(eleve);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return historique;
    }

    public List<SeanceSoutien> obtenirHistoriqueIntervenant(Intervenant intervenant) {
        SeanceSoutienDao seanceSoutienDao = new SeanceSoutienDao();
        List<SeanceSoutien> soutiens = null;
        try {
            JpaUtil.creerContextePersistance();
            soutiens = seanceSoutienDao.trouverParIntervenant(intervenant);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return soutiens;
    }

    public boolean accepterSoutien(Long idSeanceSoutien) {
        SeanceSoutienDao seanceSoutienDao = new SeanceSoutienDao();
        try {
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();
            SeanceSoutien seance = seanceSoutienDao.trouverParId(idSeanceSoutien);
            if (seance == null || seance.getStatut() != Statut.EnAttente) {
                JpaUtil.annulerTransaction();
                return false;
            }
            seance.setStatut(Statut.EnCours);
            JpaUtil.validerTransaction();
            return true;
        } catch (Exception ex) {
            JpaUtil.annulerTransaction();
            ex.printStackTrace();
            return false;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
    }

    public boolean refuserSoutien(Long idSeanceSoutien) {
        SeanceSoutienDao seanceSoutienDao = new SeanceSoutienDao();
        try {
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();
            SeanceSoutien seance = seanceSoutienDao.trouverParId(idSeanceSoutien);
            if (seance == null || seance.getStatut() != Statut.EnAttente) {
                JpaUtil.annulerTransaction();
                return false;
            }
            seance.setStatut(Statut.Rejetee);
            seance.setIntervenant(null);
            seance.setLienVisio(null);
            JpaUtil.validerTransaction();
            Message.envoyerMail(
                    MAIL_EXPEDITEUR,
                    seance.getEleve().getMail(),
                    "Demande rejetée",
                    "Demande rejetée car l'intervenant attribué n'est pas disponible."
            );
            return true;
        } catch (Exception ex) {
            JpaUtil.annulerTransaction();
            ex.printStackTrace();
            return false;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
    }

    public boolean terminerSoutien(Long idSeanceSoutien) {
        SeanceSoutienDao seanceSoutienDao = new SeanceSoutienDao();
        try {
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();
            SeanceSoutien seance = seanceSoutienDao.trouverParId(idSeanceSoutien);
            if (seance == null || seance.getStatut() != Statut.EnCours) {
                JpaUtil.annulerTransaction();
                return false;
            }
            seance.setStatut(Statut.Terminee);
            int duree = (int) ChronoUnit.MINUTES.between(seance.getDateHeure(), LocalDateTime.now());
            seance.setDuree(duree);
            JpaUtil.validerTransaction();
            return true;
        } catch (Exception ex) {
            JpaUtil.annulerTransaction();
            ex.printStackTrace();
            return false;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
    }

    public boolean soumettreBilan(Long idSeanceSoutien, String compteRendu) {
        SeanceSoutienDao seanceSoutienDao = new SeanceSoutienDao();
        try {
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();
            SeanceSoutien seance = seanceSoutienDao.trouverParId(idSeanceSoutien);
            if (seance == null || seance.getStatut() != Statut.Terminee) {
                JpaUtil.annulerTransaction();
                return false;
            }
            seance.setCompteRendu(compteRendu);
            JpaUtil.validerTransaction();
            Message.envoyerMail(
                MAIL_EXPEDITEUR,
                seance.getEleve().getMail(),
                "Bilan de ta séance de soutien",
                "Bonjour " + seance.getEleve().getPrenom() + ",\n\n" + compteRendu
            );
            return true;
        } catch (Exception ex) {
            JpaUtil.annulerTransaction();
            ex.printStackTrace();
            return false;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
    }

    private String classeToString(Niveau niveau) {
        switch (niveau) {
            case SIXIEME:   return "6ème";
            case CINQUIEME: return "5ème";
            case QUATRIEME: return "4ème";
            case TROISIEME: return "3ème";
            default:        return niveau.name();
        }
    }
}
