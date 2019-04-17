package fenetres;


import mysqlUtil.SqlConnexion;
import objets.Crise;
import objets.Mission;
import objets.Personne;
import objets.SuperCivil;
import util.LogUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class Rapport extends JFrame {

    private JLabel Description;
    private JTextArea desc;
    private JButton valider;
    private JButton annuler;
    private JLabel rapport1;
    private JCheckBox rapport;


    public Rapport() {
        super();
        this.setSize(new Dimension(500, 400));
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setUndecorated(true);
        this.setAlwaysOnTop(true);

        JScrollPane jScrollPane = new JScrollPane(new Panel());
        this.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, new Color(153, 86, 0)));
        jScrollPane.setBackground(Color.white);
        jScrollPane.setLayout(null);

        JLabel interlocuteur = new JLabel("Interlocuteur");
        jScrollPane.add(interlocuteur);
        interlocuteur.setBounds(20, 20, 100, 20);

        ArrayList<Personne> interlocuteurs = getPersonnes();
        JComboBox listePersonnes = new JComboBox(interlocuteurs.toArray());
        jScrollPane.add(listePersonnes);
        listePersonnes.setBounds(100, 20, 300, 20);

        JLabel missions = new JLabel("Mission");
        jScrollPane.add(missions);
        missions.setBounds(20, 50, 100, 20);

        ArrayList<Mission> listMission = getMissions();
        JComboBox listeMissions = new JComboBox(listMission.toArray());
        jScrollPane.add(listeMissions);
        listeMissions.setBounds(100, 50, 300, 20);
        listeMissions.setSelectedIndex(-1);

        JLabel crise = new JLabel("<html>Crises * <br />CTRL ou Shift <br />pour en sélectionner plusieurs</html>");
        jScrollPane.add(crise);
        crise.setBounds(20, 80, 90, 100);

        ArrayList<Crise> crises = getCrises();
        Crise[] data = new Crise[crises.size()];
        crises.toArray(data);
        JList<Crise> listeCrises = new JList<Crise>(data);
        listeCrises.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane scrollPane1 = new JScrollPane();
        scrollPane1.setViewportView(listeCrises);
        jScrollPane.add(scrollPane1);
        scrollPane1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane1.setBounds(110, 80, 300, 100);

        Description = new JLabel("Description");
        jScrollPane.add(Description);
        Description.setBounds(20, 190, 100, 20);


        desc = new JTextArea(10, 2);
        jScrollPane.add(desc);
        desc.setBackground(new java.awt.Color(232, 235, 239));
        desc.setBorder(BorderFactory.createLineBorder(Color.black));
        desc.setBounds(100, 190, 300, 100);

        rapport1 = new JLabel("Succès");
        jScrollPane.add(rapport1);
        rapport1.setBounds(20, 300, 100, 20);

        rapport = new JCheckBox();
        jScrollPane.add(rapport);
        rapport.setBounds(100, 300, 20, 20);

        valider = new JButton("Valider");
        jScrollPane.add(valider);
        valider.setBounds(50, 330, 100, 20);
        valider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                objets.Rapport objetRapport = new objets.Rapport((Mission)listeMissions.getSelectedItem(), listeCrises.getSelectedValuesList(), (Personne)listePersonnes.getSelectedItem(), desc.getText(), rapport.isSelected());
                objetRapport.inserer();
                Rapport.this.dispatchEvent(new WindowEvent(Rapport.this, WindowEvent.WINDOW_CLOSING));
            }
        });

        annuler = new JButton("Annuler");
        jScrollPane.add(annuler);
        annuler.setBounds(180, 330, 100, 20);
        annuler.addActionListener(new Rapport.AnnulationListener());


        this.getContentPane().add(jScrollPane);


        this.setVisible(true);
    }

    private ArrayList<Personne> getPersonnes() {
        ArrayList<Personne> personnes = new ArrayList<Personne>();
        try {
            Statement stmt = SqlConnexion.connection.createStatement();
            String query = "SELECT PERSONNE_ID, PRENOM, NOM FROM PERSONNES WHERE IS_CIVIL = 1;";
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                int personne_id = rs.getInt("PERSONNE_ID");
                String nom = rs.getString("NOM");
                String prenom = rs.getString("PRENOM");

                Personne personne = new Personne(personne_id, prenom, nom);
                personnes.add(personne);
            }
        } catch (SQLException e) {
            LogUtils.logErreur(this.getClass().getSimpleName(), e.getMessage());
            JOptionPane.showMessageDialog(null, "Une erreur est survenue", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        return personnes;
    }

    private ArrayList<Mission> getMissions() {
        ArrayList<Mission> missions = new ArrayList<Mission>();
        try {
            Statement stmt = SqlConnexion.connection.createStatement();
            String query = "SELECT MISSION_ID, TITRE FROM MISSIONS;";
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                BigInteger mission_id = BigInteger.valueOf(rs.getInt("MISSION_ID"));
                String titre = rs.getString("TITRE");

                Mission mission = new Mission(mission_id, titre);
                missions.add(mission);
            }
        } catch (SQLException e) {
            LogUtils.logErreur(this.getClass().getSimpleName(), e.getMessage());
            JOptionPane.showMessageDialog(null, "Une erreur est survenue", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        return missions;
    }

    private ArrayList<Crise> getCrises() {
        ArrayList<Crise> crises = new ArrayList<Crise>();
        try {
            Statement stmt = SqlConnexion.connection.createStatement();
            String query = "SELECT CRISE_ID, DESCRIPTION FROM CRISES;";
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                BigInteger crise_id = BigInteger.valueOf(rs.getInt("CRISE_ID"));
                String description = rs.getString("DESCRIPTION");

                Crise crise = new Crise(crise_id, description);
                crises.add(crise);
            }
        } catch (SQLException e) {
            LogUtils.logErreur(this.getClass().getSimpleName(), e.getMessage());
            JOptionPane.showMessageDialog(null, "Une erreur est survenue", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        return crises;
    }

    public class AnnulationListener implements ActionListener {
        //--------------------------------------------------------------------------------------------------------------
        //--------------------------------------------------------------------------------------------------------------
        public void actionPerformed(ActionEvent e) {
            Rapport.this.dispatchEvent(new WindowEvent(Rapport.this, WindowEvent.WINDOW_CLOSING));
        }
    }
}

