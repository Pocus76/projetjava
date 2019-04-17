package objets;

import mysqlUtil.SqlConnexion;
import util.LogUtils;

import javax.swing.*;
import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Autorisation {

    private int autorisation_id;
    private String autorisation_libelle;

    public Autorisation(int autorisation_id, String autorisation_libelle){
        this.autorisation_id = autorisation_id;
        this.autorisation_libelle = autorisation_libelle;

    }

    public int getAutorisation_id(){ return autorisation_id;}
    public String getAutorisation_libelle() {
        return autorisation_libelle;
    }

    @Override
    public String toString() {
        return autorisation_libelle;
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public boolean updateAutorisation(BigInteger idPersonne)
    {
        boolean updateOk = false;
        try
        {
            String query = "update autorisations_personnes set AUTORISATION_ID = ? where PERSONNE_ID = ?";
            PreparedStatement preparedStmt = SqlConnexion.connection.prepareStatement(query);
            preparedStmt.setInt(1, autorisation_id);
            preparedStmt.setInt(2, idPersonne.intValue());

            // execute the java preparedstatement
            preparedStmt.executeUpdate();
            updateOk = true;
        }
        catch (SQLException err)
        {
            LogUtils.logErreur("Autorisation", err.getMessage());
            JOptionPane.showMessageDialog(new JFrame(),"Une erreur s'est produite", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        return updateOk;
    }
}
