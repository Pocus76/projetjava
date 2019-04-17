package fenetres;

import mysqlUtil.SqlConnexion;
import objets.Administratif;
import objets.Personne;
import util.LogUtils;
import util.PasswordUtils;
import util.Regex;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//----------------------------------------------------------------------------------------------------------------------
/**
 * @author : Pocus
 * @dateCreation : 22/03/2019
 * @description : Classe pour la fenêtre d'inscription
 */
//----------------------------------------------------------------------------------------------------------------------
public class Inscription extends JFrame
{
    private JLabel prenom;
    private JTextField prenom1;
    private JLabel nom;
    private JTextField nom1;
    private JLabel dateNaissance;
    JFormattedTextField dateNaissance1;
    private JLabel nationalite;
    private JTextField nationalite1;
    private JLabel login;
    private JTextField login1;
    private JLabel mdp;
    private JPasswordField mdp1;
    private JLabel civilite;
    private JCheckBox civilite1;
    private JButton valider;
    private JButton annuler;
    private JLabel adresse;
    private JTextField adresse1;
    private JLabel cp;
    private JTextField cp1;
    private JLabel ville;
    private JTextField ville1;
    private JLabel pays;
    private JTextField pays1;
    private JLabel planete;
    private JTextField planete1;
    private JLabel telephone;
    private JTextField telephone1;
    private JLabel email;
    private JTextField email1;

    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public Inscription()
    {
        super();
        this.setSize(new Dimension(320,550));
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setUndecorated(true);
        this.setAlwaysOnTop(true);

        JScrollPane jScrollPane = new JScrollPane(new Panel());
        this.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, new Color(153, 86, 0)));
        jScrollPane.setBackground(Color.white);
        jScrollPane.setLayout(null);

        JLabel titre = new JLabel("Inscription", SwingConstants.CENTER);
        jScrollPane.add(titre);
        titre.setFont(new Font(titre.getFont().getName(), Font.BOLD, 15));
        titre.setBounds(90, 10, 140, 20);

        prenom = new JLabel("Prénom");
        jScrollPane.add(prenom);
        prenom.setBounds(20, 40, 100, 20);

        prenom1 = new JTextField();
        jScrollPane.add(prenom1);
        prenom1.setBounds(150, 40, 150, 20);

        nom = new JLabel("Nom");
        jScrollPane.add(nom);
        nom.setBounds(20, 70, 100, 20);

        nom1 = new JTextField();
        jScrollPane.add(nom1);
        nom1.setBounds(150, 70, 150, 20);

        dateNaissance = new JLabel("Date de naissance");
        jScrollPane.add(dateNaissance);
        dateNaissance.setBounds(20, 100, 100, 20);

        dateNaissance1 = new JFormattedTextField(df);
        jScrollPane.add(dateNaissance1);
        dateNaissance1.setBounds(150, 100, 150, 20);

        nationalite = new JLabel("Nationalité");
        jScrollPane.add(nationalite);
        nationalite.setBounds(20, 130, 100, 20);

        nationalite1 = new JTextField();
        jScrollPane.add(nationalite1);
        nationalite1.setBounds(150, 130, 150, 20);

        login = new JLabel("Identifiant");
        jScrollPane.add(login);
        login.setBounds(20, 160, 100, 20);

        login1 = new JTextField();
        jScrollPane.add(login1);
        login1.setBounds(150, 160, 150, 20);

        mdp = new JLabel("Mot de passe");
        jScrollPane.add(mdp);
        mdp.setBounds(20, 190, 100, 20);

        mdp1 = new JPasswordField();
        jScrollPane.add(mdp1);
        mdp1.setBounds(150, 190, 150, 20);

        JLabel titreAdresse = new JLabel("Adresse", SwingConstants.CENTER);
        jScrollPane.add(titreAdresse);
        titreAdresse.setFont(new Font(titreAdresse.getFont().getName(), Font.BOLD, 14));
        titreAdresse.setBounds(90, 220, 140, 20);

        civilite = new JLabel("Société");
        jScrollPane.add(civilite);
        civilite.setBounds(20, 250, 100, 20);

        civilite1 = new JCheckBox();
        jScrollPane.add(civilite1);
        civilite1.setBounds(150, 250, 20, 20);

        adresse = new JLabel("Voie");
        jScrollPane.add(adresse);
        adresse.setBounds(20, 280, 100, 20);

        adresse1 = new JTextField();
        jScrollPane.add(adresse1);
        adresse1.setBounds(150, 280, 150, 20);

        cp = new JLabel("Code postal");
        jScrollPane.add(cp);
        cp.setBounds(20, 310, 100, 20);

        NumberFormat format = NumberFormat.getInstance();
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(0);
        formatter.setMaximum(Integer.MAX_VALUE);
        formatter.setAllowsInvalid(false);
        // If you want the value to be committed on each keystroke instead of focus lost
        formatter.setCommitsOnValidEdit(true);
        cp1 = new JFormattedTextField(formatter);
        jScrollPane.add(cp1);
        cp1.setBounds(150, 310, 150, 20);

        ville = new JLabel("Ville");
        jScrollPane.add(ville);
        ville.setBounds(20, 340, 100, 20);

        ville1 = new JTextField();
        jScrollPane.add(ville1);
        ville1.setBounds(150, 340, 150, 20);

        pays = new JLabel("Pays");
        jScrollPane.add(pays);
        pays.setBounds(20, 370, 100, 20);

        pays1 = new JTextField();
        jScrollPane.add(pays1);
        pays1.setBounds(150, 370, 150, 20);

        planete = new JLabel("Planète");
        jScrollPane.add(planete);
        planete.setBounds(20, 400, 100, 20);

        planete1 = new JTextField();
        planete1.setText("Terre");
        planete1.setEditable(false);
        jScrollPane.add(planete1);
        planete1.setBounds(150, 400, 150, 20);

        JLabel titreContact = new JLabel("Contact", SwingConstants.CENTER);
        jScrollPane.add(titreContact);
        titreContact.setFont(new Font(titreContact.getFont().getName(), Font.BOLD, 14));
        titreContact.setBounds(90, 430, 140, 20);

        telephone = new JLabel("Téléphone");
        jScrollPane.add(telephone);
        telephone.setBounds(20, 460, 100, 20);

        telephone1 = new JFormattedTextField(formatter);
        jScrollPane.add(telephone1);
        telephone1.setBounds(150, 460, 150, 20);

        email = new JLabel("Email");
        jScrollPane.add(email);
        email.setBounds(20, 490, 100, 20);

        email1 = new JTextField();
        jScrollPane.add(email1);
        email1.setBounds(150, 490, 150, 20);

        valider = new JButton("Valider");
        jScrollPane.add(valider);
        valider.setBounds(110,520 ,100 ,20 );
        valider.addActionListener(new Inscription.ValidationListener());

        annuler = new JButton("Annuler");
        jScrollPane.add(annuler);
        annuler.setBounds(220, 520, 85, 20);
        annuler.addActionListener(new Inscription.AnnulationListener());

        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        // Then, add the jScrollPane to your frame
        this.getContentPane().add(jScrollPane);

        this.setVisible(true);
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public class ValidationListener implements ActionListener
    {
        //--------------------------------------------------------------------------------------------------------------
        //--------------------------------------------------------------------------------------------------------------
        public void actionPerformed(ActionEvent e)
        {
            String prenom = prenom1.getText();
            String nom = nom1.getText();
            String sDateNaissance = dateNaissance1.getText();
            Date dateNaissance = null;
            String nationalite = nationalite1.getText();
            String login = login1.getText();
            char[] mdp = mdp1.getPassword();
            Boolean isCivil = !civilite1.isSelected();
            String adresse = adresse1.getText();
            String cp = cp1.getText();
            String ville = ville1.getText();
            String pays = pays1.getText();
            String planete = planete1.getText();
            String telephone = telephone1.getText();
            String email = email1.getText();
            boolean identifiantExisteDeja = false;
            try
            {
                Statement statement = SqlConnexion.connection.createStatement();
                String sql = "select * from personnes where login='"+login+"'";
                ResultSet rs = statement.executeQuery(sql);
                identifiantExisteDeja = rs.next();
            }
            catch (SQLException e1)
            {
                LogUtils.logErreur(Inscription.this.getName(), e1.getMessage());
            }
            if (prenom.equals(""))
            {
                JOptionPane.showMessageDialog(Inscription.this,"Veuillez renseigner votre prénom", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
            else if (nom.equals(""))
            {
                JOptionPane.showMessageDialog(Inscription.this,"Veuillez renseigner votre nom", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
            else if (sDateNaissance.equals(""))
            {
                JOptionPane.showMessageDialog(Inscription.this,"Veuillez renseigner une date de naissance correcte", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
            else if (nationalite.equals(""))
            {
                JOptionPane.showMessageDialog(Inscription.this,"Veuillez renseigner votre nationalité", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
            else if (login.equals(""))
            {
                JOptionPane.showMessageDialog(Inscription.this,"Veuillez renseigner votre identifiant", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
            else if (identifiantExisteDeja)
            {
                JOptionPane.showMessageDialog(Inscription.this,"Cet identifiant existe déjà", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
            else if (new String(mdp).equals("")||new String(mdp).length()<8)
            {
                JOptionPane.showMessageDialog(Inscription.this,"Veuillez renseigner un mot de passe valide", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
            else if (email.length()!=0&&!email.matches(Regex.EMAIL_PATTERN))
            {
                JOptionPane.showMessageDialog(Inscription.this,"Veuillez renseigner un email valide", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                Administratif adrPersonne = new Administratif(adresse, cp, ville, pays, planete, telephone, email);
                try
                {
                    dateNaissance = df.parse(dateNaissance1.getText());
                }
                catch (ParseException e1)
                {
                    LogUtils.logErreur(this.getClass().getSimpleName(), e1.getMessage());
                }

                // --- On encrypte le mot de passe
                String encrypteMdp = PasswordUtils.generateSecurePassword(new String(mdp));

                Personne personne = new Personne(adrPersonne, prenom, nom, dateNaissance, nationalite, login, new String(encrypteMdp), new Date(), new Date(), isCivil);

                if (ajoutePersonneDansBDD(personne))
                {
                    JOptionPane.showMessageDialog(Inscription.this,"Vous pouvez à présent vous connecter avec vos identifiants", "Inscription réussie", JOptionPane.INFORMATION_MESSAGE);
                    Inscription.this.dispatchEvent(new WindowEvent(Inscription.this, WindowEvent.WINDOW_CLOSING));
                }
                else
                {
                    JOptionPane.showMessageDialog(Inscription.this,"Une erreur s'est produite", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

        //--------------------------------------------------------------------------------------------------------------
        //--------------------------------------------------------------------------------------------------------------
        private boolean ajoutePersonneDansBDD(Personne personne)
        {
            boolean inscriptionOk = false;
            int idAdministratif;
            String query = " insert into administratifs (RUE, CP, VILLE, PAYS, PLANETE, TELEPHONE, EMAIL)"
                    + " values (?, ?, ?, ?, ?, ?, ?)";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = null;
            try
            {
                Administratif administratif = personne.getAdministratif();
                preparedStmt = SqlConnexion.connection.prepareStatement(query);
                preparedStmt.setString(1, administratif.getAdresse());
                preparedStmt.setString(2, administratif.getCp());
                preparedStmt.setString(3, administratif.getVille());
                preparedStmt.setString(4, administratif.getPays());
                preparedStmt.setString(5, administratif.getPlanete());
                preparedStmt.setString(6, administratif.getTelephone());
                preparedStmt.setString(7, administratif.getEmail());

                // execute the preparedstatement
                preparedStmt.execute();

                Statement statement = SqlConnexion.connection.createStatement();
                String sql = "select LAST_INSERT_ID() as ADMINISTRATIF_ID";
                ResultSet rs = statement.executeQuery(sql);
                rs.next();
                idAdministratif = rs.getInt("ADMINISTRATIF_ID");

                String query2 = "insert into personnes (ADMINISTRATIF_ID, PRENOM, NOM, DATE_NAISSANCE, NATIONALITE, LOGIN, MDP, CREATION_DATE, MODIFICATION_DATE, IS_CIVIL)"
                        + "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                PreparedStatement preparedStmt2 = SqlConnexion.connection.prepareStatement(query2);
                preparedStmt2.setInt(1, idAdministratif);
                preparedStmt2.setString(2, personne.getPrenom());
                preparedStmt2.setString(3, personne.getNom());
                preparedStmt2.setDate(4, new java.sql.Date(personne.getDateNaissance().getTime()));
                preparedStmt2.setString(5, personne.getNationalite());
                preparedStmt2.setString(6, personne.getLogin());
                preparedStmt2.setString(7, personne.getMdp());
                preparedStmt2.setDate(8, new java.sql.Date(new Date().getTime()));
                preparedStmt2.setDate(9, new java.sql.Date(new Date().getTime()));
                preparedStmt2.setBoolean(10, personne.getCivil());

                // execute the preparedstatement
                preparedStmt2.execute();

                Statement statement2 = SqlConnexion.connection.createStatement();
                String sql2 = "select LAST_INSERT_ID() as PERSONNE_ID";
                ResultSet rs2 = statement2.executeQuery(sql2);
                rs2.next();
                int idPersonne = rs2.getInt("PERSONNE_ID");

                String query3 = "insert into autorisations_personnes (AUTORISATION_ID, PERSONNE_ID)"
                        + "values (?, ?)";

                PreparedStatement preparedStmt3 = SqlConnexion.connection.prepareStatement(query3);
                preparedStmt3.setInt(1, 4);
                preparedStmt3.setInt(2, idPersonne);
                // execute the preparedstatement
                preparedStmt3.execute();

                inscriptionOk = true;
            }
            catch (SQLException e)
            {
                inscriptionOk = false;
                LogUtils.logErreur(this.getClass().getSimpleName(), e.getMessage());
                JOptionPane.showMessageDialog(Inscription.this,"Une erreur s'est produite", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
            return inscriptionOk;
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public class AnnulationListener implements ActionListener
    {
        //--------------------------------------------------------------------------------------------------------------
        //--------------------------------------------------------------------------------------------------------------
        public void actionPerformed(ActionEvent e)
        {
            Inscription.this.dispatchEvent(new WindowEvent(Inscription.this, WindowEvent.WINDOW_CLOSING));
        }
    }
}
