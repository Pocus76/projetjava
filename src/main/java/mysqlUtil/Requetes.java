package mysqlUtil;

import objets.Autorisation;
import util.LogUtils;
import util.Regex;

import javax.swing.*;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//----------------------------------------------------------------------------------------------------------------------
/**
 * @author : Pocus
 * @dateCreation : 16/04/2019
 * @description : Classe qui contient pas mal de méthodes utiles pour récupérer les données de la BDD
 */
//----------------------------------------------------------------------------------------------------------------------
public class Requetes
{
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public static Autorisation getAutorisation(BigInteger personne_id)
    {
        Autorisation autorisation = null;
        try
        {
            Statement statement = SqlConnexion.connection.createStatement();
            String sql = "select * from autorisations_personnes where PERSONNE_ID='"+personne_id+"'";
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next())
            {
                String query = "select * from autorisations where AUTORISATION_ID='"+rs.getInt("AUTORISATION_ID")+"'";
                ResultSet rs2 = statement.executeQuery(query);
                if (rs2.next())
                {
                    autorisation = new Autorisation(rs2.getInt("AUTORISATION_ID"), rs2.getString("LIBELLE"));
                }
                else
                {
                    System.out.println("totototo");
                    JOptionPane.showMessageDialog(new JFrame(),"Une erreur s'est produite", "Erreur de communication", JOptionPane.ERROR_MESSAGE);
                }
                rs2.close();
            }
            else
            {
                JOptionPane.showMessageDialog(new JFrame(),"Une erreur s'est produite", "Erreur de communication", JOptionPane.ERROR_MESSAGE);
            }
            rs.close();
        }
        catch (SQLException err)
        {
            LogUtils.logErreur("Requetes", err.getMessage());
            JOptionPane.showMessageDialog(new JFrame(),"Une erreur s'est produite", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        return autorisation;
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public static ArrayList<Autorisation> getAutorisations()
    {
        ArrayList<Autorisation> autorisations = new ArrayList<>();
        try
        {
            Statement statement2 = SqlConnexion.connection.createStatement();
            String query = "select * from autorisations";
            ResultSet rs2 = statement2.executeQuery(query);
            while (rs2.next())
            {
                autorisations.add(new Autorisation(rs2.getInt("AUTORISATION_ID"), rs2.getString("LIBELLE")));
            }
            rs2.close();
        }
        catch (SQLException err)
        {
            LogUtils.logErreur("Requetes", err.getMessage());
            JOptionPane.showMessageDialog(new JFrame(),"Une erreur s'est produite", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        return autorisations;
    }

    public static List<String> getAdressesMail()
    {
        List<String> listeMails = new ArrayList<>();
        try
        {
            Statement statement = SqlConnexion.connection.createStatement();
            String query = "select EMAIL from administratifs";
            ResultSet rs = statement.executeQuery(query);
            while (rs.next())
            {
                if (rs.getString("EMAIL").matches(Regex.EMAIL_PATTERN))
                {
                    listeMails.add(rs.getString("EMAIL"));
                }
            }
            rs.close();
        }
        catch (SQLException err)
        {
            LogUtils.logErreur("Requetes", err.getMessage());
            JOptionPane.showMessageDialog(new JFrame(),"Une erreur s'est produite", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        return listeMails;
    }
}
