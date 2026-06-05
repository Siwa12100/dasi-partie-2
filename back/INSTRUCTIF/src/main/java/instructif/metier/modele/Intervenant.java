/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package instructif.metier.modele;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 *
 * @author aessalhi
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name="intervenants")
public class Intervenant extends Personne {
    @Column(nullable=false)
    protected String telephone;
    
    @Enumerated(EnumType.STRING)
    protected Niveau niveauMin;
    
    @Enumerated(EnumType.STRING)
    protected Niveau niveauMax;
    
    public Intervenant() {
    }

    public Intervenant(String telephone, Niveau niveauMin, Niveau niveauMax, String nom, String prenom, Date dateNaissance, String mail, String motDePasse) {
        super(nom, prenom, dateNaissance, mail, motDePasse);
        this.telephone = telephone;
        this.niveauMin = niveauMin;
        this.niveauMax = niveauMax;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Niveau getNiveauMin() {
        return niveauMin;
    }

    public void setNiveauMin(Niveau niveauMin) {
        this.niveauMin = niveauMin;
    }

    public Niveau getNiveauMax() {
        return niveauMax;
    }

    public void setNiveauMax(Niveau niveauMax) {
        this.niveauMax = niveauMax;
    }

    @Override
    public String toString() {
        return "Intervenant{" +
                "telephone='" + telephone + '\'' +
                ", niveauMin=" + niveauMin +
                ", niveauMax=" + niveauMax +
                ", id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", dateNaissance=" + dateNaissance +
                ", mail='" + mail + '\'' +
                ", motDePasse='" + motDePasse + '\'' +
                '}';
    }
}
