package objets;

import mysqlUtil.SqlConnexion;
import util.LogUtils;

import javax.swing.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
                   Date dateFin, int gravite, int urgence) {
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

    public Mission(Incident incident, List<SuperCivil> heros,
                   String nature, String titre,
                   String description, Date dateDebut,
                   Date dateFin, int gravite, int urgence) {
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

    public Mission(Incident incident, List<SuperCivil> heros,
                   String nature, String titre,
                   String description, Date dateDebut, int gravite, int urgence) {
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

    public Mission(BigInteger mission_id, Incident incident, String nature, String titre, String description, Date dateDebut, Date dateFin, int gravite, int urgence) {
        this.mission_id = mission_id;
        this.incident = incident;
        this.nature = nature;
        this.titre = titre;
        this.description = description;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.gravite = gravite;
        this.urgence = urgence;
    }

    public Mission (BigInteger mission_id, String titre) {
        this.mission_id = mission_id;
        this.titre = titre;
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

    public Boolean insertIntoDatabase() {
        boolean inserted = false;

        String query = "INSERT INTO missions (INCIDENT_ID, NATURE, TITRE, DESCRIPTION, DATE_DEBUT, GRAVITE, URGENCE) VALUES (?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement stmt = null;

        try {
            stmt = SqlConnexion.connection.prepareStatement(query);
            stmt.setBigDecimal(1, new BigDecimal(incident.getIncident_id()));
            stmt.setString(2, nature);
            stmt.setString(3, titre);
            stmt.setString(4, description);
            stmt.setDate(5, new java.sql.Date(dateDebut.getTime()));
            stmt.setInt(6, gravite);
            stmt.setInt(7, urgence);
            stmt.execute();
            inserted = true;
        } catch (SQLException e) {
            LogUtils.logErreur(this.getClass().getSimpleName(), e.getMessage());
            JOptionPane.showMessageDialog(null, "Une erreur est survenue", "Erreur", JOptionPane.ERROR_MESSAGE);
        }

        return inserted;
    }

    @Override
    public String toString() {
        return titre;
    }
}
