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
import java.awt.geom.RoundRectangle2D;

public class Accueil extends JFrame{
    private JButton compte;
    private JButton incident;
    private JButton SuperCivil;
    private JButton mission;
    private JButton rapport;
    private JButton grilleIncidents;
    private JButton btnCrise;

    public Accueil(){

        super();
        this.setTitle("The S.H.I.E.L.D.");
        this.setSize(new Dimension(1150,200));
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setUndecorated(true);

        Container contenu = this.getContentPane();
        this.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, new Color(0, 58, 153)));
        contenu.setBackground(Color.white);
        contenu.setLayout(null);

        Autorisation autorisation = Requetes.getAutorisation(Constants.utilisateurConnecte.getPersonne_id());

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

        btnCrise = new JButton("Déclarer une crise");
        contenu.add(btnCrise);
        btnCrise.setBounds(960,110 ,150 ,20 );
        btnCrise.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DeclarerCrise();
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


