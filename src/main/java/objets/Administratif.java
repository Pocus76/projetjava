package objets;

import java.math.BigInteger;

public class Administratif {
    private BigInteger administratif_id;
    private String adresse;
    private String cp;
    private String ville;
    private String pays;
    private String planete;
    private String telephone;
    private String email;

    public Administratif(BigInteger administratif_id, String adresse, String cp, String ville, String pays, String planete, String email, String telephone) {
        this.administratif_id = administratif_id;
        this.adresse = adresse;
        this.cp = cp;
        this.ville = ville;
        this.pays = pays;
        this.planete = planete;
        this.telephone = telephone;
        this.email = email;
    }

    public Administratif(String adresse, String cp, String ville, String pays, String planete, String email, String telephone)
    {
        this.adresse = adresse;
        this.cp = cp;
        this.ville = ville;
        this.pays = pays;
        this.planete = planete;
        this.telephone = telephone;
        this.email = email;
    }

    public BigInteger getAdministratif_id() { return administratif_id; }

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

    public String getPlanete() {
        return planete;
    }

    public String getTelephone() { return  telephone; }

    public String getEmail() { return email; }
}
