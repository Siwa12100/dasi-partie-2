/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package instructif.dao;

import instructif.metier.modele.College;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author aessalhi
 */
public class CollegeDao {
        public void creer(College clg) { 
            JpaUtil.obtenirContextePersistance().persist(clg); 
        }
        
        public College trouverParCodeEtablissement(String codeEtablissement) {
            College clg;
            try {
                String s = "select c from College c where c.codeEtablissement = :code";
                TypedQuery<College> requete = JpaUtil.obtenirContextePersistance().createQuery(s, College.class);
    
                requete.setParameter("code", codeEtablissement);
                clg = requete.getSingleResult();
            }
            catch (NoResultException ex) {
                clg = null;
            }
            return clg;
        }
        
        public List<College> trouverTout() {
        String s = "select c from College c order by c.nom asc";
        TypedQuery<College> requete = JpaUtil.obtenirContextePersistance().createQuery(s, College.class);
        return requete.getResultList();
    }
}
