/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package instructif.metier.service;

import instructif.dao.CollegeDao;
import instructif.dao.EleveDao;
import instructif.dao.JpaUtil;
import instructif.metier.modele.College;
import instructif.metier.modele.Eleve;
import instructif.util.EducationGouvCollegeAPI;
import instructif.util.MdpHasher;
import instructif.util.Message;

import java.util.List;
import javax.persistence.NoResultException;
import javax.security.sasl.AuthenticationException;

/**
 *
 * @author nfoussard
 */
public class ServiceEleve {
    private static final String MAIL_EXPEDITEUR = "instructif@insa-lyon.fr";

    public ServiceEleve() {
    }
    
    public boolean inscrireEleve(Eleve eleve, String codeEtablissement){
        EleveDao eleveDao = new EleveDao();
        CollegeDao collegeDao = new CollegeDao();
        boolean result;
        
        try {
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();
            College college = collegeDao.trouverParCodeEtablissement(codeEtablissement);
            if (college == null) {
                college = EducationGouvCollegeAPI.getCollege(codeEtablissement);
                if (college == null) throw new RuntimeException("Collège non trouvé.");
                collegeDao.creer(college);
            }
            eleve.setCollege(college);
            String mdp = eleve.getMotDePasse();
            eleve.setMotDePasse(MdpHasher.encoder(mdp));
            eleveDao.creer(eleve);
            JpaUtil.validerTransaction();
            Message.envoyerMail(
                MAIL_EXPEDITEUR,
                eleve.getMail(),
                "Confirmation d'inscription de votre compte",
                "Bonjour " + eleve.getPrenom() + " " + eleve.getNom() + ",\n\n"
                    + "Nous vous confirmons que votre inscription a bien été prise en compte.\n\n"
                    + "Cordialement,\nL'équipe Instruct'IF."
            );
            result = true;
        }
        catch (Exception ex) {
            JpaUtil.annulerTransaction();
            Message.envoyerMail(
                MAIL_EXPEDITEUR,
                eleve.getMail(),
                "Erreur d'inscription de votre compte",
                "Bonjour " + eleve.getPrenom() + " " + eleve.getNom() + ",\n\n"
                    + "Nous vous informons que l'inscription de votre compte a échoué.\n\n"
                    + "Cordialement,\nL'équipe Instruct'IF."
            );
            result = false; 
        }
        finally {
            JpaUtil.fermerContextePersistance();
        }
        return result;
    }
    
    public Eleve authentifierEleve(String mail, String mdp) {
        EleveDao eleveDao = new EleveDao();
        Eleve eleve = null;
        try {
            JpaUtil.creerContextePersistance();
            Eleve eleveAConnecter = eleveDao.trouverParMail(mail);
            if (MdpHasher.verifier(mdp, eleveAConnecter.getMotDePasse())) {
                eleve = eleveAConnecter;
            }
            else throw new AuthenticationException("Invalid credentials");
        }
        catch (AuthenticationException|NoResultException ex) {
            ex.printStackTrace();
        }
        return eleve;
    }
                
    public Eleve recupererEleve(Long idEleve) {
        EleveDao eleveDao = new EleveDao();
        Eleve eleve = null;
        try {
            JpaUtil.creerContextePersistance();
            eleve = eleveDao.trouverParId(idEleve);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            JpaUtil.fermerContextePersistance();
        }
        return eleve;
    }

    public List<Eleve> listerEleves() {
        EleveDao eleveDao = new EleveDao();
        List<Eleve> eleves;
        try {
            JpaUtil.creerContextePersistance();
            eleves = eleveDao.trouverTout();
        }
        catch (Exception ex) {
            JpaUtil.annulerTransaction();
            return null; 
        }
        finally {
            JpaUtil.fermerContextePersistance();
        }
        return eleves;
    }
}
