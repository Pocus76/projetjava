package fenetres;

import mysqlUtil.SqlConnexion;
import objets.Administratif;
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

/**
 * @author Guill
 * @dateCreation: 21/03/2019
 * @description: Classe qui gère l'écran d'ajout d'incident
 */


public class Incident extends JFrame {
    private JLabel commentaire, adresse, cp, ville, pays, planete, vilains;
    private JTextArea fieldCommentaire;
    private JTextField fieldAdresse, fieldCp, fieldVille, fieldPays, fieldPlanete;
    private JList<SuperCivil> listVilains;
    private JButton valider, annuler;

    public Incident() {
        super();
        this.setTitle("Déclaration d'un incident");
        this.setSize(new Dimension(400, 400));
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setUndecorated(true);

        Container contenu = this.getContentPane();
        contenu.setLayout(null);

        adresse = new JLabel("Adresse");
        contenu.add(adresse);
        adresse.setBounds(20, 20, 100, 20);

        fieldAdresse = new JTextField();
        contenu.add(fieldAdresse);
        fieldAdresse.setBounds(110, 20, 250, 20);

        cp = new JLabel("Code Postal *");
        contenu.add(cp);
        cp.setBounds(20, 45, 100, 20);

        fieldCp = new JTextField();
        contenu.add(fieldCp);
        fieldCp.setBounds(110, 45, 250, 20);

        ville = new JLabel("Ville *");
        contenu.add(ville);
        ville.setBounds(20, 75, 100, 20);

        fieldVille = new JTextField();
        contenu.add(fieldVille);
        fieldVille.setBounds(110, 75, 250, 20);

        pays = new JLabel("Pays *");
        contenu.add(pays);
        pays.setBounds(20, 100, 100, 20);

        fieldPays = new JTextField();
        contenu.add(fieldPays);
        fieldPays.setBounds(110, 100, 250, 20);

        planete = new JLabel("Planète");
        contenu.add(planete);
        planete.setBounds(20, 125, 100, 20);

        fieldPlanete = new JTextField();
        contenu.add(fieldPlanete);
        fieldPlanete.setText("Terre");
        fieldPlanete.setEditable(false);
        fieldPlanete.setBounds(110, 125, 250, 20);

        commentaire = new JLabel("Commentaire *");
        contenu.add(commentaire);
        commentaire.setBounds(20, 150, 100, 20);

        fieldCommentaire = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(fieldCommentaire);
        contenu.add(scrollPane);
        scrollPane.setBounds(110, 150, 250, 100);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        vilains = new JLabel("<html>Super vilains * <br />CTRL ou Shift <br />pour en sélectionner plusieurs</html>");
        contenu.add(vilains);
        vilains.setBounds(20, 255, 100, 100);

        ArrayList<SuperCivil> storeVilains = new ArrayList<SuperCivil>();
        try {
            Statement stmt = SqlConnexion.connection.createStatement();
            String query = "SELECT SUPER_CIVIL_ID, NOM, POUVOIR, FAIBLESSE, SCORE, COMMENTAIRE, IS_SUPER_HEROS FROM SUPERS_CIVILS WHERE IS_SUPER_HEROS IS FALSE";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                BigInteger superCivil_id = BigInteger.valueOf(rs.getInt("SUPER_CIVIL_ID"));
                String nom = rs.getString("NOM");
                String pouvoir = rs.getString("POUVOIR");
                String faiblesse = rs.getString("FAIBLESSE");
                Float score = rs.getFloat("SCORE");
                String commentaire = rs.getString("COMMENTAIRE");
                Boolean isSuperHeros = rs.getBoolean("IS_SUPER_HEROS");
                storeVilains.add(new SuperCivil(superCivil_id, nom, pouvoir, faiblesse, score, commentaire, isSuperHeros));
            }
        } catch (SQLException e) {
            LogUtils.logErreur(this.getClass().getSimpleName(), e.getMessage());
            JOptionPane.showMessageDialog(null, "Une erreur est survenue", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        SuperCivil[] data = new SuperCivil[storeVilains.size()];
        storeVilains.toArray(data);
        listVilains = new JList<SuperCivil>(data);
        listVilains.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane scrollPane1 = new JScrollPane();
        scrollPane1.setViewportView(listVilains);
        contenu.add(scrollPane1);
        scrollPane1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane1.setBounds(110, 255, 250, 100);

        valider = new JButton("Valider");
        contenu.add(valider);
        valider.setBounds(110, 360, 80, 20);
        valider.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!checkFormulaire()) {
                    return;
                }
                Administratif administratif = new Administratif(fieldAdresse.getText(), fieldCp.getText(), fieldVille.getText(), fieldPays.getText(), fieldPlanete.getText());
                administratif.insertIntoDatabase();
                objets.Incident incident = new objets.Incident(administratif, listVilains.getSelectedValuesList(), fieldCommentaire.getText());
                incident.insertIntoDatabase();
            }
        });

        annuler = new JButton("Annuler");
        contenu.add(annuler);
        annuler.setBounds(200, 360, 80, 20);
        annuler.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Incident.this.dispatchEvent(new WindowEvent(Incident.this, WindowEvent.WINDOW_CLOSING));
            }
        });

        this.setVisible(true);
    }

    private Boolean checkFormulaire()
    {
        boolean isFormValid =  true;
        if (fieldCommentaire.getText().equals(""))
        {
            JOptionPane.showMessageDialog(Incident.this, "Vous devez saisir un commentaire", "Erreur", JOptionPane.ERROR_MESSAGE);
            isFormValid = false;
        }
        else if (fieldCp.getText().equals("") || fieldVille.getText().equals("") ||fieldPays.getText().equals(""))
        {
            JOptionPane.showMessageDialog(Incident.this, "Adresse incomplète", "Erreur", JOptionPane.ERROR_MESSAGE);
            isFormValid = false;
        }
        return isFormValid;
    }
}
