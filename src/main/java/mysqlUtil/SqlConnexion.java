package mysqlUtil;
//----------------------------------------------------------------------------------------------------------------------

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.TimeZone;

/**
 * @author : Pocus
 * @dateCreation : 21/03/2019
 * @description : Classe qui gère la connection à la BDD
 */
//----------------------------------------------------------------------------------------------------------------------
public class SqlConnexion
{
    private String url = "jdbc:mysql://localhost:3306/avenger_bdd?serverTimezone="+TimeZone.getDefault().getID();
    private String user = "root";
    private String pwd = "";
    private Connection connection;

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public SqlConnexion()
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver O.K.");

            this.connection = DriverManager.getConnection(url ,user,pwd);
            System.out.println("Connexion effective !");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
