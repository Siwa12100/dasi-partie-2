package instructif.console;

import instructif.dao.JpaUtil;
import instructif.metier.modele.Intervenant;
import instructif.metier.modele.Matiere;
import instructif.metier.modele.Theme;
import instructif.metier.service.ServiceInitialisation;
import instructif.metier.service.ServiceIntervenant;
import instructif.metier.service.ServiceMatiere;

public class MainInit {
    public static void init() {
        System.out.println("===== INITIALISATION =====");
        ServiceInitialisation serviceInit = new ServiceInitialisation();
        boolean ok = serviceInit.initialiser();
        System.out.println("Initialisation : " + (ok ? "OK" : "ECHEC"));

        ServiceMatiere serviceMatiere = new ServiceMatiere();
        System.out.println("\nMatières et thèmes créés :");
        for (Matiere matiere : serviceMatiere.listerMatieres()) {
            System.out.println("- " + matiere.getNom());
            for (Theme theme : serviceMatiere.recupererThemesParMatiere(matiere.getId())) {
                System.out.println("    * " + theme.getNom());
            }
        }

        ServiceIntervenant serviceIntervenant = new ServiceIntervenant();
        System.out.println("\nIntervenants créés :");
        for (Intervenant intervenant: serviceIntervenant.listerIntervenants()) {
            System.out.println("-" + intervenant.toString());
        }
    }
}
