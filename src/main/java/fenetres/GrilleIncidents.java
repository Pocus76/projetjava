package fenetres;

import mysqlUtil.SqlConnexion;
import objets.Administratif;
import objets.Incident;
import objets.Personne;
import objets.SuperCivil;
import util.LogUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.math.BigInteger;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.util.Date;

public class GrilleIncidents extends JFrame {
    ArrayList<Incident> incidents = new ArrayList<Incident>();

    public GrilleIncidents() {
        super();
        getIncidents();
        int gridx = 0;
        int gridy = 0;
        this.setSize(new Dimension(1300, 700));
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setUndecorated(true);
        Container contenu = this.getContentPane();
        this.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, new Color(153, 86, 10, 10)));
        contenu.setBackground(Color.white);
        contenu.setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        GridBagLayout layout = new GridBagLayout();
        panel.setLayout(layout);
        GridBagConstraints c = new GridBagConstraints();
        SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yy");
        JPanel bbar = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton retour = new JButton("Retour");
        bbar.add(retour);
        retour.setBounds(400 , 300, 100, 20);
        retour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GrilleIncidents.this.dispatchEvent(new WindowEvent(GrilleIncidents.this, WindowEvent.WINDOW_CLOSING));
            }
        });

        c.gridx = gridx;
        c.gridy = gridy;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.CENTER;
        c.weightx = 40;

        panel.add(new JLabel("Commentaire"), c);
        gridx += 1;

        c.gridx = gridx;
        c.gridy = gridy;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 5;
        panel.add(new JLabel("CP"), c);
        gridx += 1;

        c.gridx = gridx;
        c.gridy = gridy;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 10;
        panel.add(new JLabel("Ville"), c);
        gridx += 1;

        c.gridx = gridx;
        c.gridy = gridy;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 10;
        panel.add(new JLabel("Pays"), c);
        gridx += 1;

        c.gridx = gridx;
        c.gridy = gridy;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 10;
        panel.add(new JLabel("Nom / Prénom Contact"), c);
        gridx += 1;

        c.gridx = gridx;
        c.gridy = gridy;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 10;
        panel.add(new JLabel("Date"), c);
        gridx += 1;

        c.gridx = gridx;
        c.gridy = gridy;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 10;
        panel.add(new JLabel("Sans suite"), c);
        gridx += 1;

        c.gridx = gridx;
        c.gridy = gridy;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 10;

        panel.add(new JLabel("Voir les super vilains associés"), c);
        gridx += 1;

        c.gridx = gridx;
        c.gridy = gridy;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 10;
        panel.add(new JLabel("Déclarer sans suite"), c);
        gridx += 1;

        c.gridx = gridx;
        c.gridy = gridy;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 10;
        panel.add(new JLabel("Partir en mission"), c);

        gridx = 0;
        gridy = 1;

        c.gridx = gridx;
        c.gridy = gridy;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 40;

        for (int i = 0; i < incidents.size(); i += 1) {
            Incident incident = incidents.get(i);
            Administratif administratif = incident.getAdministratif();
            Personne personne = incident.getPersonne();
            panel.add(new JLabel(incident.getCommentaire()), c);
            gridx += 1;
            c.gridx = gridx;
            c.gridy = gridy;
            c.gridwidth = 1;
            c.gridheight = 1;
            c.weightx = 5;
            panel.add(new JLabel(administratif.getCp()), c);
            gridx += 1;
            c.gridx = gridx;
            c.gridy = gridy;
            c.gridwidth = 1;
            c.gridheight = 1;
            c.weightx = 10;
            panel.add(new JLabel(administratif.getVille()), c);
            gridx += 1;
            c.gridx = gridx;
            c.gridy = gridy;
            c.gridwidth = 1;
            c.gridheight = 1;
            c.weightx = 10;
            panel.add(new JLabel(administratif.getPays()), c);
            gridx += 1;
            c.gridx = gridx;
            c.gridy = gridy;
            c.gridwidth = 1;
            c.gridheight = 1;
            c.weightx = 10;
            panel.add(new JLabel(personne.getNom() + " " + personne.getPrenom()), c);
            gridx += 1;
            c.gridx = gridx;
            c.gridy = gridy;
            c.gridwidth = 1;
            c.gridheight = 1;
            c.weightx = 10;
            panel.add(new JLabel(formater.format(incident.getCreation_date())), c);
            gridx += 1;
            c.gridx = gridx;
            c.gridy = gridy;
            c.gridwidth = 1;
            c.gridheight = 1;
            c.weightx = 10;
            JLabel labelSansSuite = new JLabel(incident.getSans_suite() ? "Sans suite" : "En cours");
            panel.add(labelSansSuite, c);
            gridx += 1;

            c.gridx = gridx;
            c.gridy = gridy;
            c.gridwidth = 1;
            c.gridheight = 1;
            c.weightx = 10;
            JButton voirVillains = new JButton("X");
            panel.add(voirVillains, c);
            voirVillains.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                   VisualiserVilains vv = new VisualiserVilains(incident);
                }
            });
            gridx += 1;

            c.gridx = gridx;
            c.gridy = gridy;
            c.gridwidth = 1;
            c.gridheight = 1;
            c.weightx = 10;
            JButton sansSuite = new JButton("X");
            panel.add(sansSuite, c);
            sansSuite.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (incident.getSans_suite()) return;
                    incident.setSans_suite();
                    labelSansSuite.setText("Sans suite");
                }
            });
            gridx += 1;

            c.gridx = gridx;
            c.gridy = gridy;
            c.gridwidth = 1;
            c.gridheight = 1;
            c.weightx = 10;
            JButton mission = new JButton("X");
            panel.add(mission, c);
            mission.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (incident.getSans_suite()) return;
                    new Mission(incident);
                }
            });


            gridx = 0;
            gridy += 1;
            c.gridx = gridx;
            c.gridy = gridy;
            c.gridwidth = 1;
            c.gridheight = 1;
            c.weightx = 10;
        }

        contenu.add(panel, BorderLayout.PAGE_START);
        contenu.add(bbar, BorderLayout.SOUTH);
        this.setVisible(true);
    }

    private void getIncidents() {
        try {
            Statement stmt = SqlConnexion.connection.createStatement();
            String query = "SELECT INCIDENT_ID, I.PERSONNE_ID, P.PRENOM, P.NOM, P.IS_CIVIL, I.ADMINISTRATIF_ID, A.RUE, A.CP, A.VILLE, A.PAYS, A.PLANETE, I.COMMENTAIRE, I.CREATION_DATE, I.SANS_SUITE FROM INCIDENTS I INNER JOIN PERSONNES P ON I.PERSONNE_ID = P.PERSONNE_ID INNER JOIN ADMINISTRATIFS A ON I.ADMINISTRATIF_ID = A.ADMINISTRATIF_ID ORDER BY I.CREATION_DATE DESC";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                BigInteger incident_id = BigInteger.valueOf(rs.getInt("INCIDENT_ID"));
                BigInteger personne_id = BigInteger.valueOf(rs.getInt("PERSONNE_ID"));
                BigInteger administratif_id = BigInteger.valueOf(rs.getInt("ADMINISTRATIF_ID"));
                String prenom = rs.getString("PRENOM");
                String nom = rs.getString("NOM");
                Boolean isCivil = rs.getBoolean("IS_CIVIL");
                String rue = rs.getString("RUE");
                String cp = rs.getString("CP");
                String ville = rs.getString("VILLE");
                String pays = rs.getString("PAYS");
                String planete = rs.getString("PLANETE");
                String commentaire = rs.getString("COMMENTAIRE");
                Date creationDate = rs.getDate("CREATION_DATE");
                Boolean sansSuite = rs.getBoolean("SANS_SUITE");

                Personne personne = new Personne(personne_id, nom, prenom, isCivil);
                Administratif administratif = new Administratif(administratif_id, rue, cp, ville, pays, planete);

                Incident incident = new Incident(incident_id, personne, administratif, new ArrayList<SuperCivil>(), commentaire, creationDate, sansSuite);
                incidents.add(incident);
            }
        } catch (SQLException e) {
            LogUtils.logErreur(this.getClass().getSimpleName(), e.getMessage());
            JOptionPane.showMessageDialog(null, "Une erreur est survenue", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}
