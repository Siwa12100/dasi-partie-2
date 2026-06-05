package instructif.metier.modele;

import java.util.ArrayList;
import java.util.List;

public class StatistiquesIntervenant {
    private Double moyenneDuree;
    private Integer entretiensNb;
    private List<College> colleges = new ArrayList<>();

    public StatistiquesIntervenant() {
    }

    public StatistiquesIntervenant(Double moyenneDuree, Integer entretiensNb) {
        this.moyenneDuree = moyenneDuree;
        this.entretiensNb = entretiensNb;
    }

    public Double getMoyenneDuree() { return moyenneDuree; }
    public void setMoyenneDuree(Double moyenneDuree) { this.moyenneDuree = moyenneDuree; }

    public Integer getEntretiensNb() { return entretiensNb; }
    public void setEntretiensNb(Integer entretiensNb) { this.entretiensNb = entretiensNb; }

    public List<College> getColleges() { return colleges; }
    public void setColleges(List<College> colleges) { this.colleges = colleges; }
    public void addCollege(College college) { this.colleges.add(college); }

    @Override
    public String toString() {
        return "StatistiquesIntervenant{moyenneDuree=" + moyenneDuree
            + ", entretiensNb=" + entretiensNb
            + ", colleges=" + colleges.size() + "}";
    }
}
