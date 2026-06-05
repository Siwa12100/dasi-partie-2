package instructif.metier.service;

import instructif.dao.JpaUtil;
import instructif.dao.SeanceSoutienDao;
import instructif.metier.modele.College;
import instructif.metier.modele.Intervenant;
import instructif.metier.modele.SeanceSoutien;
import instructif.metier.modele.StatistiquesIntervenant;
import instructif.metier.modele.Statut;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class ServiceStatistiques {
    public ServiceStatistiques() {
    }

    public StatistiquesIntervenant obtenirStatistiquesIntervenant(Intervenant intervenant) {
        SeanceSoutienDao seanceSoutienDao = new SeanceSoutienDao();
        StatistiquesIntervenant stats = new StatistiquesIntervenant();
        try {
            JpaUtil.creerContextePersistance();
            List<SeanceSoutien> seances = seanceSoutienDao.trouverParIntervenant(intervenant);

            int nb = 0;
            int sommeDuree = 0;
            int nbAvecDuree = 0;
            Set<College> colleges = new LinkedHashSet<>();

            for (SeanceSoutien s : seances) {
                if (s.getStatut() != Statut.Terminee) continue;
                nb++;
                if (s.getDuree() != null) {
                    sommeDuree += s.getDuree();
                    nbAvecDuree++;
                }
                if (s.getEleve() != null && s.getEleve().getCollege() != null) {
                    colleges.add(s.getEleve().getCollege());
                }
            }

            stats.setEntretiensNb(nb);
            stats.setMoyenneDuree(nbAvecDuree > 0 ? (double) sommeDuree / nbAvecDuree : 0.0);
            stats.setColleges(new ArrayList<>(colleges));
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return stats;
    }
}
