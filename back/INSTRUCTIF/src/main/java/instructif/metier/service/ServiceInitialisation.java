package instructif.metier.service;

import instructif.dao.IntervenantDao;
import instructif.dao.JpaUtil;
import instructif.dao.MatiereDao;
import instructif.dao.ThemeDao;
import instructif.metier.modele.Enseignant;
import instructif.metier.modele.Etudiant;
import instructif.metier.modele.Matiere;
import instructif.metier.modele.Niveau;
import instructif.metier.modele.Theme;
import instructif.util.MdpHasher;

import java.util.Date;

public class ServiceInitialisation {
    public ServiceInitialisation() {
    }

    public boolean initialiser() {
        MatiereDao matiereDao = new MatiereDao();
        ThemeDao themeDao = new ThemeDao();
        IntervenantDao intervenantDao = new IntervenantDao();
        try {
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();

            Matiere histoire = new Matiere("Histoire et Géographie");
            Matiere maths = new Matiere("Mathématiques");
            Matiere francais = new Matiere("Français");
            matiereDao.creer(histoire);
            matiereDao.creer(maths);
            matiereDao.creer(francais);

            themeDao.creer(new Theme("Moyen-Age", histoire));
            themeDao.creer(new Theme("La Révolution française", histoire));
            themeDao.creer(new Theme("La Première Guerre mondiale", histoire));
            themeDao.creer(new Theme("Fractions", maths));
            themeDao.creer(new Theme("Théorème de Pythagore", maths));
            themeDao.creer(new Theme("Équations", maths));
            themeDao.creer(new Theme("Grammaire", francais));
            themeDao.creer(new Theme("Conjugaison", francais));

            Etudiant camille = new Etudiant(
                "Langues orientales", "Sorbonne",
                "06 55 44 77 88", Niveau.SIXIEME, Niveau.TROISIEME,
                "Martin", "Camille", new Date(),
                "cmartin@sorbonne.fr", MdpHasher.encoder("camille")
            );
            Enseignant emile = new Enseignant(
                "Collège",
                "07 88 55 99 44", Niveau.TROISIEME, Niveau.TROISIEME,
                "Hugo", "Emile", new Date(),
                "ehugo@college.fr", MdpHasher.encoder("emile")
            );
            intervenantDao.creer(camille);
            intervenantDao.creer(emile);

            JpaUtil.validerTransaction();
            return true;
        } catch (Exception ex) {
            JpaUtil.annulerTransaction();
            ex.printStackTrace();
            return false;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
    }
}
