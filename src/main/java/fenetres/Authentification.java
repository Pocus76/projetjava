package fenetres;

import mysqlUtil.SqlConnexion;
import objets.Personne;
import util.CloseListener;
import util.Constants;
import util.LogUtils;
import util.PasswordUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//----------------------------------------------------------------------------------------------------------------------
/**
 * @author : Pocus
 * @dateCreation : 21/03/2019
 * @description : Classe qui gère l'écran d'authentification
 */
//----------------------------------------------------------------------------------------------------------------------
public class Authentification extends JFrame
{
    private JLabel login, mdp;
    private JTextField login1;
    private JPasswordField mdp1;
    private JButton inscription, valider, annuler;

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public Authentification()
    {
        super();
        this.setSize(new Dimension(320,150));
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setUndecorated(true);

        Container contenu = this.getContentPane();
        this.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, new Color(153, 86, 0)));
        contenu.setBackground(Color.white);
        contenu.setLayout(null);

        JLabel titre = new JLabel("The S.H.I.E.L.D.", SwingConstants.CENTER);
        contenu.add(titre);
        titre.setFont(new Font(titre.getFont().getName(), Font.BOLD, 15));
        titre.setBounds(90, 10, 140, 20);

        login = new JLabel("Identifiant");
        contenu.add(login);
        login.setBounds(20, 40, 100, 20);

        login1 = new JTextField();
        contenu.add(login1);
        login1.setBounds(150, 40, 150, 20);

        mdp = new JLabel("Mot de Passe");
        contenu.add(mdp);
        mdp.setBounds(20, 75, 100, 20);

        mdp1 = new JPasswordField();
        contenu.add(mdp1);
        mdp1.setBounds(150, 75, 150, 20);

        valider = new JButton("Valider");
        contenu.add(valider);
        valider.setBounds(20,110 ,80 ,20 );
        valider.addActionListener(new ValiderListener());

        inscription = new JButton("S'inscrire ");
        contenu.add(inscription);
        inscription.setBounds(110,110 ,100 ,20 );
        inscription.addActionListener(new InscriptionListener());

        annuler = new JButton("Fermer");
        contenu.add(annuler);
        annuler.setBounds(220, 110, 85, 20);
        annuler.addActionListener(new CloseListener());

        this.getRootPane().setDefaultButton(valider);

        this.setVisible(true);
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    private class ValiderListener implements ActionListener
    {
        //--------------------------------------------------------------------------------------------------------------
        //--------------------------------------------------------------------------------------------------------------
        public void actionPerformed(ActionEvent e)
        {
            String sLogin = login1.getText();
            char[] sMdp = mdp1.getPassword();

            if (!sLogin.equals("")&&sMdp.length!=0)
            {
                // --- On encrypte le mot de passe
                String encrypteMdp = PasswordUtils.generateSecurePassword(new String(sMdp));
                if (this.login(sLogin, encrypteMdp))
                {
                    JOptionPane.showMessageDialog(new JFrame(),"Connexion réussie", "Information", JOptionPane.INFORMATION_MESSAGE);
                    Authentification.this.dispatchEvent(new WindowEvent(Authentification.this, WindowEvent.WINDOW_CLOSING));
                    new GereAutorisations(Constants.utilisateurConnecte.getPersonne_id());
                }
            }
            else
            {
                JOptionPane.showMessageDialog(new JFrame(),"Veuillez remplir tous les champs", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }

        //--------------------------------------------------------------------------------------------------------------
        //--------------------------------------------------------------------------------------------------------------
        private boolean login(String login, String mdp)
        {
            boolean connexionReussie = false;
            try
            {
                if (login != null && mdp != null)
                {
                    Statement statement = SqlConnexion.connection.createStatement();
                    String sql = "select * from personnes where login='"+login+"' and mdp='"+mdp+"'";
                    ResultSet rs = statement.executeQuery(sql);
                    if (rs.next())
                    {
                        Constants.utilisateurConnecte = new Personne(BigInteger.valueOf(rs.getInt("PERSONNE_ID")), rs.getString("NOM"), rs.getString("PRENOM"), rs.getBoolean("IS_CIVIL"));
                        connexionReussie = true;
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(new JFrame(),"Identifiant ou mot de passe incorrect", "Erreur d'authentification", JOptionPane.ERROR_MESSAGE);
                        connexionReussie = false;
                    }
                }
            }
            catch (SQLException err)
            {
                LogUtils.logErreur(this.getClass().getSimpleName(), err.getMessage());
                JOptionPane.showMessageDialog(new JFrame(),"Une erreur s'est produite", "Erreur", JOptionPane.ERROR_MESSAGE);
                connexionReussie = false;
            }
            return connexionReussie;
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    private class InscriptionListener implements ActionListener
    {
        //--------------------------------------------------------------------------------------------------------------
        //--------------------------------------------------------------------------------------------------------------
        public void actionPerformed(ActionEvent e)
        {
            new Inscription();
        }
    }
}