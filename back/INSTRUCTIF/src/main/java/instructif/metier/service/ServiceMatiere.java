package instructif.metier.service;

import instructif.dao.JpaUtil;
import instructif.dao.MatiereDao;
import instructif.dao.ThemeDao;
import instructif.metier.modele.Matiere;
import instructif.metier.modele.Theme;

import java.util.List;

public class ServiceMatiere {
    public ServiceMatiere() {
    }

    public Matiere recupererMatiere(Long idMatiere) {
        MatiereDao matiereDao = new MatiereDao();
        Matiere matiere = null;
        try {
            JpaUtil.creerContextePersistance();
            matiere = matiereDao.trouverParId(idMatiere);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return matiere;
    }

    public Theme recupererTheme(Long idTheme) {
        ThemeDao themeDao = new ThemeDao();
        Theme theme = null;
        try {
            JpaUtil.creerContextePersistance();
            theme = themeDao.trouverParId(idTheme);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return theme;
    }

    public List<Theme> recupererThemesParMatiere(Long idMatiere) {
        ThemeDao themeDao = new ThemeDao();
        List<Theme> themes = null;
        try {
            JpaUtil.creerContextePersistance();
            themes = themeDao.trouverParMatiere(idMatiere);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return themes;
    }

    public List<Matiere> listerMatieres() {
        MatiereDao matiereDao = new MatiereDao();
        List<Matiere> matieres = null;
        try {
            JpaUtil.creerContextePersistance();
            matieres = matiereDao.trouverTout();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return matieres;
    }

    public List<Theme> listerThemes() {
        ThemeDao themeDao = new ThemeDao();
        List<Theme> themes = null;
        try {
            JpaUtil.creerContextePersistance();
            themes = themeDao.trouverTout();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return themes;
    }
}
