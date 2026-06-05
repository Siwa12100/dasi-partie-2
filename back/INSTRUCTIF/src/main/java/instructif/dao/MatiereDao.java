package instructif.dao;

import instructif.metier.modele.Matiere;

import javax.persistence.TypedQuery;
import java.util.List;

public class MatiereDao {
    public void creer(Matiere matiere) { JpaUtil.obtenirContextePersistance().persist(matiere); }

    public Matiere trouverParId(Long id) {
        return JpaUtil.obtenirContextePersistance().find(Matiere.class, id);
    }

    public List<Matiere> trouverTout() {
        String s = "select m from Matiere m order by m.nom asc";
        TypedQuery<Matiere> requete = JpaUtil.obtenirContextePersistance().createQuery(s, Matiere.class);
        return requete.getResultList();
    }
}
