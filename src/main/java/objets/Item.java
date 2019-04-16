package objets;
//----------------------------------------------------------------------------------------------------------------------

/**
 * @author : Pocus
 * @dateCreation : 15/04/2019
 * @description : Objet item, toujours utile
 */
//----------------------------------------------------------------------------------------------------------------------
public class Item
{
    String id;
    String libelle;

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public Item(String id, String libelle)
    {
        this.id = id;
        this.libelle = libelle;
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public String getId()
    {
        return id;
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public String getLibelle()
    {
        return libelle;
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public String toString()
    {
        return libelle;
    }
}