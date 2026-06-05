package instructif.dao;

import instructif.metier.modele.Eleve;
import instructif.metier.modele.Intervenant;
import instructif.metier.modele.SeanceSoutien;
import instructif.metier.modele.Statut;

import javax.persistence.TypedQuery;
import java.util.List;

public class SeanceSoutienDao {
    public void creer(SeanceSoutien seance) { JpaUtil.obtenirContextePersistance().persist(seance); }

    public SeanceSoutien trouverParId(Long id) {
        return JpaUtil.obtenirContextePersistance().find(SeanceSoutien.class, id);
    }

    public List<SeanceSoutien> trouverParEleve(Eleve eleve) {
        String s = "select s from SeanceSoutien s where s.eleve = :eleve order by s.dateHeure desc";
        TypedQuery<SeanceSoutien> requete = JpaUtil.obtenirContextePersistance().createQuery(s, SeanceSoutien.class);
        requete.setParameter("eleve", eleve);
        return requete.getResultList();
    }

    public List<SeanceSoutien> trouverParIntervenant(Intervenant intervenant) {
        String s = "select s from SeanceSoutien s where s.intervenant = :intervenant order by s.dateHeure desc";
        TypedQuery<SeanceSoutien> requete = JpaUtil.obtenirContextePersistance().createQuery(s, SeanceSoutien.class);
        requete.setParameter("intervenant", intervenant);
        return requete.getResultList();
    }

    public SeanceSoutien trouverDemandeAttribuee(Long idIntervenant) {
        String s = "select s from SeanceSoutien s "
                 + "where s.intervenant.id = :idIntervenant "
                 + "and s.statut in (:enAttente, :enCours) "
                 + "order by s.dateHeure desc";
        TypedQuery<SeanceSoutien> requete = JpaUtil.obtenirContextePersistance().createQuery(s, SeanceSoutien.class);
        requete.setParameter("idIntervenant", idIntervenant);
        requete.setParameter("enAttente", Statut.EnAttente);
        requete.setParameter("enCours", Statut.EnCours);
        List<SeanceSoutien> resultats = requete.getResultList();
        return resultats.isEmpty() ? null : resultats.getFirst();
    }
}
