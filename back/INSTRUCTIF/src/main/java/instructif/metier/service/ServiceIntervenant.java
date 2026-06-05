/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package instructif.metier.service;

import instructif.dao.IntervenantDao;
import instructif.dao.JpaUtil;
import instructif.metier.modele.Intervenant;
import instructif.util.MdpHasher;

import javax.persistence.NoResultException;
import javax.security.sasl.AuthenticationException;
import java.util.List;

/**
 *
 * @author aessalhi
 */
public class ServiceIntervenant {
    private static final String MAIL_EXPEDITEUR = "instructif@insa-lyon.fr";

    public ServiceIntervenant() {
    }

    public Intervenant recupererIntervenant(Long idIntervenant) {
        IntervenantDao intervenantDao = new IntervenantDao();
        Intervenant intervenant = null;
        try {
            JpaUtil.creerContextePersistance();
            intervenant = intervenantDao.trouverParId(idIntervenant);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return intervenant;
    }

    public Intervenant authentifierIntervenant(String mail, String mdp) {
        IntervenantDao intervenantDao = new IntervenantDao();
        Intervenant intervenant = null;
        try {
            JpaUtil.creerContextePersistance();
            Intervenant intervenantAConnecter = intervenantDao.trouverParMail(mail);
            if (MdpHasher.verifier(mdp, intervenantAConnecter.getMotDePasse())) {
                intervenant = intervenantAConnecter;
            }
            else throw new AuthenticationException("Invalid credentials");
        }
        catch (AuthenticationException | NoResultException ex) {
            ex.printStackTrace();
        }
        finally {
            JpaUtil.fermerContextePersistance();
        }
        return intervenant;
    }

    public List<Intervenant> listerIntervenants() {
        IntervenantDao intervenantDao = new IntervenantDao();
        List<Intervenant> intervenants = null;
        try {
            JpaUtil.creerContextePersistance();
            intervenants = intervenantDao.trouverTout();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return intervenants;
    }
}
