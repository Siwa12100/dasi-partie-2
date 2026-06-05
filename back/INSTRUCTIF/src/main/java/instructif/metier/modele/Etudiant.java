/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package instructif.metier.modele;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author aessalhi
 */
@Entity
@Table(name="etudiants")
public class Etudiant extends Intervenant {
    @Column(nullable=false)
    protected String specialite;
        
    @Column(nullable=false)    
    protected String etablissement;

    public Etudiant() {
    }

    public Etudiant(String specialite, String etablissement, String telephone, Niveau niveauMin, Niveau niveauMax, String nom, String prenom, Date dateNaissance, String mail, String motDePasse) {
        super(telephone, niveauMin, niveauMax, nom, prenom, dateNaissance, mail, motDePasse);
        this.specialite = specialite;
        this.etablissement = etablissement;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public String getEtablissement() {
        return etablissement;
    }

    public void setEtablissement(String etablissement) {
        this.etablissement = etablissement;
    }

    @Override
    public String toString() {
        return "Etudiant{" +
                "etablissement='" + etablissement + '\'' +
                ", telephone='" + telephone + '\'' +
                ", niveauMin=" + niveauMin +
                ", niveauMax=" + niveauMax +
                ", id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", dateNaissance=" + dateNaissance +
                ", mail='" + mail + '\'' +
                ", motDePasse='" + motDePasse + '\'' +
                ", specialite='" + specialite + '\'' +
                '}';
    }
}
