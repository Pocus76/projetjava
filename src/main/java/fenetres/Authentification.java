package fenetres;
//----------------------------------------------------------------------------------------------------------------------

import javax.swing.*;
import java.awt.*;

/**
 * @author :
 * @dateCreation :
 * @description :
 */
//----------------------------------------------------------------------------------------------------------------------
public class Authentification extends JFrame
{
    private JLabel login,mdp;
    private JTextField login1;
    private JPasswordField mdp1;
    private JButton valider,annuler;

    public Authentification(){

        super();
        this.setTitle("The S.H.I.E.L.D.");
        this.setSize(new Dimension(400,200));
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setUndecorated(true);

        login = new JLabel("Identifiant");
        login1 = new JTextField();

        mdp = new JLabel("Mot de Passe");
        mdp1 = new JPasswordField();

        valider = new JButton("Valider ");
        annuler = new JButton(" Annuler");

        Container contenu = this.getContentPane();
        contenu.setLayout(null);

        contenu.add(login);
        login.setBounds(20, 20, 100, 20);

        contenu.add(login1);
        login1.setBounds(150, 20, 150, 20);

        contenu.add(mdp);
        mdp.setBounds(22, 55, 100, 20);

        contenu.add(mdp1);
        mdp1.setBounds(150, 55, 150, 20);

        contenu.add(valider);
        valider.setBounds(125,100 ,77 ,20 );

        contenu.add(annuler);
        annuler.setBounds(225, 100, 82, 20);

        valider.addActionListener(null);
        annuler.addActionListener(new CloseListener());

        this.setVisible(true);

    }

}