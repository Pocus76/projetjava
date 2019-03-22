package objets;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

public class Mission {

    private BigInteger mission_id;
    private Incident incident;
    private List<SuperCivil> heros;
    private String nature;
    private String titre;
    private String description;
    private Date dateDebut;
    private Date dateFin;
    private Integer gravite;
    private Integer urgence;

    public Mission(BigInteger mission_id, Incident incident, List<SuperCivil> heros,
                   String nature, String titre,
                   String description, Date dateDebut,
                   Date dateFin, int gravite, int urgence)
    {
        this.mission_id = mission_id;
        this.incident = incident;
        this.heros = heros;
        this.nature = nature;
        this.titre = titre;
        this.description = description;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.gravite = gravite;
        this.urgence = urgence;
    }

    public BigInteger getMission_id() {
        return mission_id;
    }

    public Incident getIncident() {
        return incident;
    }

    public List<SuperCivil> getHeros() {
        return heros;
    }

    public String getNature() {
        return nature;
    }

    public String getTitre() {
        return titre;
    }

    public String getDescription() {
        return description;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public Integer getGravite() {
        return gravite;
    }

    public Integer getUrgence() {
        return urgence;
    }
}
