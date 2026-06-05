package instructif.metier.modele;

public enum Niveau {
    SIXIEME(6),
    CINQUIEME(5),
    QUATRIEME(4),
    TROISIEME(3);

    private final Integer valeur;

    Niveau(Integer valeur) {
        this.valeur = valeur;
    }

    public Integer getValeur() {
        return valeur;
    }
}
