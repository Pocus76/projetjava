package fenetres;

import mysqlUtil.Requetes;
import objets.Autorisation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.math.BigInteger;
import java.util.ArrayList;

//----------------------------------------------------------------------------------------------------------------------
/**
 * @author : Pocus
 * @dateCreation : 16/04/2019
 * @description : Classe qui permet a l'administrateur de gérer les autorisations
 */
//----------------------------------------------------------------------------------------------------------------------
public class GereAutorisations extends JFrame
{
    private Autorisation autorisation;
    private JLabel autorisations;
    private JComboBox listAutorisations;
    private JButton valider, annuler;

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    public GereAutorisations(BigInteger idPersonne)
    {
        super();
        this.setTitle("Édition des autorisations");
        this.setSize(new Dimension(400, 80));
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setAlwaysOnTop(true);
        this.setUndecorated(true);

        Container contenu = this.getContentPane();
        contenu.setLayout(null);

        autorisation = Requetes.getAutorisation(idPersonne);

        autorisations = new JLabel("Autorisations");
        contenu.add(autorisations);
        autorisations.setBounds(20, 20, 100, 20);

        ArrayList<Autorisation> arrayListAutorisations = Requetes.getAutorisations();
        listAutorisations = new JComboBox(arrayListAutorisations.toArray());
        contenu.add(listAutorisations);
        listAutorisations.setBounds(110, 20, 250, 20);
        int index = -1;
        for (int i=0; i<arrayListAutorisations.size(); i++)
        {
            if (arrayListAutorisations.get(i).getAutorisation_libelle().equals(autorisation.getAutorisation_libelle()))
            {
               index = i;
               break;
            }
        }
        System.out.println(index);
        listAutorisations.setSelectedIndex(index);

        valider = new JButton("Valider");
        contenu.add(valider);
        valider.setBounds(110, 45, 80, 20);
        valider.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                Autorisation autorisation = (Autorisation) listAutorisations.getSelectedItem();
                if (autorisation.updateAutorisation(idPersonne))
                {
                    GereAutorisations.this.dispatchEvent(new WindowEvent(GereAutorisations.this, WindowEvent.WINDOW_CLOSING));
                    JOptionPane.showMessageDialog(new JFrame(),"Autorisations mises à jour", "Information", JOptionPane.INFORMATION_MESSAGE);
                }
                else
                {
                    JOptionPane.showMessageDialog(new JFrame(),"Une erreur s'est produite", "Erreur de communication", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        annuler = new JButton("Annuler");
        contenu.add(annuler);
        annuler.setBounds(200, 45, 80, 20);
        annuler.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GereAutorisations.this.dispatchEvent(new WindowEvent(GereAutorisations.this, WindowEvent.WINDOW_CLOSING));
            }
        });

        this.setVisible(true);
    }
}
