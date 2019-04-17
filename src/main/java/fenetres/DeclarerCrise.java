package fenetres;

import mysqlUtil.Requetes;
import objets.Administratif;
import objets.Personne;
import objets.SuperCivil;
import util.Constants;
import util.EnvoiMail;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

//----------------------------------------------------------------------------------------------------------------------
/**
 * @author : Pocus
 * @dateCreation : 17/04/2019
 * @description : Classe qui permet de déclarer une crise
 */
//----------------------------------------------------------------------------------------------------------------------
public class DeclarerCrise extends JFrame
{
    private JLabel mission, description, personne;
    private JTextArea fieldDesciption;
    private JComboBox listMissions;
    private JList<Personne> listPersonnes;
    private JButton btnAnnuler, btnValider;

    public DeclarerCrise()
    {
        super();
        this.setTitle("Déclaration d'une crise");
        this.setSize(new Dimension(400, 280));
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setUndecorated(true);

        Container contenu = this.getContentPane();
        contenu.setLayout(null);

        mission = new JLabel("Mission");
        contenu.add(mission);
        mission.setBounds(20, 20, 100, 20);


        ArrayList<objets.Mission> missions = Requetes.getMissions();
        listMissions = new JComboBox<>(missions.toArray());
        contenu.add(listMissions);
        listMissions.setBounds(110, 20, 250, 20);
        listMissions.setSelectedIndex(-1);

        description = new JLabel("Description");
        contenu.add(description);
        description.setBounds(20, 70, 100, 20);

        fieldDesciption = new JTextArea();
        contenu.add(fieldDesciption);
        fieldDesciption.setBounds(110, 45, 250, 75);

        personne = new JLabel("Personne(s)");
        contenu.add(personne);
        personne.setBounds(20, 170, 100, 20);

        ArrayList<Personne> personnes = Requetes.getPersonnes();
        Personne[] data = new Personne[personnes.size()];
        personnes.toArray(data);
        listPersonnes = new JList<>(data);
        listPersonnes.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane scrollPane1 = new JScrollPane();
        scrollPane1.setViewportView(listPersonnes);
        contenu.add(scrollPane1);
        scrollPane1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane1.setBounds(110, 145, 250, 75);

        btnValider = new JButton("Valider");
        contenu.add(btnValider);
        btnValider.setBounds(110, 245, 80, 20);
        btnValider.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                objets.Crise crise = new objets.Crise((objets.Mission) listMissions.getSelectedItem(), fieldDesciption.getText(), listPersonnes.getSelectedValuesList());
                boolean insere = crise.insertIntoDatabase();
                if (insere)
                {
                    String mission = "";
                    if (crise.getMission()!=null)
                    {
                        mission = crise.getMission().getTitre();
                    }
                    String message = Constants.utilisateurConnecte.getNom()+" "+Constants.utilisateurConnecte.getPrenom()+" vient de d&eacute;clarer une crise.<br/>";
                    if (!crise.getDescription().equals("")) message += "Description : "+crise.getDescription()+".<br/>";
                    if (!mission.equals("")) message+="Cette crise concerne la mission : "+mission+".<br/>";
                    if (crise.getPersonnes()!=null&&crise.getPersonnes().size()>0)
                    {
                        String sPersonnes = "";
                        if (crise.getPersonnes().size()>1)
                        {
                            for (Personne personne : crise.getPersonnes())
                            {
                                sPersonnes+=personne.getNom()+" "+personne.getPrenom()+", ";
                            }
                            sPersonnes = sPersonnes.substring(0, sPersonnes.lastIndexOf(","));
                            message+="Les personnes "+sPersonnes+" sont concern&eacute;es.<br/>";
                        }
                        else
                        {
                            message+="La personne "+crise.getPersonnes().get(0).getNom()+" "+crise.getPersonnes().get(0).getPrenom()+" est concern&eacute;e.<br/>";
                        }
                    }
                    new EnvoiMail("Notification de crise", message);
                    DeclarerCrise.this.dispatchEvent(new WindowEvent(DeclarerCrise.this, WindowEvent.WINDOW_CLOSING));
                    JOptionPane.showMessageDialog(new JFrame(),"Crise déclarée", "Information", JOptionPane.INFORMATION_MESSAGE);
                    new Accueil();
                }
            }
        });

        btnAnnuler = new JButton("Annuler");
        contenu.add(btnAnnuler);
        btnAnnuler.setBounds(200, 245, 80, 20);
        btnAnnuler.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DeclarerCrise.this.dispatchEvent(new WindowEvent(DeclarerCrise.this, WindowEvent.WINDOW_CLOSING));
                new Accueil();
            }
        });

        this.setVisible(true);
    }
}
