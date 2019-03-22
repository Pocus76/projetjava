package objets;

import java.util.Date;

public class Mission {

    private int id;
    private int rapport_id;
    private int satisfaction_id;
    private int litige_id;
    private int incident_id;
    private String titre;
    private String nature;
    private Date date_debut;
    private Date date_fin;
    private String commentaire;
    private int gravite;
    private int urgence;

    public Mission(int id, int rapport_id,
                   int satisfaction_id, int litige_id,
                   int incident_id, String titre,
                   String nature, Date date_debut,
                   Date date_fin, String commentaire,
                   int gravite, int urgence)
    {
        this.id = id;
        this.rapport_id = rapport_id;
        this.satisfaction_id = satisfaction_id;
        this.litige_id = litige_id;
        this.incident_id = incident_id;
        this.titre = titre;
        this.nature = nature;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.commentaire = commentaire;
        this.gravite = gravite;
        this.urgence = urgence;
    }

    public int getId() {
        return id;
    }

    public int getRapport_id() {
        return rapport_id;
    }

    public int getSatisfaction_id() {
        return satisfaction_id;
    }

    public int getLitige_id() {
        return litige_id;
    }

    public int getIncident_id() {
        return incident_id;
    }

    public String getTitre() {
        return titre;
    }

    public String getNature() {
        return nature;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public int getGravite() {
        return gravite;
    }

    public int getUrgence() {
        return urgence;
    }
}
