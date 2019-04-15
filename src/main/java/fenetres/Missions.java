package fenetres;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Missions extends JFrame implements ActionListener
{

    private JLabel titre;
    private JButton mission;
    private JButton mission2;

    public Missions()
    {

        super();
        this.setTitle("Les missions");
        this.setSize(new Dimension(400,200));
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setUndecorated(true);
        Container contenu = this.getContentPane();
        contenu.setLayout(null);

        mission2 = new JButton("Mission2");
        mission = new JButton("Mission1");
        contenu.add(mission2);
        contenu.add(mission);
        mission2.setBounds(300,100 ,80 ,20 );
        mission.setBounds(150,100 ,80 ,20 );
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == mission)
        {
            new DescriptifMission();
            System.out.println("TEST");
            this.setVisible(true);
        }
    }
}


