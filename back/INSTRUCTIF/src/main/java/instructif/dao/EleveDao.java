package instructif.dao;

import instructif.metier.modele.Eleve;

import javax.persistence.TypedQuery;
import java.util.List;
import javax.persistence.NoResultException;

public class EleveDao {
    public void creer(Eleve eleve) { JpaUtil.obtenirContextePersistance().persist(eleve); }

    public Eleve trouverParMail(String mail) throws NoResultException {
        String s = "select e from Eleve e where e.mail = :mail";
        TypedQuery<Eleve> requete = JpaUtil.obtenirContextePersistance().createQuery(s, Eleve.class);
        requete.setParameter("mail", mail);
        return requete.getSingleResult();
    }
    
    public Eleve trouverParId(Long id) {
        return JpaUtil.obtenirContextePersistance().find(Eleve.class, id);
    }

    public List<Eleve> trouverTout() {
        String s = "select e from Eleve e order by e.nom asc";
        TypedQuery<Eleve> requete = JpaUtil.obtenirContextePersistance().createQuery(s, Eleve.class);
        return requete.getResultList();
    }
}
