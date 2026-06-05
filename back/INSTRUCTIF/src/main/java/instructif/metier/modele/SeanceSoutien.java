package instructif.metier.modele;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name="seances_soutien")
public class SeanceSoutien {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @Column(nullable = false)
    protected LocalDateTime dateHeure;

    @Column(length = 2000)
    protected String compteRendu;

    protected Integer duree;

    @Column(nullable = false, length = 1000)
    protected String messageDemande;

    @Column(length = 500)
    protected String lienVisio;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    protected Statut statut;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ELEVE_ID", nullable = false)
    protected Eleve eleve;

    @ManyToOne(optional = true)
    @JoinColumn(name = "INTERVENANT_ID", nullable = true)
    protected Intervenant intervenant;

    @ManyToOne(optional = false)
    @JoinColumn(name = "THEME_ID", nullable = false)
    protected Theme theme;

    public SeanceSoutien() {
    }

    public SeanceSoutien(LocalDateTime dateHeure, String messageDemande, Statut statut, Eleve eleve, Theme theme) {
        this.dateHeure = dateHeure;
        this.messageDemande = messageDemande;
        this.statut = statut;
        this.eleve = eleve;
        this.theme = theme;
    }

    public Long getId() { return id; }

    public LocalDateTime getDateHeure() { return dateHeure; }
    public void setDateHeure(LocalDateTime dateHeure) { this.dateHeure = dateHeure; }

    public String getCompteRendu() { return compteRendu; }
    public void setCompteRendu(String compteRendu) { this.compteRendu = compteRendu; }

    public Integer getDuree() { return duree; }
    public void setDuree(Integer duree) { this.duree = duree; }

    public String getMessageDemande() { return messageDemande; }
    public void setMessageDemande(String messageDemande) { this.messageDemande = messageDemande; }

    public String getLienVisio() { return lienVisio; }
    public void setLienVisio(String lienVisio) { this.lienVisio = lienVisio; }

    public Statut getStatut() { return statut; }
    public void setStatut(Statut statut) { this.statut = statut; }

    public Eleve getEleve() { return eleve; }
    public void setEleve(Eleve eleve) { this.eleve = eleve; }

    public Intervenant getIntervenant() { return intervenant; }
    public void setIntervenant(Intervenant intervenant) { this.intervenant = intervenant; }

    public Theme getTheme() { return theme; }
    public void setTheme(Theme theme) { this.theme = theme; }

    @Override
    public String toString() {
        return "SeanceSoutien{id=" + id + ", dateHeure=" + dateHeure + ", statut=" + statut + ", eleve=" + eleve + ", theme=" + theme + "}";
    }

    @Override
    public int hashCode() { return Objects.hashCode(id); }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SeanceSoutien s = (SeanceSoutien) o;
        return Objects.equals(id, s.id);
    }
}
