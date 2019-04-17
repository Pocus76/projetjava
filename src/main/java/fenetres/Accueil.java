package fenetres;

import mysqlUtil.Requetes;
import objets.Autorisation;
import objets.SuperCivil;
import util.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

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
        this.setSize(new Dimension(1000,200));
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setUndecorated(true);

        Autorisation autorisation = Requetes.getAutorisation(Constants.utilisateurConnecte.getPersonne_id());

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
        if (autorisation.getAutorisation_id()<4) contenu.add(SuperCivil);
        SuperCivil.setBounds(180,110 ,200 ,20 );
        SuperCivil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AjouteVilain();
            }
        });

        rapport = new JButton("Rapport");
        if (autorisation.getAutorisation_id()<4) contenu.add(rapport);
        rapport.setBounds(390,110 ,80 ,20 );
        rapport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //new Rapport();
            }
        });

        grilleIncidents = new JButton("Liste des incidents");
        if (autorisation.getAutorisation_id()<4) contenu.add(grilleIncidents);
        grilleIncidents.setBounds(480, 110, 150, 20);
        grilleIncidents.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GrilleIncidents();
            }
        });

        JButton listePersonnes = new JButton("Liste des personnes");
        if (autorisation.getAutorisation_id()==1) contenu.add(listePersonnes);
        listePersonnes.setBounds(640, 110, 150, 20);
        listePersonnes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GrillePersonnes();
            }
        });

        JButton listeMissions = new JButton("Liste des missions");
        if (autorisation.getAutorisation_id() < 2) contenu.add(listeMissions);
        listeMissions.setBounds(800, 110, 150, 20);
        listeMissions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GrilleMissions();
            }
        });

        JButton deconnexion = new JButton("Deconnexion");
        contenu.add(deconnexion);
        deconnexion.setBounds(400, 150, 150, 20);
        deconnexion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Constants.utilisateurConnecte = null;
                Accueil.this.dispatchEvent(new WindowEvent(Accueil.this, WindowEvent.WINDOW_CLOSING));
                new Authentification();
            }
        });

        this.setVisible(true);

    }
}


