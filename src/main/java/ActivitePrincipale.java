import fenetres.AjouteVilain;
import fenetres.Authentification;
import mysqlUtil.SqlConnexion;

//----------------------------------------------------------------------------------------------------------------------
/**
 * @author : Pocus
 * @dateCreation : 21/03/2019
 * @description : Classe qui gère l'appli
 */
//----------------------------------------------------------------------------------------------------------------------
public class ActivitePrincipale
{
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public static void main(String[] args)
    {
        SqlConnexion connexion = new SqlConnexion();
        //new AjouteVilain();
        new Authentification();
    }
}
