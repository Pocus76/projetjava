package objets;

public class Adresse {
    private String adresse;
    private String cp;
    private String ville;
    private String pays;
    private Planete planete;

    public Adresse(String adresse, String cp, String ville, String pays, Planete planete) {
        this.adresse = adresse;
        this.cp = cp;
        this.ville = ville;
        this.pays = pays;
        this.planete = planete;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getCp() {
        return cp;
    }

    public String getVille() {
        return ville;
    }

    public String getPays() {
        return pays;
    }

    public Planete getPlanete() {
        return planete;
    }
}
