package fenetres;

import mysqlUtil.SqlConnexion;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;

//----------------------------------------------------------------------------------------------------------------------
/**
 * @author : Pocus
 * @dateCreation : 18/04/2019
 * @description : Classe qui affiche les rapports
 */
//----------------------------------------------------------------------------------------------------------------------
public class GrilleRapports extends JFrame
{
    ArrayList<objets.Rapport> rapports = new ArrayList<>();
    private int nbColumns = 2;
    private ArrayList<JLabel> headers = new ArrayList<JLabel>();
    private SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yy");

    public GrilleRapports()
    {
        super();
        getHeaders();
        getRapports();
        this.setSize(new Dimension(1300, 700));
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setUndecorated(true);

        Container contenu = this.getContentPane();
        this.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, new Color(0, 58, 153)));
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
        retour.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                GrilleRapports.this.dispatchEvent(new WindowEvent(GrilleRapports.this, WindowEvent.WINDOW_CLOSING));
            }
        });

        c.gridwidth = 1;
        c.gridheight = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.FIRST_LINE_START;

        for (int i = 0; i < rapports.size(); i += 1) { // Lignes
            objets.Rapport rapport = rapports.get(i);
            for (int j = 0; j < nbColumns; j += 1) { // Colonnes
                c.gridx = j;
                c.weightx = getWidth(j);
                if (i == 0) {
                    c.gridy = 0;
                    panel.add(headers.get(j), c);
                }

                c.gridy = i + 1;
                panel.add(getColumn(rapport, j), c);
            }
        }

        contenu.add(panel, BorderLayout.PAGE_START);
        contenu.add(bbar, BorderLayout.SOUTH);
        this.setVisible(true);
    }

    private void getRapports()
    {
        try
        {
            Statement stmt = SqlConnexion.connection.createStatement();
            String query = "SELECT * FROM RAPPORTS";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next())
            {
                BigInteger rapport_id = BigInteger.valueOf(rs.getInt("RAPPORT_ID"));
                String description = rs.getString("DESCRIPTION");
                boolean succes = rs.getBoolean("SUCCES");

                objets.Rapport rapport = new objets.Rapport(rapport_id, description, succes);
                rapports.add(rapport);
            }
        }
        catch (SQLException e)
        {
            LogUtils.logErreur(this.getClass().getSimpleName(), e.getMessage());
            JOptionPane.showMessageDialog(null, "Une erreur est survenue", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void getHeaders()
    {
        headers.add(new JLabel("Description"));
        headers.add(new JLabel("Succès"));
    }

    private JLabel getColumn(objets.Rapport rapport, int column)
    {
        if (column == 0)
        {
            return new JLabel(rapport.getDescription());
        }
        if (column == 1)
        {
            return new JLabel(rapport.getSucces() ? "Oui" : "Non");
        }
        return new JLabel("Vide");
    }

    private int getWidth(int column)
    {
        switch (column)
        {
            case 0:
                return 70;
            case 1:
                return 30;
        }
        return 0;
    }
}
