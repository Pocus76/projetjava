package objets;

//----------------------------------------------------------------------------------------------------------------------

import java.math.BigInteger;

/**
 * @author : Pocus
 * @dateCreation : 21/03/2019
 * @description : Classe qui définit un super civil
 */
//----------------------------------------------------------------------------------------------------------------------
public class SuperCivil {
    private BigInteger superCivil_id;
    private Personne personne;
    private String nom;
    private String pouvoir;
    private String faiblesse;
    private Float score;
    private String commentaire;
    private Boolean isSuperHeros;

    public SuperCivil(BigInteger superCivil_id, Personne personne, String nom, String pouvoir, String faiblesse, Float score, String commentaire, Boolean isSuperHeros) {
        this.superCivil_id = superCivil_id;
        this.personne = personne;
        this.nom = nom;
        this.pouvoir = pouvoir;
        this.faiblesse = faiblesse;
        this.score = score;
        this.commentaire = commentaire;
        this.isSuperHeros = isSuperHeros;
    }

    public SuperCivil(Personne personne, String nom, String pouvoir, String faiblesse, Float score, String commentaire, Boolean isSuperHeros)
    {
        this.superCivil_id = superCivil_id;
        this.personne = personne;
        this.nom = nom;
        this.pouvoir = pouvoir;
        this.faiblesse = faiblesse;
        this.score = score;
        this.commentaire = commentaire;
        this.isSuperHeros = isSuperHeros;
    }

    public BigInteger getSuperCivil_id() {
        return superCivil_id;
    }

    public Personne getPersonne() {
        return personne;
    }

    public String getNom() {
        return nom;
    }

    public String getPouvoir() {
        return pouvoir;
    }

    public String getFaiblesse() {
        return faiblesse;
    }

    public Float getScore() {
        return score;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public Boolean getSuperHeros() {
        return isSuperHeros;
    }
}
