import fenetres.Authentification;
import fenetres.DescriptifMission;
import fenetres.Missions;
import mysqlUtil.SqlConnexion;

//----------------------------------------------------------------------------------------------------------------------
/**
 * @author : robin
 * @dateCreation : 22 Mars
 * @description : Choix des missions
 */
//----------------------------------------------------------------------------------------------------------------------
public class ActivitePrincipale
{
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public static void main(String[] args)
    {
        //SqlConnexion connexion = new SqlConnexion();
        //new Authentification();
        new Missions();
        new DescriptifMission();
    }
}
