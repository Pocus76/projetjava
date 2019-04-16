package objets;

import mysqlUtil.SqlConnexion;
import util.Constants;
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

public class Incident
{
    private BigInteger incident_id;
    private Personne personne;
    private Administratif administratif;
    private List<SuperCivil> vilains;
    private String commentaire;
    private Date creation_date;
    private Boolean sans_suite;

    public Incident(Administratif administratif, List<SuperCivil> vilains, String commentaire) {
        this.administratif = administratif;
        this.personne = Constants.utilisateurConnecte;
        this.vilains = vilains;
        this.commentaire = commentaire;
        this.creation_date = new Date();
        this.sans_suite = false;
    }

    public Incident(BigInteger incident_id, Personne personne, Administratif administratif, List<SuperCivil> vilains, String commentaire, Date creation_date, Boolean sans_suite)
    {
        this.incident_id = incident_id;
        this.personne = personne;
        this.administratif = administratif;
        this.vilains = vilains;
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

    public List<SuperCivil> getVilains() {
        return vilains;
    }

    public Boolean insertIntoDatabase() {
        boolean inserted = false;

        String query = "INSERT INTO INCIDENTS (ADMINISTRATIF_ID, PERSONNE_ID, COMMENTAIRE, CREATAION_DATE, SANS_SUITE) VALUES (?, ?, ?, ?, ?);";
        PreparedStatement stmt = null;

        try {
            stmt = SqlConnexion.connection.prepareStatement(query);
            stmt.setBigDecimal(1, new BigDecimal(administratif.getAdministratif_id()));
            stmt.setBigDecimal(2, new BigDecimal(personne.getPersonne_id()));
            stmt.setString(3, commentaire);
            stmt.setDate(4, new java.sql.Date(creation_date.getTime()));
            stmt.setBoolean(5, sans_suite);
            stmt.execute();

            Statement stmtId = SqlConnexion.connection.createStatement();
            String queryId = "SELECT LAST_INSERT_ID() AS INCIDENT_ID";
            ResultSet rs = stmtId.executeQuery(queryId);
            rs.next();
            incident_id = BigInteger.valueOf(rs.getInt("INCIDENT_ID"));

            if (vilains.size() > 0) {
                for (int i = 0; i < vilains.size(); i++) {
                    SuperCivil vilain = vilains.get(i);
                    String queryVilains = "INSERT INTO INCIDENTS_VILAINS (INCIDENT_ID, SUPER_CIVIL_ID) VALUES (?, ?);";
                    PreparedStatement stmt2 = SqlConnexion.connection.prepareStatement(queryVilains);
                    stmt2.setBigDecimal(1, new BigDecimal(administratif.getAdministratif_id()));
                    stmt2.setBigDecimal(2, new BigDecimal(vilain.getSuperCivil_id()));
                    stmt2.execute();
                }
            }

            inserted = true;
        } catch (SQLException e) {
            LogUtils.logErreur(this.getClass().getSimpleName(), e.getMessage());
            JOptionPane.showMessageDialog(null, "Une erreur est survenue", "Erreur", JOptionPane.ERROR_MESSAGE);
        }

        return inserted;
    }
}
