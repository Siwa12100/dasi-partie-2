package instructif.console;

import instructif.dao.JpaUtil;
import instructif.metier.modele.College;
import instructif.metier.modele.Eleve;
import instructif.metier.modele.Intervenant;
import instructif.metier.modele.Niveau;
import instructif.metier.modele.SeanceSoutien;
import instructif.metier.modele.StatistiquesIntervenant;
import instructif.metier.modele.Theme;
import instructif.metier.service.ServiceIntervenant;
import instructif.metier.service.ServiceSeanceSoutien;
import instructif.metier.service.ServiceStatistiques;

import java.util.List;

public class MainIntervenant {
    public static Intervenant connexionIntervenant(String mail, String mdp) {
        ServiceIntervenant serviceIntervenant = new ServiceIntervenant();

        System.out.println("\n[" + mail + "] Connexion...");
        Intervenant intervenant = serviceIntervenant.authentifierIntervenant(mail, mdp);
        System.out.println("Connexion : " + (intervenant != null ? "OK" : "ECHEC"));
        return intervenant;
    }

    public static List<SeanceSoutien> consulterInterventions(Intervenant intervenant) {
        ServiceSeanceSoutien serviceSeance = new ServiceSeanceSoutien();

        System.out.println("\n[" + intervenant.getPrenom() + "] Ses interventions :");
        List<SeanceSoutien> interventions = serviceSeance.obtenirHistoriqueIntervenant(intervenant);
        for (SeanceSoutien s : interventions) {
            System.out.println("    - #" + s.getId() + " " + s.getTheme().getMatiere().getNom()
                + " / " + s.getTheme().getNom() + " (" + s.getStatut() + ")");
        }
        return interventions;
    }

    public static SeanceSoutien consulterInterventionAttribuee(Intervenant intervenant) {
        ServiceSeanceSoutien serviceSeance = new ServiceSeanceSoutien();

        return serviceSeance.recupererSoutienAttribue(intervenant.getId());
    }

    public static void consulterProfilEleve(SeanceSoutien seance) {
        Eleve eleve = seance.getEleve();
        System.out.println("\nProfil élève : " + eleve.getPrenom() + " " + eleve.getNom()
            + ", classe de " + eleve.getClasse()
            + (eleve.getCollege() != null ? ", " + eleve.getCollege().getNom() : ""));
    }

    public static void accepterSoutien(Long idSeance) {
        ServiceSeanceSoutien serviceSeance = new ServiceSeanceSoutien();
        boolean ok = serviceSeance.accepterSoutien(idSeance);
        System.out.println("Acceptation de la séance #" + idSeance + " : " + (ok ? "OK" : "ECHEC"));
    }

    public static void refuserSoutien(Long idSeance) {
        ServiceSeanceSoutien serviceSeance = new ServiceSeanceSoutien();
        boolean ok = serviceSeance.refuserSoutien(idSeance);
        System.out.println("Refus de la séance #" + idSeance + " : " + (ok ? "OK" : "ECHEC"));
    }

    public static void terminerSoutien(Long idSeance) {
        ServiceSeanceSoutien serviceSeance = new ServiceSeanceSoutien();
        boolean ok = serviceSeance.terminerSoutien(idSeance);
        System.out.println("Fin de la séance #" + idSeance + " : " + (ok ? "OK" : "ECHEC"));
    }

    public static void soumettreBilan(Long idSeance, String compteRendu) {
        ServiceSeanceSoutien serviceSeance = new ServiceSeanceSoutien();
        boolean ok = serviceSeance.soumettreBilan(idSeance, compteRendu);
        System.out.println("Bilan de la séance #" + idSeance + " : " + (ok ? "envoyé" : "ECHEC"));
    }

    public static StatistiquesIntervenant consulterStatistiques(Intervenant intervenant) {
        ServiceStatistiques serviceStats = new ServiceStatistiques();

        StatistiquesIntervenant stats = serviceStats.obtenirStatistiquesIntervenant(intervenant);
        System.out.println("\n[" + intervenant.getPrenom() + "] Tableau de bord :");
        System.out.println("    Entretiens réalisés : " + stats.getEntretiensNb());
        System.out.println("    Durée moyenne : " + stats.getMoyenneDuree() + " min");
        System.out.println("    Établissements :");
        for (College college : stats.getColleges()) {
            System.out.println("        - " + college.getNom() + " (" + college.getCommune() + ")");
        }
        return stats;
    }
}
