package objets;

import mysqlUtil.SqlConnexion;
import util.Constants;
import util.LogUtils;

import javax.swing.*;
import mysqlUtil.SqlConnexion;
import util.LogUtils;

import javax.swing.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class Personne {
    private BigInteger personne_id;
    private Administratif administratif;
    private List<Personne> membres;
    private String prenom;
    private String nom;
    private Date dateNaissance;
    private Date dateDeces;
    private String nationalite;
    private String login;
    private String mdp;
    private String commentaire;
    private Date creation_date;
    private Date modification_date;
    private Integer nbIncidentsDeclares;
    private Integer nbIncidentsLies;
    private Boolean isCivil;

    public Personne(BigInteger personne_id, Administratif administratif, List<Personne> membres, String prenom, String nom, Date dateNaissance, Date dateDeces, String nationalite, String login, String mdp, String commentaire, Date creation_date, Date modification_date, Integer nbIncidentsDeclares, Integer nbIncidentsLies, Boolean isCivil) {
        this.personne_id = personne_id;
        this.administratif = administratif;
        this.membres = membres;
        this.prenom = prenom;
        this.nom = nom;
        this.dateNaissance = dateNaissance;
        this.dateDeces = dateDeces;
        this.nationalite = nationalite;
        this.login = login;
        this.mdp = mdp;
        this.commentaire = commentaire;
        this.creation_date = creation_date;
        this.modification_date = modification_date;
        this.nbIncidentsDeclares = nbIncidentsDeclares;
        this.nbIncidentsLies = nbIncidentsLies;
        this.isCivil = isCivil;
    }

    public Personne (BigInteger personne_id, String nom, String prenom, Boolean isCivil) {
        this.personne_id = personne_id;
        this.nom = nom;
        this.prenom = prenom;
        this.isCivil = isCivil;
    }

    public Personne(Administratif administratif, String prenom, String nom, Date dateNaissance, String nationalite, String login, String mdp, Date creation_date, Date modification_date, Boolean isCivil)
    {
        this.administratif = administratif;
        this.prenom = prenom;
        this.nom = nom;
        this.dateNaissance = dateNaissance;
        this.nationalite = nationalite;
        this.login = login;
        this.mdp = mdp;
        this.creation_date = creation_date;
        this.modification_date = modification_date;
        this.isCivil = isCivil;
    }

    public Personne(int id, String prenom, String nom)
    {
        this.personne_id = BigInteger.valueOf(id);
        this.prenom = prenom;
        this.nom = nom;
    }

    public Personne(BigInteger id, String nom, String prenom, String nationalite, int nbIncidentsDeclares, int nbIncidentsLies, Date creation_date, boolean isCivil, String commentaire) {
        this.personne_id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.nationalite = nationalite;
        this.nbIncidentsLies = nbIncidentsLies;
        this.nbIncidentsDeclares = nbIncidentsDeclares;
        this.commentaire = commentaire;
        this.creation_date = creation_date;
        this.isCivil = isCivil;
    }

    public BigInteger getPersonne_id() {
        return personne_id;
    }

    public Administratif getAdministratif() {
        return administratif;
    }

    public List<Personne> getMembres() {
        return membres;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getNom() {
        return nom;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public Date getDateDeces() {
        return dateDeces;
    }

    public String getNationalite() {
        return nationalite;
    }

    public String getLogin() {
        return login;
    }

    public String getMdp() {
        return mdp;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public Date getCreation_date() {
        return creation_date;
    }

    public Date getModification_date() {
        return modification_date;
    }

    public Integer getNbIncidentsDeclares() {
        return nbIncidentsDeclares;
    }

    public Integer getNbIncidentsLies() {
        return nbIncidentsLies;
    }

    public Boolean getCivil() {
        return isCivil;
    }

    public Autorisation getAutorisation()
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
                if (rs.next())
                {
                    autorisation = new Autorisation(rs2.getInt("AUTORISATION_ID"), rs2.getString("LIBELLE"));
                }
                else
                {
                    JOptionPane.showMessageDialog(new JFrame(),"Une erreur s'est produite", "Erreur de communication", JOptionPane.ERROR_MESSAGE);
                }
            }
            else
            {
                JOptionPane.showMessageDialog(new JFrame(),"Une erreur s'est produite", "Erreur de communication", JOptionPane.ERROR_MESSAGE);
            }
        }
        catch (SQLException err)
        {
            LogUtils.logErreur(this.getClass().getSimpleName(), err.getMessage());
            JOptionPane.showMessageDialog(new JFrame(),"Une erreur s'est produite", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        return autorisation;
    }

    public Boolean supprimer() {
        Boolean supprime = false;
        String query = "DELETE FROM PERSONNES WHERE PERSONNE_ID = ?;";
        PreparedStatement stmt = null;

        try {
            stmt = SqlConnexion.connection.prepareStatement(query);
            stmt.setBigDecimal(1, new BigDecimal(personne_id));
            stmt.execute();
            supprime = true;
        } catch (SQLException e) {
            LogUtils.logErreur(this.getClass().getSimpleName(), e.getMessage());
            JOptionPane.showMessageDialog(null, "Une erreur est survenue", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        return supprime;
    }
}
