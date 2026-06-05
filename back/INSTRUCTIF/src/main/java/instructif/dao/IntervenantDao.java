package instructif.dao;

import instructif.metier.modele.Intervenant;
import instructif.metier.modele.Statut;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

public class IntervenantDao {
    public void creer(Intervenant intervenant) { JpaUtil.obtenirContextePersistance().persist(intervenant); }

    public Intervenant trouverParId(Long id) {
        return JpaUtil.obtenirContextePersistance().find(Intervenant.class, id);
    }

    public Intervenant trouverParMail(String mail) throws NoResultException {
        String s = "select i from Intervenant i where i.mail = :mail";
        TypedQuery<Intervenant> requete = JpaUtil.obtenirContextePersistance().createQuery(s, Intervenant.class);
        requete.setParameter("mail", mail);
        return requete.getSingleResult();
    }

    public List<Intervenant> trouverTout() {
        String s = "select i from Intervenant i order by i.nom asc";
        TypedQuery<Intervenant> requete = JpaUtil.obtenirContextePersistance().createQuery(s, Intervenant.class);
        return requete.getResultList();
    }

    public List<Intervenant> trouverDisponiblesOrderByNbInterventions() {
        String s = "select i from Intervenant i " +
                   "where i not in (" +
                   "  select s.intervenant from SeanceSoutien s " +
                   "  where s.statut in (:statutEnCours,:statutEnAttente) and s.intervenant is not null" +
                   ") " +
                   "order by (" +
                   "  select count(s2) from SeanceSoutien s2 " +
                   "  where s2.intervenant = i and s2.statut = :statutTerminee" +
                   ") asc";
        TypedQuery<Intervenant> requete = JpaUtil.obtenirContextePersistance().createQuery(s, Intervenant.class);
        requete.setParameter("statutEnCours", Statut.EnCours);
        requete.setParameter("statutEnAttente", Statut.EnAttente);
        requete.setParameter("statutTerminee", Statut.Terminee);
        return requete.getResultList();
    }
}
