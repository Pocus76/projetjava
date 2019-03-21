package objets;

import java.util.Date;

//----------------------------------------------------------------------------------------------------------------------
/**
 *
 * @author : Pocus
 * @dateCreation : 21/03/2019
 * @description : Classe qui définit un civil
 *
 */
//----------------------------------------------------------------------------------------------------------------------
public class Civil
{
    private String id;
    private String prenom;
    private String civilite;
    private Date dateNaissance;
    private Date dateDeces;
    private String nationalite;
    private Adresse adresse;
    private String email;
    private String mdp;

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public Civil(String id, String prenom, String civilite, Date dateNaissance, Date dateDeces, String nationalite, Adresse adresse, String email, String mdp)
    {
        this.id = id;
        this.prenom = prenom;
        this.civilite = civilite;
        this.dateNaissance = dateNaissance;
        this.dateDeces = dateDeces;
        this.nationalite = nationalite;
        this.adresse = adresse;
        this.email = email;
        this.mdp = mdp;
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public String getId()
    {
        return id;
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public String getPrenom()
    {
        return prenom;
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public String getCivilite()
    {
        return civilite;
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public Date getDateNaissance()
    {
        return dateNaissance;
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public Date getDateDeces()
    {
        return dateDeces;
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public String getNationalite()
    {
        return nationalite;
    }

    public Adresse getAdresse() { return  adresse; }

    public String getMdp() {
        return mdp;
    }

    public String getEmail() {
        return email;
    }
}
