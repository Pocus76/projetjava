import fenetres.Authentification;
import mysqlUtil.SqlConnexion;

//----------------------------------------------------------------------------------------------------------------------
/**
 * @author :
 * @dateCreation :
 * @description :
 */
//----------------------------------------------------------------------------------------------------------------------
public class ActivitePrincipale
{
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public static void main(String[] args)
    {
        SqlConnexion connexion = new SqlConnexion();
        new Authentification();
    }
}