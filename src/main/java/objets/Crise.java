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

public class Crise
{
    private BigInteger crise_id;
    private Mission mission;
    private String description;
    private List<Personne> personnes;

    public Crise(BigInteger crise_id, Mission mission, String description, List<Personne> personnes) {
        this.crise_id = crise_id;
        this.mission = mission;
        this.description = description;
        this.personnes = personnes;
    }

    public Crise(Mission mission, String description, List<Personne> personnes)
    {
        this.mission = mission;
        this.description = description;
        this.personnes = personnes;
    }

    public BigInteger getCrise_id() {
        return crise_id;
    }

    public Mission getMission() {
        return mission;
    }

    public String getDescription() {
        return description;
    }

    public List<Personne> getPersonnes() {
        return personnes;
    }

    public boolean insertIntoDatabase()
    {
        boolean insertionOk = false;
        try
        {
            String query = "INSERT INTO crises (MISSION_ID, DESCRIPTION) VALUES (?, ?);";
            PreparedStatement stmt = SqlConnexion.connection.prepareStatement(query);
            BigDecimal missionId = null;
            if (getMission()!=null)
            {
                missionId = new BigDecimal(getMission().getMission_id());
            }
            stmt.setBigDecimal(1, missionId);
            stmt.setString(2, description);
            stmt.execute();

            if (personnes.size()>0)
            {
                Statement stmtId = SqlConnexion.connection.createStatement();
                String queryId = "SELECT LAST_INSERT_ID() AS CRISE_ID";
                ResultSet rs = stmtId.executeQuery(queryId);
                rs.next();
                int criseId = rs.getInt("CRISE_ID");

                for (Personne personne : personnes)
                {
                    String query2 = "INSERT INTO crises_personnes (CRISE_ID, PERSONNE_ID) VALUES (?, ?);";
                    PreparedStatement stmt2 = SqlConnexion.connection.prepareStatement(query2);
                    stmt2.setBigDecimal(1, new BigDecimal(criseId));
                    stmt2.setBigDecimal(2, new BigDecimal(personne.getPersonne_id()));
                    stmt2.execute();
                }
            }

            insertionOk = true;
        }
        catch (SQLException e)
        {
            LogUtils.logErreur(this.getClass().getSimpleName(), e.getMessage());
            JOptionPane.showMessageDialog(null, "Une erreur est survenue", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        return insertionOk;
    }
}
