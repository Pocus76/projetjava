package fenetres;

import mysqlUtil.SqlConnexion;
import objets.Administratif;
import objets.SuperCivil;
import util.AutoSuggestor;
import util.LogUtils;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.util.ArrayList;

//----------------------------------------------------------------------------------------------------------------------
/**
 * @author : Pocus
 * @dateCreation : 22/03/2019
 * @description : Classe qui ajoute un vilain à la BDD
 */
//----------------------------------------------------------------------------------------------------------------------
public class AjouteVilain extends JFrame
{
    private JLabel nom, personne, pouvoir, faiblesse, score, commentaire, isSuperHeros;
    private JTextField nom1, personne1, pouvoir1, faiblesse1, score1;
    private JTextArea commentaire1;
    private JCheckBox isSuperHeros1;
    private JButton valider, annuler;
    private ArrayList<String> arrayListPersonnes = getPersonnes();

    public AjouteVilain()
    {
        super();
        this.setSize(new Dimension(320,400));
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setUndecorated(true);

        Container contenu = this.getContentPane();
        this.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, new Color(153, 86, 0)));
        contenu.setBackground(Color.white);
        contenu.setLayout(null);

        JLabel titre = new JLabel("Ajouter un super-civil", SwingConstants.CENTER);
        contenu.add(titre);
        titre.setFont(new Font(titre.getFont().getName(), Font.BOLD, 15));
        titre.setBounds(65, 10, 180, 20);

        nom = new JLabel("Nom");
        contenu.add(nom);
        nom.setBounds(20, 40, 100, 20);

        nom1 = new JTextField();
        contenu.add(nom1);
        nom1.setBounds(150, 40, 150, 20);

        personne = new JLabel("Personne");
        contenu.add(personne);
        personne.setBounds(20, 70, 100, 20);

        personne1 = new JTextField();
        contenu.add(personne1);
        personne1.setBounds(150, 70, 150, 20);
        AutoSuggestor.setupAutoComplete(personne1, arrayListPersonnes);

        pouvoir = new JLabel("Pouvoir");
        contenu.add(pouvoir);
        pouvoir.setBounds(20, 100, 100, 20);

        pouvoir1 = new JTextField();
        contenu.add(pouvoir1);
        pouvoir1.setBounds(150, 100, 150, 20);

        faiblesse = new JLabel("Faiblesse");
        contenu.add(faiblesse);
        faiblesse.setBounds(20, 130, 100, 20);

        faiblesse1 = new JTextField();
        contenu.add(faiblesse1);
        faiblesse1.setBounds(150, 130, 150, 20);

        score = new JLabel("Score");
        contenu.add(score);
        score.setBounds(20, 160, 100, 20);

        NumberFormat format = NumberFormat.getInstance();
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Float.class);
        formatter.setMinimum(0);
        formatter.setMaximum(Integer.MAX_VALUE);
        formatter.setAllowsInvalid(false);
        formatter.setCommitsOnValidEdit(true);
        score1 = new JFormattedTextField(formatter);
        contenu.add(score1);
        score1.setBounds(150, 160, 150, 20);

        commentaire = new JLabel("Commentaire");
        contenu.add(commentaire);
        commentaire.setBounds(20, 230, 100, 20);

        commentaire1 = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(commentaire1);
        contenu.add(scrollPane);
        scrollPane.setBounds(150, 190, 150, 100);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        isSuperHeros = new JLabel("Super-héros");
        contenu.add(isSuperHeros);
        isSuperHeros.setBounds(20, 320, 100, 20);

        isSuperHeros1 = new JCheckBox();
        contenu.add(isSuperHeros1);
        isSuperHeros1.setBounds(150, 320, 20, 20);

        valider = new JButton("Valider");
        contenu.add(valider);
        valider.setBounds(20,350 ,80 ,20 );
        valider.addActionListener(new AjouteVilain.ValidationListener());

        annuler = new JButton("Annuler");
        contenu.add(annuler);
        annuler.setBounds(220, 350, 85, 20);
        annuler.addActionListener(new AjouteVilain.AnnulationListener());

        this.setVisible(true);
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    private static ArrayList<String> getPersonnes()
    {
        ArrayList<String> listePersonnes = new ArrayList<>();
        try
        {
            Statement stm = SqlConnexion.connection.createStatement();
            String sql = "select PRENOM, NOM from personnes";
            ResultSet rst;
            rst = stm.executeQuery(sql);
            while (rst.next())
            {
                String personne = rst.getString("PRENOM")+" "+rst.getString("NOM");
                listePersonnes.add(personne);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return listePersonnes;
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public class ValidationListener implements ActionListener {
        //--------------------------------------------------------------------------------------------------------------
        //--------------------------------------------------------------------------------------------------------------
        @Override
        public void actionPerformed(ActionEvent e)
        {
            String nom = nom1.getText();
            String personne = personne1.getText();
            String pouvoir = pouvoir1.getText();
            String faiblesse = faiblesse1.getText();
            String score = score1.getText();
            float fScore = score.equals("") ? -1 : Float.parseFloat(score);
            String commentaire = commentaire1.getText();
            boolean isSuperHeros = isSuperHeros1.isSelected();

            if (nom.equals(""))
            {
                JOptionPane.showMessageDialog(AjouteVilain.this, "Veuillez renseigner un nom", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
            else if (!personne.equals("") && checkPersonneExiste(personne) == -1)
            {
                JOptionPane.showMessageDialog(AjouteVilain.this, "Veuillez renseigner une personne existante", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                int idPersonne = -1;
                if (!personne.equals(""))
                {
                    idPersonne = checkPersonneExiste(personne);
                }
                if (ajouteSuperCivil(nom, idPersonne, pouvoir, faiblesse, fScore, commentaire, isSuperHeros))
                {
                    JOptionPane.showMessageDialog(AjouteVilain.this,"Super-civil bien ajouté", "Ajout réussi", JOptionPane.INFORMATION_MESSAGE);
                    AjouteVilain.this.dispatchEvent(new WindowEvent(AjouteVilain.this, WindowEvent.WINDOW_CLOSING));
                }
                else
                {
                    JOptionPane.showMessageDialog(AjouteVilain.this,"Une erreur s'est produite", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

        //--------------------------------------------------------------------------------------------------------------
        //--------------------------------------------------------------------------------------------------------------
        private boolean ajouteSuperCivil(String nom, int idPersonne, String pouvoir, String faiblesse, float score, String commentaire, boolean isSuperHeros)
        {
            boolean ajoutOk = false;
            String query = " insert into supers_civils (PERSONNE_ID, NOM, POUVOIR, FAIBLESSE, SCORE, COMMENTAIRE, IS_SUPER_HEROS)"
                    + " values (?, ?, ?, ?, ?, ?, ?)";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = null;
            try
            {
                preparedStmt = SqlConnexion.connection.prepareStatement(query);
                if (idPersonne!=-1) preparedStmt.setBigDecimal(1, BigDecimal.valueOf(idPersonne));
                else preparedStmt.setBigDecimal(1, null);
                preparedStmt.setString(2, nom);
                preparedStmt.setString(3, pouvoir);
                preparedStmt.setString(4, faiblesse);
                if (score!=-1)preparedStmt.setFloat(5, score);
                else preparedStmt.setFloat(5, 0);
                preparedStmt.setString(6, commentaire);
                preparedStmt.setBoolean(7, isSuperHeros);

                // execute the preparedstatement
                preparedStmt.execute();
                ajoutOk = true;
            }
            catch (SQLException e)
            {
                ajoutOk = false;
                LogUtils.logErreur(this.getClass().getSimpleName(), e.getMessage());
                JOptionPane.showMessageDialog(AjouteVilain.this, "Une erreur s'est produite", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
            return ajoutOk;
        }
    }

    private int checkPersonneExiste(String personne)
    {
        int idPersonne = -1;
        try
        {
            Statement statement = SqlConnexion.connection.createStatement();
            String sql = "select PERSONNE_ID from personnes where concat(PRENOM, ' ', NOM)='"+personne+"'";
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next())
            {
                idPersonne = rs.getInt("PERSONNE_ID");
            }
        }
        catch (SQLException err)
        {
            LogUtils.logErreur(this.getClass().getSimpleName(), err.getMessage());
            JOptionPane.showMessageDialog(AjouteVilain.this,"Une erreur s'est produite", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        return idPersonne;
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public class AnnulationListener implements ActionListener
    {
        //--------------------------------------------------------------------------------------------------------------
        //--------------------------------------------------------------------------------------------------------------
        @Override
        public void actionPerformed(ActionEvent e)
        {
            AjouteVilain.this.dispatchEvent(new WindowEvent(AjouteVilain.this, WindowEvent.WINDOW_CLOSING));
        }
    }
}
