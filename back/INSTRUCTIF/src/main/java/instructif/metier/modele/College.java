package instructif.metier.modele;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="colleges")
public class College {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    protected Long id;

    @Column(nullable = false, unique = true)
    protected String codeEtablissement;

    @Column(nullable = false)
    protected String nom;

    @Column(nullable = false)
    protected String adresse;

    @Column(nullable = false)
    protected String codePostal;

    @Column(nullable = false)
    protected String commune;

    @Column(nullable = false)
    protected String departement;

    @Column(nullable = false)
    protected String academie;

    @Column(nullable = false)
    protected String secteur;

    @Column(nullable = true)
    protected Double latitude;

    @Column(nullable = true)
    protected Double longitude;

    public College() {
    }

    public College(String codeEtablissement, String nom, String adresse, String codePostal, String commune, String departement, String academie, String secteur, Double latitude, Double longitude) {
        this.codeEtablissement = codeEtablissement;
        this.nom = nom;
        this.adresse = adresse;
        this.codePostal = codePostal;
        this.commune = commune;
        this.departement = departement;
        this.academie = academie;
        this.secteur = secteur;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Long getId() {
        return id;
    }

    public String getCodeEtablissement() {
        return codeEtablissement;
    }

    public void setCodeEtablissement(String codeEtablissement) {
        this.codeEtablissement = codeEtablissement;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getCommune() {
        return commune;
    }

    public void setCommune(String commune) {
        this.commune = commune;
    }

    public String getDepartement() {
        return departement;
    }

    public void setDepartement(String departement) {
        this.departement = departement;
    }

    public String getAcademie() {
        return academie;
    }

    public void setAcademie(String academie) {
        this.academie = academie;
    }

    public String getSecteur() {
        return secteur;
    }

    public void setSecteur(String secteur) {
        this.secteur = secteur;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "College{" +
                "id=" + id +
                ", codeEtablissement='" + codeEtablissement + '\'' +
                ", nom='" + nom + '\'' +
                ", adresse='" + adresse + '\'' +
                ", codePostal='" + codePostal + '\'' +
                ", commune='" + commune + '\'' +
                ", departement='" + departement + '\'' +
                ", academie='" + academie + '\'' +
                ", secteur='" + secteur + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        College college = (College) o;
        return Objects.equals(id, college.id);
    }
}
