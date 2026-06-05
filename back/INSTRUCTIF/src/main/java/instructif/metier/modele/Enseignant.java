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
@Table(name="enseignants")
public class Enseignant extends Intervenant {
    @Column(nullable=false)
    protected String typeEtablissement;

    public Enseignant() {
    }

    public Enseignant(String typeEtablissement, String telephone, Niveau niveauMin, Niveau niveauMax, String nom, String prenom, Date dateNaissance, String mail, String motDePasse) {
        super(telephone, niveauMin, niveauMax, nom, prenom, dateNaissance, mail, motDePasse);
        this.typeEtablissement = typeEtablissement;
    }

    public String getTypeEtablissement() {
        return typeEtablissement;
    }

    public void setTypeEtablissement(String typeEtablissement) {
        this.typeEtablissement = typeEtablissement;
    }

    @Override
    public String toString() {
        return "Enseignant{" +
                "typeEtablissement='" + typeEtablissement + '\'' +
                ", telephone='" + telephone + '\'' +
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
