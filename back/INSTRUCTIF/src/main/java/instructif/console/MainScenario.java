package instructif.console;

import instructif.dao.JpaUtil;
import instructif.metier.modele.*;

import java.util.List;

import static instructif.console.MainIntervenant.*;

public class MainScenario {
    public static void main(String[] args) {
        JpaUtil.creerFabriquePersistance();
        MainInit.init();

        System.out.println("\n\nVérification 1 : Un élève s'inscrit, se connecte, et demande un soutien :");
        MainEleve.inscriptionEleve("Pascal", "Alice", "alice.pascal@free.fr", "alice",
                "0691664J", Niveau.CINQUIEME);
        Eleve alice = MainEleve.connexionEleve("alice.pascal@free.fr", "alice");
        Theme moyenAge = MainEleve.choisirTheme("Histoire et Géographie", "Moyen-Age");
        MainEleve.demanderSoutien(alice, moyenAge, "Je suis bloquée sur l'ordre seigneurial.");

        System.out.println("\n\nVérification 2 : Un élève s'inscrit mais refusé car même mail");
        MainEleve.inscriptionEleve("Pascale", "Alice", "alice.pascal@free.fr", "alice",
                "0691664J", Niveau.CINQUIEME);

        System.out.println("\n\nVérification 3 : L'intervenant se connecte, consulte ses interventions et accepte");
        Intervenant camille = connexionIntervenant("cmartin@sorbonne.fr", "camille");
        List<SeanceSoutien> interventions = consulterInterventions(camille);
        if (!interventions.isEmpty()) {
            SeanceSoutien seance = consulterInterventionAttribuee(camille);
            consulterProfilEleve(seance);
            accepterSoutien(seance.getId());
        }
        consulterStatistiques(camille);

        System.out.println("\n\nVérification 3 : Un autre eleve s'inscrit, se connecte et fait une demande, un autre intervenant est solicité.'");
        MainEleve.inscriptionEleve("Moutarde", "John", "john@free.fr", "alice",
                "0691664J", Niveau.TROISIEME);
        Eleve john = MainEleve.connexionEleve("john@free.fr", "alice");
        MainEleve.demanderSoutien(john, moyenAge, "J'y arrive pas !!!!");

        System.out.println("\n\nVérification 4 : Un autre eleve s'inscrit, se connecte et fait une demande mais il n'y a plus d'intervenant.'");
        MainEleve.inscriptionEleve("Zoubir", "Jake", "jake@free.fr", "alice",
                "0691664J", Niveau.QUATRIEME);
        Eleve jake = MainEleve.connexionEleve("jake@free.fr", "alice");
        MainEleve.demanderSoutien(jake, moyenAge, "bjr");

        System.out.println("\n\nVérification 5 : L'intervenant Camille termine le soutien et écrit un petit bilan super gentil, puis elle regarde ses statistiques'");
        if (!interventions.isEmpty()) {
            SeanceSoutien seance = consulterInterventionAttribuee(camille);
            terminerSoutien(seance.getId());
            soumettreBilan(seance.getId(),
                    "Nous avons revu les notions clés. Félicitations pour ta progression !");
        }
        consulterStatistiques(camille);

        System.out.println("\n\nVérification 6 : L'intervenant Emile est super occupé et se voit obligé de refuser le soutien. L'élève devra refaire une demande.'");
        Intervenant hugo = connexionIntervenant("ehugo@college.fr", "emile");
        List<SeanceSoutien> interventionsHugo = consulterInterventions(hugo);
        if (!interventions.isEmpty()) {
            SeanceSoutien seance = consulterInterventionAttribuee(hugo);
            consulterProfilEleve(seance);
            refuserSoutien(seance.getId());
        }

        JpaUtil.fermerFabriquePersistance();
    }
}
