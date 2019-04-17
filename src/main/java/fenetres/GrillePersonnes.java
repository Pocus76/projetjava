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
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class GrillePersonnes extends JFrame {
    ArrayList<Personne> personnes = new ArrayList<Personne>();
    private int nbColumns = 10;
    private ArrayList<JLabel> headers = new ArrayList<JLabel>();
    private SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yy");

    public GrillePersonnes() {
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
                GrillePersonnes.this.dispatchEvent(new WindowEvent(GrillePersonnes.this, WindowEvent.WINDOW_CLOSING));
            }
        });

        c.gridwidth = 1;
        c.gridheight = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.FIRST_LINE_START;

        for (int i = 0; i < personnes.size(); i += 1) { // Lignes
            Personne personne = personnes.get(i);
            for (int j = 0; j < nbColumns; j += 1) { // Colonnes
                c.gridx = j;
                c.weightx = getWidth(j);
                if (i == 0) {
                    c.gridy = 0;
                    panel.add(headers.get(j), c);
                }

                c.gridy = i + 1;

                if (j == 7) {
                    JButton modifAutorisations = new JButton("X");
                    panel.add(modifAutorisations, c);
                    modifAutorisations.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            new GereAutorisations(personne.getPersonne_id());
                            GrillePersonnes.this.dispatchEvent(new WindowEvent(GrillePersonnes.this, WindowEvent.WINDOW_CLOSING));
                        }
                    });
                } else if (j == 8) {
                    JButton deceder = new JButton("X");
                    panel.add(deceder, c);
                    deceder.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            Object[] options = {"OUI", "NON"};
                            int n = JOptionPane.showOptionDialog(null, "Êtes-vous sûr de vouloir déclarer cette personne comme décédée", "Confimation", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
                            if (n == 0) {
                                personne.declarerDecedee();
                            }
                        }
                    });
                } else if (j == 9) {
                    JButton supprimerPersonne = new JButton("X");
                    panel.add(supprimerPersonne, c);
                    supprimerPersonne.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            Object[] options = {"OUI", "NON"};
                            int n = JOptionPane.showOptionDialog(null, "Êtes-vous sûr de vouloir supprimer cet utilisateur", "Confimation", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
                            if (n == 0) {
                                Boolean supprime = personne.supprimer();
                                if (supprime) {
                                    Component[] componentList = panel.getComponents();
                                    GridBagConstraints buttonConstraints = layout.getConstraints(supprimerPersonne);
                                    for (Component compo : componentList) {
                                        GridBagConstraints gbc = layout.getConstraints(compo);
                                        if (gbc.gridy == buttonConstraints.gridy) {
                                            panel.remove(compo);
                                        }
                                    }
                                    personnes.remove(personne);
                                    panel.revalidate();
                                    panel.repaint();
                                }
                            }
                        }
                    });
                } else {
                    panel.add(getColumn(personne, j), c);
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
            String query = "SELECT PERSONNE_ID, IS_CIVIL, PRENOM, NOM, NATIONALITE, CREATION_DATE, NB_INCIDENTS_DECLARES, NB_INCIDENTS_LIES, COMMENTAIRE FROM PERSONNES";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                BigInteger personne_id = BigInteger.valueOf(rs.getInt("PERSONNE_ID"));
                Boolean isCivil = rs.getBoolean("IS_CIVIL");
                String nom = rs.getString("NOM");
                String prenom = rs.getString("PRENOM");
                String nationalite = rs.getString("NATIONALITE");
                Date creationDate = rs.getDate("CREATION_DATE");
                int nbIncidentsDeclares = rs.getInt("NB_INCIDENTS_DECLARES");
                int nbIncidentsLies = rs.getInt("NB_INCIDENTS_LIES");
                String commentaire = rs.getString("COMMENTAIRE");

                Personne personne = new Personne(personne_id, nom, prenom, nationalite, nbIncidentsDeclares, nbIncidentsLies, creationDate, isCivil, commentaire);
                personnes.add(personne);
            }
        } catch (SQLException e) {
            LogUtils.logErreur(this.getClass().getSimpleName(), e.getMessage());
            JOptionPane.showMessageDialog(null, "Une erreur est survenue", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void getHeaders() {
        headers.add(new JLabel("Pers / Entreprise"));
        headers.add(new JLabel("Nom / Prénom"));
        headers.add(new JLabel("Nationalité"));
        headers.add(new JLabel("Date création"));
        headers.add(new JLabel("Incidents déclarés"));
        headers.add(new JLabel("Incidents liés"));
        headers.add(new JLabel("Commentaire"));
        headers.add(new JLabel("Modifier autorisations"));
        headers.add(new JLabel("Déclarer comme décédée"));
        headers.add(new JLabel("Supprimer"));
    }

    private JLabel getColumn(Personne personne, int column) {
        switch (column) {
            case 0:
                return new JLabel(personne.getCivil() ? "Pers" : "Entreprise");
            case 1:
                return new JLabel(personne.getNom() + " " + personne.getPrenom());
            case 2:
                return new JLabel(personne.getNationalite());
            case 3:
                return new JLabel(formater.format(personne.getCreation_date()));
            case 4:
                return new JLabel(personne.getNbIncidentsDeclares().toString());
            case 5:
                return new JLabel(personne.getNbIncidentsLies().toString());
            case 6:
                return new JLabel(personne.getCommentaire());
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
            case 9:
                return 10;

        }
        return 0;
    }
}
