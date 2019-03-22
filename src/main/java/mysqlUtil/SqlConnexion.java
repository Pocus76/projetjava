package mysqlUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.TimeZone;

//----------------------------------------------------------------------------------------------------------------------
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
    private boolean connected;

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public SqlConnexion()
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(url ,user,pwd);
            connected = true;
        }
        catch (Exception e)
        {
            connected = false;
            e.printStackTrace();
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public boolean isConnected()
    {
        return connected;
    }
}
