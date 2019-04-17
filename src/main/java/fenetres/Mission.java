package fenetres;

import mysqlUtil.SqlConnexion;
import objets.Item;
import objets.SuperCivil;
import util.AvengersToolkit;
import util.DateUtil;
import util.ListesAvengers;
import util.LogUtils;

import javax.swing.*;
import javax.swing.event.CaretListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

//----------------------------------------------------------------------------------------------------------------------
/**
 * @author : Pocus
 * @dateCreation : 15/04/2019
 * @description : Ecran pour saisir une mission
 */
//----------------------------------------------------------------------------------------------------------------------
public class Mission extends JFrame
{
    private JLabel naturesMission, titre, dateDebut, dateFin, itineraire, complements, gravite, niveauUrgence, heros;
    private JComboBox listeGravites;
    private JList<SuperCivil> listHeros;
    private JCheckBox checkUrgence;
    private JTextField fieldNaturesMission, fieldTitre, fieldDateDebut, fieldDateFin, fieldItiniraire, fieldComplements;
    private JButton valider, annuler;

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public Mission(objets.Incident incident)
    {
        super();
        this.setTitle("Déclaration d'une mission");
        this.setSize(new Dimension(400, 400));
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setUndecorated(true);

        Container contenu = this.getContentPane();
        this.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, new Color(0, 58, 153)));
        contenu.setBackground(Color.white);
        contenu.setLayout(null);

        naturesMission = new JLabel("Nature *");
        contenu.add(naturesMission);
        naturesMission.setBounds(20, 20, 100, 20);

        CaretListener caretupdate = new CaretListener()
        {
            public void caretUpdate(javax.swing.event.CaretEvent e)
            {
                JTextField text = (JTextField)e.getSource();
                if (fieldTitre.getText().contains("Mission"))
                {
                    String[] txtTitre = fieldTitre.getText().split("-");
                    fieldTitre.setText(txtTitre[0]+"- "+text.getText());
                }
            }
        };

        fieldNaturesMission = new JTextField();
        fieldNaturesMission.addCaretListener(caretupdate);
        contenu.add(fieldNaturesMission);
        fieldNaturesMission.setBounds(110, 20, 250, 20);

        titre = new JLabel("Titre *");
        contenu.add(titre);
        titre.setBounds(20, 45, 100, 20);

        fieldTitre = new JTextField();
        fieldTitre.setText("Mission "+countMissions()+" ");
        contenu.add(fieldTitre);
        fieldTitre.setBounds(110, 45, 250, 20);

        dateDebut = new JLabel("Date de début *");
        contenu.add(dateDebut);
        dateDebut.setBounds(20, 70, 100, 20);

        fieldDateDebut = new JTextField(DateUtil.toString(Calendar.getInstance().getTime()));
        contenu.add(fieldDateDebut);
        fieldDateDebut.setBounds(110, 70, 250, 20);

        dateFin = new JLabel("Date de fin");
        contenu.add(dateFin);
        dateFin.setBounds(20, 95, 100, 20);

        fieldDateFin = new JTextField();
        contenu.add(fieldDateFin);
        fieldDateFin.setEnabled(false);
        fieldDateFin.setBounds(110, 95, 250, 20);

        itineraire = new JLabel("Itinéraire *");
        contenu.add(itineraire);
        itineraire.setBounds(20, 120, 100, 20);

        fieldItiniraire = new JTextField();
        contenu.add(fieldItiniraire);
        fieldItiniraire.setBounds(110, 120, 250, 20);

        gravite = new JLabel("Gravité *");
        contenu.add(gravite);
        gravite.setBounds(20, 145, 100, 20);

        listeGravites = new JComboBox(ListesAvengers.listeNaturesMission);
        contenu.add(listeGravites);
        listeGravites.setBounds(110, 145, 250, 20);

        niveauUrgence = new JLabel("Urgent");
        contenu.add(niveauUrgence);
        niveauUrgence.setBounds(20, 170, 100, 20);

        checkUrgence = new JCheckBox();
        contenu.add(checkUrgence);
        checkUrgence.setBounds(110, 170, 250, 20);

        complements = new JLabel("Compléments");
        contenu.add(complements);
        complements.setBounds(20, 195, 100, 20);

        fieldComplements = new JTextField();
        contenu.add(fieldComplements);
        fieldComplements.setBounds(110, 195, 250, 20);

        heros = new JLabel("<html>Super héros * <br />CTRL ou Shift <br />pour en sélectionner plusieurs</html>");
        contenu.add(heros);
        heros.setBounds(20, 220, 100, 100);

        ArrayList<SuperCivil> storeVilains = new ArrayList<>();
        try
        {
            Statement stmt = SqlConnexion.connection.createStatement();
            String query = "SELECT SUPER_CIVIL_ID, NOM, POUVOIR, FAIBLESSE, SCORE, COMMENTAIRE, IS_SUPER_HEROS FROM SUPERS_CIVILS WHERE IS_SUPER_HEROS IS TRUE";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next())
            {
                BigInteger superCivil_id = BigInteger.valueOf(rs.getInt("SUPER_CIVIL_ID"));
                String nom = rs.getString("NOM");
                String pouvoir = rs.getString("POUVOIR");
                String faiblesse = rs.getString("FAIBLESSE");
                Float score = rs.getFloat("SCORE");
                String commentaire = rs.getString("COMMENTAIRE");
                Boolean isSuperHeros = rs.getBoolean("IS_SUPER_HEROS");
                storeVilains.add(new SuperCivil(superCivil_id, nom, pouvoir, faiblesse, score, commentaire, isSuperHeros));
            }
        } catch (SQLException e)
        {
            LogUtils.logErreur(this.getClass().getSimpleName(), e.getMessage());
            JOptionPane.showMessageDialog(null, "Une erreur est survenue", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        SuperCivil[] data = new SuperCivil[storeVilains.size()];
        storeVilains.toArray(data);
        listHeros = new JList<SuperCivil>(data);
        listHeros.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane scrollPane1 = new JScrollPane();
        scrollPane1.setViewportView(listHeros);
        contenu.add(scrollPane1);
        scrollPane1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane1.setBounds(110, 220, 250, 100);

        valider = new JButton("Valider");
        contenu.add(valider);
        valider.setBounds(110, 345, 80, 20);
        valider.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if (!checkFormulaire())
                {
                    return;
                }
                objets.Mission mission = new objets.Mission(incident, listHeros.getSelectedValuesList(), fieldNaturesMission.getText(), fieldTitre.getText(), fieldComplements.getText(), DateUtil.toDate(fieldDateDebut.getText()), Integer.parseInt(((Item) listeGravites.getSelectedItem()).getId()), AvengersToolkit.boolToInt(checkUrgence.isSelected()));
                if (mission.insertIntoDatabase())
                {
                    JOptionPane.showMessageDialog(new JFrame(), "Mission ajoutée", "Information", JOptionPane.INFORMATION_MESSAGE);
                    Mission.this.dispatchEvent(new WindowEvent(Mission.this, WindowEvent.WINDOW_CLOSING));
                }
                else
                {
                    JOptionPane.showMessageDialog(new JFrame(), "Une erreur est survenue", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        annuler = new JButton("Annuler");
        contenu.add(annuler);
        annuler.setBounds(200, 345, 80, 20);
        annuler.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                Mission.this.dispatchEvent(new WindowEvent(Mission.this, WindowEvent.WINDOW_CLOSING));
            }
        });

        this.setVisible(true);
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    private int countMissions()
    {
        int nbMissions = 0;
        try
        {
            Statement statement = SqlConnexion.connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select count(*) from missions");

            while (resultSet.next())
            {
                nbMissions = resultSet.getInt(1);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return nbMissions+1;
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    private Boolean checkFormulaire()
    {
        boolean isFormValid =  true;
        if (fieldNaturesMission.getText().equals(""))
        {
            JOptionPane.showMessageDialog(Mission.this, "Vous devez saisir la nature de la mission", "Erreur", JOptionPane.ERROR_MESSAGE);
            isFormValid = false;
        }
        else if (fieldTitre.getText().equals(""))
        {
            JOptionPane.showMessageDialog(Mission.this, "Vous devez saisir le titre de la mission", "Erreur", JOptionPane.ERROR_MESSAGE);
            isFormValid = false;
        }
        else if (fieldDateDebut.getText().equals(""))
        {
            JOptionPane.showMessageDialog(Mission.this, "Vous devez saisir la date de début de la mission", "Erreur", JOptionPane.ERROR_MESSAGE);
            isFormValid = false;
        }
        else if (fieldItiniraire.getText().equals(""))
        {
            JOptionPane.showMessageDialog(Mission.this, "Vous devez saisir l'itinéraire de la mission", "Erreur", JOptionPane.ERROR_MESSAGE);
            isFormValid = false;
        }
        return isFormValid;
    }
}
