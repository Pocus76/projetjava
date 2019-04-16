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
    private JButton grilleIncidents;

    public Accueil(){

        super();
        this.setTitle("The S.H.I.E.L.D.");
        this.setSize(new Dimension(700,200));
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setUndecorated(true);

        Container contenu = this.getContentPane();
        contenu.setLayout(null);

        incident = new JButton("Nouvel incident");
        contenu.add(incident);
        incident.setBounds(20,110 ,150 ,20 );
        incident.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              new Incident();
            }
        });

        SuperCivil = new JButton("Ajouter un super civil");
        contenu.add(SuperCivil);
        SuperCivil.setBounds(180,110 ,200 ,20 );
        SuperCivil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AjouteVilain();
            }
        });

        rapport = new JButton("Rapport");
        contenu.add(rapport);
        rapport.setBounds(390,110 ,80 ,20 );
        rapport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //new Rapport();
            }
        });

        grilleIncidents = new JButton("Liste des incidents");
        contenu.add(grilleIncidents);
        grilleIncidents.setBounds(480, 110, 150, 20);
        grilleIncidents.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GrilleIncidents();
            }
        });

        this.setVisible(true);

    }
}


