package objets;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

public class Incident
{
    private BigInteger incident_id;
    private Personne personne;
    private Administratif administratif;
    private List<SuperCivil> villains;
    private String commentaire;
    private Date creation_date;
    private Boolean sans_suite;

    public Incident(BigInteger incident_id, Personne personne, Administratif administratif, List<SuperCivil> villains, String commentaire, Date creation_date, Boolean sans_suite)
    {
        this.incident_id = incident_id;
        this.personne = personne;
        this.administratif = administratif;
        this.villains = villains;
        this.commentaire = commentaire;
        this.creation_date = creation_date;
        this.sans_suite = sans_suite;
    }

    public BigInteger getIncident_id() {
        return incident_id;
    }

    public Personne getPersonne() {
        return personne;
    }

    public Administratif getAdministratif() {
        return administratif;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public Date getCreation_date() {
        return creation_date;
    }

    public Boolean getSans_suite() {
        return sans_suite;
    }

    public List<SuperCivil> getVillains() {
        return villains;
    }
}
