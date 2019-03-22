package objets;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.Date;

public class Personne {
    private String personne_id;
    private Administratif administratif;
    private String prenom;
    private String nom;
    private Date dateNaissance;
    private Date dateDeces;
    private String nationalite;
    private String login;
    private String mdp;
    private String commentaire;
    private Date creation_date;
    private Date modification_date;
    private Integer nbIncidentsDeclares;
    private Integer nbIncidentsLies;
    private Boolean isCivil;

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public Personne(String personne_id, Administratif administratif, String prenom, String nom, Date dateNaissance, Date dateDeces, String nationalite, String login, String mdp, String commentaire, Date creation_date, Date modification_date, Integer nbIncidentsDeclares, Integer nbIncidentsLies, Boolean isCivil)
    {
        this.personne_id = personne_id;
        this.administratif = administratif;
        this.prenom = prenom;
        this.nom = nom;
        this.dateNaissance = dateNaissance;
        this.dateDeces = dateDeces;
        this.nationalite = nationalite;
        this.login = login;
        this.mdp = mdp;
        this.commentaire = commentaire;
        this.creation_date = creation_date;
        this.modification_date = modification_date;
        this.nbIncidentsDeclares = nbIncidentsDeclares;
        this.nbIncidentsLies = nbIncidentsLies;
        this.isCivil = isCivil;
    }

    public String getPersonne_id() {
        return personne_id;
    }

    public Administratif getAdministratif() {
        return administratif;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getNom() {
        return nom;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public Date getDateDeces() {
        return dateDeces;
    }

    public String getNationalite() {
        return nationalite;
    }

    public String getLogin() {
        return login;
    }

    public String getMdp() {
        return mdp;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public Date getCreation_date() {
        return creation_date;
    }

    public Date getModification_date() {
        return modification_date;
    }

    public Integer getNbIncidentsDeclares() {
        return nbIncidentsDeclares;
    }

    public Integer getNbIncidentsLies() {
        return nbIncidentsLies;
    }

    public Boolean getCivil() {
        return isCivil;
    }
}
