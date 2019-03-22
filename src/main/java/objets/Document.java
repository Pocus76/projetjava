package objets;

import java.math.BigInteger;

public class Document {
    private BigInteger document_id;
    private Personne personne;
    private String libelle;

    public Document(BigInteger document_id, Personne personne, String libelle) {
        this.document_id = document_id;
        this.personne = personne;
        this.libelle = libelle;
    }

    public BigInteger getDocument_id() {
        return document_id;
    }

    public Personne getPersonne() {
        return personne;
    }

    public String getLibelle() {
        return libelle;
    }
}
