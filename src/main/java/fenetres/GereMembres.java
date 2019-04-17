package fenetres;

import mysqlUtil.SqlConnexion;
import objets.Personne;
import util.LogUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class GereMembres extends JFrame {
    BigInteger entreprise_id;
    ArrayList<Personne> membres = new ArrayList<Personne>();
    ArrayList<Personne> candidats = new ArrayList<Personne>();
    private int nbColumns = 2;
    private ArrayList<JLabel> headers = new ArrayList<JLabel>();
    private SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yy");

    public GereMembres(BigInteger personne_id) {
        super();
        entreprise_id = personne_id;
        getHeaders();
        getCandidats();
        getMembres();
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
        JPanel panelCandidats = new JPanel();
        GridBagLayout layoutCandidats = new GridBagLayout();
        panelCandidats.setLayout(layoutCandidats);
        GridBagConstraints c2 = new GridBagConstraints();
        bbar.add(retour);
        retour.setBounds(400, 300, 100, 20);
        retour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GereMembres.this.dispatchEvent(new WindowEvent(GereMembres.this, WindowEvent.WINDOW_CLOSING));
            }
        });

        c.gridwidth = 1;
        c.gridheight = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.FIRST_LINE_START;

        c2.gridwidth = 1;
        c2.gridheight = 1;
        c2.fill = GridBagConstraints.HORIZONTAL;
        c2.anchor = GridBagConstraints.FIRST_LINE_START;

        for (int i = 0; i < membres.size(); i += 1) { // Lignes
            Personne personne = membres.get(i);
            for (int j = 0; j < nbColumns; j += 1) { // Colonnes
                c.gridx = j;
                c.weightx = getWidth(j);
                if (i == 0) {
                    c.gridy = 0;
                    panel.add(headers.get(j), c);
                }

                c.gridy = i + 1;

                if (j == 1) {
                    JButton desaffecter = new JButton("X");
                    panel.add(desaffecter, c);
                    desaffecter.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            Component[] componentList = panel.getComponents();
                            GridBagConstraints buttonConstraints = layout.getConstraints(desaffecter);
                            for (Component compo : componentList) {
                                GridBagConstraints gbc = layout.getConstraints(compo);
                                if (gbc.gridy == buttonConstraints.gridy) {
                                    panel.remove(compo);
                                }
                            }
                            membres.remove(personne);
                            panel.revalidate();
                            panel.repaint();
                        }
                    });
                } else {
                    panel.add(getColumn(personne, j), c);
                }
            }
        }

        for (int k = 0; k < candidats.size(); k += 1) { // Lignes
            Personne candidat = candidats.get(k);
            for (int l = 0; l < nbColumns; l += 1) { // Colonnes
                c2.gridx = l;
                c2.weightx = getWidth(l);
                if (k == 0) {
                    c2.gridy = 0;
                    if (l == 0) {
                        panelCandidats.add(new JLabel("Prénom / Nom"), c2);
                    } else {
                        panelCandidats.add(new JLabel("Affecter"), c2);
                    }
                }

                c2.gridy = k + 1;


                if (l == 1) {
                    JButton affecter = new JButton("X");
                    panelCandidats.add(affecter, c2);
                    affecter.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            Component[] componentList = panelCandidats.getComponents();
                            GridBagConstraints buttonConstraints = layoutCandidats.getConstraints(affecter);
                            for (Component compo : componentList) {
                                GridBagConstraints gbc = layoutCandidats.getConstraints(compo);
                                if (gbc.gridy == buttonConstraints.gridy) {
                                    panelCandidats.remove(compo);
                                }
                            }
                            candidats.remove(candidat);
                            panelCandidats.revalidate();
                            panelCandidats.repaint();
                        }
                    });
                } else {
                    panelCandidats.add(getColumn(candidat, l), c2);
                }
            }
        }

        contenu.add(panel, BorderLayout.PAGE_START);
        contenu.add(panelCandidats, BorderLayout.CENTER);
        contenu.add(bbar, BorderLayout.SOUTH);
        this.setVisible(true);
    }

    private void getMembres() {
        try {
            String query = "SELECT P.PERSONNE_ID, P.PRENOM, P.NOM, MO.IS_DIRIGEANT FROM PERSONNES P INNER JOIN MEMBRES_ORGANISATIONS MO ON (MO.PER_PERSONNE_ID = P.PERSONNE_ID) WHERE MO.PERSONNE_ID = ?;";
            PreparedStatement stmt = SqlConnexion.connection.prepareStatement(query);
            stmt.setBigDecimal(1, new BigDecimal(entreprise_id));
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int personne_id = rs.getInt("PERSONNE_ID");
                String dirigeant = rs.getBoolean("IS_DIRIGEANT") ? " (Dirigeant)" : "";
                String nom = rs.getString("NOM") + dirigeant;
                String prenom = rs.getString("PRENOM");

                Personne personne = new Personne(personne_id, prenom, nom);
                membres.add(personne);
            }
        } catch (SQLException e) {
            LogUtils.logErreur(this.getClass().getSimpleName(), e.getMessage());
            JOptionPane.showMessageDialog(null, "Une erreur est survenue", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void getCandidats() {
        try {
            Statement stmt = SqlConnexion.connection.createStatement();
            String query = "SELECT * FROM PERSONNES WHERE IS_CIVIL = 1 AND PERSONNE_ID NOT IN(SELECT P.PERSONNE_ID FROM PERSONNES P INNER JOIN MEMBRES_ORGANISATIONS MO ON ( MO.PER_PERSONNE_ID = P.PERSONNE_ID) OR (MO.PERSONNE_ID = P.PERSONNE_ID));";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int personne_id = rs.getInt("PERSONNE_ID");
                String nom = rs.getString("NOM");
                String prenom = rs.getString("PRENOM");

                Personne personne = new Personne(personne_id, prenom, nom);
                candidats.add(personne);
            }
        } catch (SQLException e) {
            LogUtils.logErreur(this.getClass().getSimpleName(), e.getMessage());
            JOptionPane.showMessageDialog(null, "Une erreur est survenue", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void getHeaders() {
        headers.add(new JLabel("Prénom / Nom"));
        headers.add(new JLabel("Désaffecter"));
    }

    private JLabel getColumn(Personne personne, int column) {
        if (column == 0) {
            return new JLabel(personne.getPrenom() + " " + personne.getNom());
        }
        return new JLabel("Vide");
    }

    private int getWidth(int col) {
        return 50;
    }
}
