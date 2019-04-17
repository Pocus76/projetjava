package objets;

import mysqlUtil.SqlConnexion;
import util.LogUtils;

import javax.swing.*;
import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Administratif {
    private BigInteger administratif_id;
    private String adresse;
    private String cp;
    private String ville;
    private String pays;
    private String planete;
    private String telephone;
    private String email;

    public Administratif(String adresse, String cp, String ville, String pays, String planete) {
        this.adresse = adresse;
        this.cp = cp;
        this.ville = ville;
        this.pays = pays;
        this.planete = planete;
    }

    public Administratif(BigInteger administratif_id, String adresse, String cp, String ville, String pays, String planete) {
        this.administratif_id = administratif_id;
        this.adresse = adresse;
        this.cp = cp;
        this.ville = ville;
        this.pays = pays;
        this.planete = planete;
    }

    public Administratif(BigInteger administratif_id, String adresse, String cp, String ville, String pays, String planete, String email, String telephone) {
        this.administratif_id = administratif_id;
        this.adresse = adresse;
        this.cp = cp;
        this.ville = ville;
        this.pays = pays;
        this.planete = planete;
        this.telephone = telephone;
        this.email = email;
    }

    public Administratif(String adresse, String cp, String ville, String pays, String planete, String email, String telephone)
    {
        this.adresse = adresse;
        this.cp = cp;
        this.ville = ville;
        this.pays = pays;
        this.planete = planete;
        this.telephone = telephone;
        this.email = email;
    }

    public BigInteger getAdministratif_id() { return administratif_id; }

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

    public String getPlanete() {
        return planete;
    }

    public String getTelephone() { return  telephone; }

    public String getEmail() { return email; }

    public Boolean insertIntoDatabase() {
        boolean inserted = false;
        String query = "INSERT INTO ADMINISTRATIFS (RUE, CP, VILLE, PAYS, PLANETE, TELEPHONE, EMAIL) VALUES (?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement stmt = null;

        try {
            stmt = SqlConnexion.connection.prepareStatement(query);
            stmt.setString(1, adresse);
            stmt.setString(2, cp);
            stmt.setString(3, ville);
            stmt.setString(4, planete);
            stmt.setString(5, pays);
            stmt.setString(6, telephone);
            stmt.setString(7, email);
            stmt.execute();

            Statement stmtId = SqlConnexion.connection.createStatement();
            String queryId = "SELECT LAST_INSERT_ID() AS ADMINISTRATIF_ID";
            ResultSet rs = stmtId.executeQuery(queryId);
            rs.next();
            administratif_id = BigInteger.valueOf(rs.getInt("ADMINISTRATIF_ID"));
            inserted = true;
        } catch (SQLException e) {
            LogUtils.logErreur(this.getClass().getSimpleName(), e.getMessage());
            JOptionPane.showMessageDialog(null, "Une erreur est survenue", "Erreur", JOptionPane.ERROR_MESSAGE);
        }

        return  inserted;
    }
}
