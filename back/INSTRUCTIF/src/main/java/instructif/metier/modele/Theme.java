package instructif.metier.modele;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="themes")
public class Theme {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @Column(nullable = false)
    protected String nom;

    @ManyToOne(optional = false)
    @JoinColumn(name = "MATIERE_ID", nullable = false)
    protected Matiere matiere;

    public Theme() {
    }

    public Theme(String nom, Matiere matiere) {
        this.nom = nom;
        this.matiere = matiere;
    }

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Matiere getMatiere() {
        return matiere;
    }

    public void setMatiere(Matiere matiere) {
        this.matiere = matiere;
    }

    @Override
    public String toString() {
        return "Theme{id=" + id + ", nom='" + nom + "', matiere=" + matiere + "}";
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Theme theme = (Theme) o;
        return Objects.equals(id, theme.id);
    }
}
