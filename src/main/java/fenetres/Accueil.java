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

public class Accueil extends JFrame {
    private JButton compte;
    private JButton incident;
    private JButton SuperCivil;
    private JButton mission;
    private JButton rapport;
    private JButton grilleIncidents;
    private JButton btnCrise;

    public Accueil() {

        super();
        this.setTitle("The S.H.I.E.L.D.");
        int x = 20;

        Container contenu = this.getContentPane();
        this.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, new Color(0, 58, 153)));
        contenu.setBackground(Color.white);
        contenu.setLayout(null);

        Autorisation autorisation = Requetes.getAutorisation(Constants.utilisateurConnecte.getPersonne_id());

        incident = new JButton("Nouvel incident");
        contenu.add(incident);
        incident.setBounds(x, 110, 150, 20);
        incident.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Incident();
            }
        });

        x += 150 + 10;


        SuperCivil = new JButton("Ajouter un super civil");
        SuperCivil.setBounds(x, 110, 200, 20);
        if (autorisation.getAutorisation_id() < 4) {
            contenu.add(SuperCivil);
            x += 200 + 10;
        }
        SuperCivil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AjouteVilain();
            }
        });

        rapport = new JButton("Rapport");
        rapport.setBounds(x, 110, 80, 20);
        if (autorisation.getAutorisation_id() < 4) {
            contenu.add(rapport);
            x += 80 + 10;
        }
        rapport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Rapport();
            }
        });

        grilleIncidents = new JButton("Liste des incidents");
        grilleIncidents.setBounds(x, 110, 150, 20);
        if (autorisation.getAutorisation_id() < 4) {
            contenu.add(grilleIncidents);
            x += 150 + 10;
        }
        grilleIncidents.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GrilleIncidents();
            }
        });

        JButton listePersonnes = new JButton("Liste des personnes");
        listePersonnes.setBounds(x, 110, 150, 20);
        if (autorisation.getAutorisation_id() == 1) {
            contenu.add(listePersonnes);
            x += 150 + 10;
        }
        listePersonnes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GrillePersonnes();
            }
        });

        JButton listeMissions = new JButton("Liste des missions");
        listeMissions.setBounds(x, 110, 150, 20);
        if (autorisation.getAutorisation_id() < 2) {
            contenu.add(listeMissions);
            x += 150 + 10;
        }
        listeMissions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GrilleMissions();
            }
        });

        btnCrise = new JButton("Déclarer une crise");
        btnCrise.setBounds(x, 110, 150, 20);
        if (autorisation.getAutorisation_id() < 2) {
            contenu.add(btnCrise);
            x += 150 + 10;
        }
        btnCrise.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DeclarerCrise();
            }
        });

        JButton gererEntreprise = new JButton("Gérer les membres de mon organisation");
        System.out.println(Constants.utilisateurConnecte.getCivil());
        gererEntreprise.setBounds(x, 110, 300, 20);
        if (!Constants.utilisateurConnecte.getCivil()) {
            contenu.add(gererEntreprise);
            x += 300 + 10;
        }
        gererEntreprise.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GereMembres(Constants.utilisateurConnecte.getPersonne_id());
            }
        });

        JButton deconnexion = new JButton("Deconnexion");
        contenu.add(deconnexion);
        deconnexion.setBounds(20, 150, 150, 20);
        deconnexion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Constants.utilisateurConnecte = null;
                Accueil.this.dispatchEvent(new WindowEvent(Accueil.this, WindowEvent.WINDOW_CLOSING));
                new Authentification();
            }
        });

        this.setSize(new Dimension(x + 10, 200));
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setUndecorated(true);
        this.setVisible(true);

    }
}


