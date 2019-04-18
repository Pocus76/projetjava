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
import java.util.List;

public class Rapport {
    private BigInteger rapport_id;
    private Mission mission;
    private List<Crise> crises;
    private Personne avenger;
    private Personne personne;
    private String description;
    private Boolean succes;

    public Rapport(BigInteger rapport_id, Mission mission, List<Crise> crises, Personne avenger, Personne personne, String description, Boolean succes) {
        this.rapport_id = rapport_id;
        this.mission = mission;
        this.crises = crises;
        this.avenger = avenger;
        this.personne = personne;
        this.description = description;
        this.succes = succes;
    }

    public Rapport(Mission mission, List<Crise> crises, Personne avenger, String description, Boolean succes) {
        this.mission = mission;
        this.crises = crises;
        this.avenger = avenger;
        this.description = description;
        this.succes = succes;
    }

    public Rapport(List<Crise> crises, Personne avenger, String description, Boolean succes) {
        this.crises = crises;
        this.avenger = avenger;
        this.description = description;
        this.succes = succes;
    }

    public Rapport(BigInteger rapport_id, String description, Boolean succes)
    {
        this.rapport_id = rapport_id;
        this.description = description;
        this.succes = succes;
    }

    public BigInteger getRapport_id() {
        return rapport_id;
    }

    public Mission getMission() {
        return mission;
    }

    public List<Crise> getCrises() {
        return crises;
    }

    public Personne getAvenger() {
        return avenger;
    }

    public Personne getPersonne() {
        return personne;
    }

    public String getDescription() {
        return description;
    }

    public Boolean getSucces() {
        return succes;
    }

    public void inserer() {
        String query = "INSERT INTO RAPPORTS (MISSION_ID, AVENGER_ID, DESCRIPTION, SUCCES) VALUES (?,?,?,?);";
        PreparedStatement stmt = null;

        try {
            stmt = SqlConnexion.connection.prepareStatement(query);
            stmt.setBigDecimal(1, mission == null ? null : new BigDecimal(mission.getMission_id()));
            stmt.setBigDecimal(2, new BigDecimal(avenger.getPersonne_id()));
            stmt.setString(3, description);
            stmt.setBoolean(4, succes);
            stmt.execute();

            Statement stmtId = SqlConnexion.connection.createStatement();
            String queryId = "SELECT LAST_INSERT_ID() AS RAPPORT_ID";
            ResultSet rs = stmtId.executeQuery(queryId);
            rs.next();
            rapport_id = BigInteger.valueOf(rs.getInt("RAPPORT_ID"));

            if (crises.size() > 0) {
                for (int i = 0; i < crises.size(); i++) {
                    Crise crise = crises.get(i);
                    String queryVilains = "INSERT INTO RAPPORTS_CRISES (RAPPORT_ID, CRISE_iD) VALUES (?, ?);";
                    PreparedStatement stmt2 = SqlConnexion.connection.prepareStatement(queryVilains);
                    stmt2.setBigDecimal(1, new BigDecimal(rapport_id));
                    stmt2.setBigDecimal(2, new BigDecimal(crise.getCrise_id()));
                    stmt2.execute();
                }
            }
        } catch (SQLException e) {
            LogUtils.logErreur(this.getClass().getSimpleName(), e.getMessage());
            JOptionPane.showMessageDialog(null, "Une erreur est survenue", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}
