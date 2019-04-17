package fenetres;

import javax.swing.*;
import java.awt.*;

public class Crise extends JFrame{

    private JButton notification;
    private JTextArea description;
    private JTable tableau;

    public Crise(objets.Mission mission){

        super();
        this.setTitle("Déclaration d'une crise");
        this.setSize(new Dimension(400, 400));
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setUndecorated(true);

        Container contenu = this.getContentPane();
        contenu.setLayout(null);

        notification = new JButton("Envoyer une notification");
        contenu.add(notification);
        notification.setBounds(220, 110, 85, 20);
    }

}
