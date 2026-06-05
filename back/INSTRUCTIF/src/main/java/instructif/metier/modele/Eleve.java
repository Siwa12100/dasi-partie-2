package instructif.metier.modele;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="eleves")
public class Eleve extends Personne {
    @ManyToOne
    @JoinColumn(name = "COLLEGE_ID", nullable = false)
    protected College college;

    @Enumerated(EnumType.STRING)
    protected Niveau classe;

    public Eleve() {
    }

    public Eleve(String nom, String prenom, Date dateNaissance, String mail, String motDePasse, College college, Niveau classe) {
        super(nom, prenom, dateNaissance, mail, motDePasse);
        this.college = college;
        this.classe = classe;
    }

    public College getCollege() {
        return college;
    }

    public void setCollege(College college) {
        this.college = college;
    }

    public Niveau getClasse() {
        return classe;
    }

    public void setClasse(Niveau classe) {
        this.classe = classe;
    }
}
