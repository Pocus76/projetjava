package fenetres;

import objets.SuperCivil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Accueil extends JFrame{
    private JButton compte;
    private JButton incident;
    private JButton SuperCivil;
    private JButton mission;
    private JButton rapport;

    public Accueil(){

        super();
        this.setTitle("The S.H.I.E.L.D.");
        this.setSize(new Dimension(400,200));
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setUndecorated(true);

        Container contenu = this.getContentPane();
        contenu.setLayout(null);

        incident = new JButton("Incident");
        contenu.add(incident);
        incident.setBounds(20,110 ,80 ,20 );
        incident.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              new Incident();
            }
        });

        SuperCivil = new JButton("SuperCivil");
        contenu.add(SuperCivil);
        SuperCivil.setBounds(110,110 ,80 ,20 );
        SuperCivil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AjouteVilain();
            }
        });

        rapport = new JButton("Rapport");
        contenu.add(rapport);
        rapport.setBounds(200,110 ,80 ,20 );
        rapport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //new Rapport();
            }
        });
        this.setVisible(true);

    }
}


