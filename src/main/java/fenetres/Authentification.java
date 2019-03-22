package fenetres;

import mysqlUtil.SqlConnexion;
import util.LogUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        this.setTitle("The S.H.I.E.L.D.");
        this.setSize(new Dimension(400,200));
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setUndecorated(true);

        Container contenu = this.getContentPane();
        contenu.setLayout(null);

        login = new JLabel("Identifiant");
        contenu.add(login);
        login.setBounds(20, 20, 100, 20);

        login1 = new JTextField();
        contenu.add(login1);
        login1.setBounds(150, 20, 150, 20);

        mdp = new JLabel("Mot de Passe");
        contenu.add(mdp);
        mdp.setBounds(22, 55, 100, 20);

        mdp1 = new JPasswordField();
        contenu.add(mdp1);
        mdp1.setBounds(150, 55, 150, 20);

        inscription = new JButton("S'inscrire ");
        contenu.add(inscription);
        inscription.setBounds(25,100 ,100 ,20 );
        inscription.addActionListener(new InscriptionListener());

        valider = new JButton("Valider ");
        contenu.add(valider);
        valider.setBounds(145,100 ,80 ,20 );
        valider.addActionListener(new ValiderListener());

        annuler = new JButton(" Fermer");
        contenu.add(annuler);
        annuler.setBounds(245, 100, 85, 20);
        annuler.addActionListener(new CloseListener());

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
                this.login(sLogin, sMdp);
            }
            else
            {
                JOptionPane.showMessageDialog(new JFrame(),"Veuillez remplir tous les champs", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }

        //--------------------------------------------------------------------------------------------------------------
        //--------------------------------------------------------------------------------------------------------------
        private void login(String login, char[] mdp)
        {
            try
            {
                if (login != null && mdp != null)
                {
                    Statement statement = SqlConnexion.connection.createStatement();
                    String sql = "select * from users_table where username='"+login+"' and password='"+String.valueOf(mdp)+"'";
                    ResultSet rs = statement.executeQuery(sql);
                    if (rs.next())
                    {
                        // --- TODO : Gérer la connexion réussie
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(new JFrame(),"Identifiant ou mot de passe incorrect", "Erreur d'authentification", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
            catch (SQLException err)
            {
                LogUtils.logErreur(this.getClass().getSimpleName(), err.getMessage());
                JOptionPane.showMessageDialog(new JFrame(),"Une erreur s'est produite", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
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
            // --- TODO : Gerer l'inscription
            JOptionPane.showMessageDialog(new JFrame(),"Pas encore géré", "TODO", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}