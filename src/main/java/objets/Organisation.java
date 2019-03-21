package objets;

import java.util.Date;

public class Organisation {


    private int planete_id;
    private String nom;
    private String adresse;
    private String cp;
    private String ville;
    private String pays;
    private String tel;
    private String mail;
    private String commentaire;
    private Date  creation_date;
    private int nb_incidents_declares;
    private int nb_incidents_lies;

    public Organisation()
    {

    }

    public Organisation(int id, int planete_id, String nom, String adresse, String cp,
                        String ville, String pays, String tel, String mail, String commentaire,
                        Date creation_date, int nb_incidents_declares, int nb_incidents_lies)
    {
        this.id = id;
        this.planete_id = planete_id;
        this.nom = nom;
        this.adresse = adresse;
        this.cp = cp;
        this.ville = ville;
        this.pays = pays;
        this.tel = tel;
        this.mail = mail;
        this.commentaire = commentaire;
        this.creation_date = creation_date;
        this.nb_incidents_declares = nb_incidents_declares;
        this.nb_incidents_lies = nb_incidents_lies;
    }

    private int id;

    public int getId() {
        return id;
    }

    public int getPlanete_id() {
        return planete_id;
    }

    public String getNom() {
        return nom;
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

    public String getTel() {
        return tel;
    }

    public String getMail() {
        return mail;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public Date getCreation_date() {
        return creation_date;
    }

    public int getNb_incidents_declares() {
        return nb_incidents_declares;
    }

    public int getNb_incidents_lies() {
        return nb_incidents_lies;
    }
}
