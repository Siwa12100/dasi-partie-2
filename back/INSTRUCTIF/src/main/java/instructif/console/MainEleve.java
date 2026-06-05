package instructif.console;

import instructif.dao.JpaUtil;
import instructif.metier.modele.*;
import instructif.metier.service.ServiceEleve;
import instructif.metier.service.ServiceMatiere;
import instructif.metier.service.ServiceSeanceSoutien;

import java.util.Date;

public class MainEleve {
    private static final String CODE_COLLEGE_VILLEURBANNE = "0691664J";

    public static Eleve inscriptionEleve(String nom, String prenom, String mail, String mdp,
                                         String codeEtablissement, Niveau classe) {
        ServiceEleve serviceEleve = new ServiceEleve();

        System.out.println("\n[" + prenom + "] Inscription sur le réseau INSTRUCT'IF...");
        Eleve eleve = new Eleve(nom, prenom, new Date(), mail, mdp, null, classe);
        boolean inscrit = serviceEleve.inscrireEleve(eleve, codeEtablissement);
        System.out.println("Inscription de " + prenom + " : " + (inscrit ? "réussie" : "non réalisée"));
        return inscrit ? eleve : null;
    }

    public static Eleve connexionEleve(String mail, String mdp) {
        ServiceEleve serviceEleve = new ServiceEleve();

        System.out.println("\n[" + mail + "] Connexion...");
        Eleve eleve = serviceEleve.authentifierEleve(mail, mdp);
        System.out.println("Connexion : " + (eleve != null ? "OK" : "ECHEC"));
        return eleve;
    }

    public static void consulterMatieres() {
        ServiceMatiere serviceMatiere = new ServiceMatiere();

        System.out.println("\nMatières disponibles :");
        for (Matiere matiere : serviceMatiere.listerMatieres()) {
            System.out.println("    - " + matiere.getNom());
        }
    }

    public static Theme choisirTheme(String nomMatiere, String nomTheme) {
        ServiceMatiere serviceMatiere = new ServiceMatiere();

        for (Matiere matiere : serviceMatiere.listerMatieres()) {
            if (matiere.getNom().equals(nomMatiere)) {
                for (Theme theme : serviceMatiere.recupererThemesParMatiere(matiere.getId())) {
                    if (theme.getNom().equals(nomTheme)) {
                        System.out.println("\nThème choisi : " + nomMatiere + " / " + nomTheme);
                        return theme;
                    }
                }
            }
        }
        System.out.println("\nThème introuvable : " + nomMatiere + " / " + nomTheme);
        return null;
    }

    public static SeanceSoutien demanderSoutien(Eleve eleve, Theme theme, String description) {
        ServiceSeanceSoutien serviceSeance = new ServiceSeanceSoutien();

        System.out.println("\n[" + eleve.getPrenom() + "] Envoi de la demande de soutien...");
        SeanceSoutien seance = serviceSeance.demanderSoutien(eleve, theme, description);
        if (seance.getStatut() != Statut.Rejetee) {
            System.out.println("Demande attribuée à " + seance.getIntervenant().getPrenom()
                    + " " + seance.getIntervenant().getNom());
            System.out.println("Lien visio : " + seance.getLienVisio());
        }
        return seance;
    }

    public static void consulterHistorique(Eleve eleve) {
        ServiceSeanceSoutien serviceSeance = new ServiceSeanceSoutien();

        System.out.println("\n[" + eleve.getPrenom() + "] Historique des demandes :");
        for (SeanceSoutien s : serviceSeance.obtenirHistoriqueEleve(eleve)) {
            System.out.println("    - " + s.getTheme().getNom() + " : " + s.getStatut()
                + " (" + (s.getDuree() != null ? s.getDuree() + " min" : "—") + ")");
        }
    }

    public static SeanceSoutien consulterSeance(Long idSeance) {
        ServiceSeanceSoutien serviceSeance = new ServiceSeanceSoutien();

        SeanceSoutien seance = serviceSeance.recupererSeanceSoutien(idSeance);
        if (seance != null) {
            System.out.println("\nSéance #" + idSeance + " : statut=" + seance.getStatut()
                + ", durée=" + (seance.getDuree() != null ? seance.getDuree() + " min" : "—"));
            if (seance.getCompteRendu() != null) {
                System.out.println("Compte-rendu : " + seance.getCompteRendu());
            }
        }
        return seance;
    }
}
