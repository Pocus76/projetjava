package objets;

import java.util.Date;

public class Incident
{
    private int incident_id;
    private Date incident_date;
    private String incident_adresse;
    private String incident_ville;
    private String incident_pays;
    private String incident_cp;
    private Boolean sans_suite;

    public Incident()
    {

    }

    public Incident(int incident_id, Date incident_date, String incident_adresse,
                    String incident_ville, String incident_pays, String incident_cp, Boolean sans_suite)
    {
        this.incident_id = incident_id;
        this.incident_date = incident_date;
        this.incident_adresse = incident_adresse;
        this.incident_ville = incident_ville;
        this.incident_pays = incident_pays;
        this.incident_cp = incident_cp;
        this.sans_suite = sans_suite;
    }

    public int getIncident_id() {
        return incident_id;
    }

    public Date getIncident_date() {
        return incident_date;
    }

    public String getIncident_adresse() {
        return incident_adresse;
    }

    public String getIncident_ville() {
        return incident_ville;
    }

    public String getIncident_pays() {
        return incident_pays;
    }

    public String getIncident_cp() {
        return incident_cp;
    }

    public Boolean getSans_suite() {return sans_suite;}
}
