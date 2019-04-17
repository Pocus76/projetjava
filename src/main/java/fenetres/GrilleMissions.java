package fenetres;

import mysqlUtil.SqlConnexion;
import objets.Administratif;
import objets.Incident;
import objets.Mission;
import objets.Personne;
import objets.SuperCivil;
import util.LogUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class GrilleMissions extends JFrame {
    ArrayList<Mission> missions = new ArrayList<Mission>();
    private int nbColumns = 9;
    private ArrayList<JLabel> headers = new ArrayList<JLabel>();
    private SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yy");

    public GrilleMissions() {
        super();
        getHeaders();
        getPersonnes();
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
        JPanel bbar = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton retour = new JButton("Retour");
        bbar.add(retour);
        retour.setBounds(400 , 300, 100, 20);
        retour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GrilleMissions.this.dispatchEvent(new WindowEvent(GrilleMissions.this, WindowEvent.WINDOW_CLOSING));
            }
        });

        c.gridwidth = 1;
        c.gridheight = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.FIRST_LINE_START;

        for (int i = 0; i < missions.size(); i += 1) { // Lignes
            Mission mission = missions.get(i);
            for (int j = 0; j < nbColumns; j += 1) { // Colonnes
                c.gridx = j;
                c.weightx = getWidth(j);
                if (i == 0) {
                    c.gridy = 0;
                    panel.add(headers.get(j), c);
                }

                c.gridy = i + 1;

                if (j == 7) {
                    JButton voirIncident = new JButton("X");
                    panel.add(voirIncident, c);
                    voirIncident.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            // TODO
                            //new GereAutorisations(personne.getPersonne_id());
                            //GrillePersonnes.this.dispatchEvent(new WindowEvent(GrillePersonnes.this, WindowEvent.WINDOW_CLOSING));
                        }
                    });
                } else if (j == 8) {
                    JButton terminer = new JButton("X");
                    panel.add(terminer, c);
                    terminer.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            Object[] options = {"OUI", "NON"};
                            int n = JOptionPane.showOptionDialog(null, "Êtes-vous sûr de vouloir déclarer cette personne comme décédée", "Confimation", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
                            /*if (n == 0) {
                                personne.declarerDecedee();
                            }*/
                        }
                    });
                } else {
                    panel.add(getColumn(mission, j), c);
                }
            }
        }



        contenu.add(panel, BorderLayout.PAGE_START);
        contenu.add(bbar, BorderLayout.SOUTH);
        this.setVisible(true);
    }

    private void getPersonnes() {
        try {
            Statement stmt = SqlConnexion.connection.createStatement();
            String query = "SELECT MISSION_ID, INCIDENT_ID, NATURE, TITRE, DESCRIPTION, DATE_DEBUT, DATE_FIN, GRAVITE, URGENCE FROM MISSIONS";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                BigInteger mission_id = BigInteger.valueOf(rs.getInt("MISSION_ID"));
                BigInteger incident_id = BigInteger.valueOf(rs.getInt("INCIDENT_ID"));
                String nature = rs.getString("NATURE");
                String titre = rs.getString("TITRE");
                String description = rs.getString("DESCRIPTION");
                Date dateDebut = rs.getDate("DATE_DEBUT");
                Date dateFin = rs.getDate("DATE_FIN");
                int gravite = rs.getInt("GRAVITE");
                int urgence = rs.getInt("URGENCE");

                Incident incident = new Incident(incident_id);
                Mission mission = new Mission(mission_id, incident, nature, titre, description, dateDebut, dateFin, gravite, urgence);
                missions.add(mission);
            }
        } catch (SQLException e) {
            LogUtils.logErreur(this.getClass().getSimpleName(), e.getMessage());
            JOptionPane.showMessageDialog(null, "Une erreur est survenue", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void getHeaders() {
        headers.add(new JLabel("Titre"));
        headers.add(new JLabel("Description"));
        headers.add(new JLabel("Nature"));
        headers.add(new JLabel("Date début"));
        headers.add(new JLabel("Date fin"));
        headers.add(new JLabel("Gravité"));
        headers.add(new JLabel("Urgence"));
        headers.add(new JLabel("Voir l'incident lié"));
        headers.add(new JLabel("Terminer la mission"));
    }

    private JLabel getColumn(Mission mission, int column) {
        switch (column) {
            case 0:
                return new JLabel(mission.getTitre());
            case 1:
                return new JLabel(mission.getDescription());
            case 2:
                return new JLabel(mission.getNature());
            case 3:
                return new JLabel(formater.format(mission.getDateDebut()));
            case 4:
                return new JLabel(mission.getDateFin() == null ? "Aucune" : formater.format(mission.getDateFin()));
            case 5:
                return new JLabel(mission.getGravite().toString());
            case 6:
                return new JLabel(mission.getUrgence().toString());
        }
        return new JLabel("Vide");
    }

    private int getWidth(int column) {
        switch (column) {
            case 0:
                return 5;
            case 1:
                return 20;
            case 2:
            case 3:
            case 4:
            case 5:
                return 5;
            case 6:
                return 50;
            case 7:
            case 8:
                return 10;

        }
        return 0;
    }
}
