package objets;

import java.math.BigInteger;

public class Planete {
    private BigInteger id;
    private String nom;

    public Planete(BigInteger id, String nom) {
        this.id = id;
        this.nom = nom;
    }



    public BigInteger getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }
}
