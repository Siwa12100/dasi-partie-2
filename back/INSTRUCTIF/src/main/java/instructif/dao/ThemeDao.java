package instructif.dao;

import instructif.metier.modele.Theme;

import javax.persistence.TypedQuery;
import java.util.List;

public class ThemeDao {
    public void creer(Theme theme) { JpaUtil.obtenirContextePersistance().persist(theme); }

    public Theme trouverParId(Long id) {
        return JpaUtil.obtenirContextePersistance().find(Theme.class, id);
    }

    public List<Theme> trouverParMatiere(Long idMatiere) {
        String s = "select t from Theme t where t.matiere.id = :idMatiere order by t.nom asc";
        TypedQuery<Theme> requete = JpaUtil.obtenirContextePersistance().createQuery(s, Theme.class);
        requete.setParameter("idMatiere", idMatiere);
        return requete.getResultList();
    }

    public List<Theme> trouverTout() {
        String s = "select t from Theme t order by t.nom asc";
        TypedQuery<Theme> requete = JpaUtil.obtenirContextePersistance().createQuery(s, Theme.class);
        return requete.getResultList();
    }
}
