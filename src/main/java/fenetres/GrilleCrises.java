package fenetres;

import mysqlUtil.SqlConnexion;
import objets.Crise;
import objets.Incident;
import objets.Mission;
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
import java.util.Date;

//----------------------------------------------------------------------------------------------------------------------
/**
 * @author : Pocus
 * @dateCreation : 18/04/2019
 * @description : Classe qui affiche les crises
 */
//----------------------------------------------------------------------------------------------------------------------
public class GrilleCrises extends JFrame
{
    ArrayList<objets.Crise> crises = new ArrayList<>();
    private int nbColumns = 2;
    private ArrayList<JLabel> headers = new ArrayList<JLabel>();
    private SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yy");

    public GrilleCrises()
    {
        super();
        getHeaders();
        getCrises();
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
                GrilleCrises.this.dispatchEvent(new WindowEvent(GrilleCrises.this, WindowEvent.WINDOW_CLOSING));
            }
        });

        c.gridwidth = 1;
        c.gridheight = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.FIRST_LINE_START;

        for (int i = 0; i < crises.size(); i += 1) { // Lignes
            objets.Crise crise = crises.get(i);
            for (int j = 0; j < nbColumns; j += 1) { // Colonnes
                c.gridx = j;
                c.weightx = getWidth(j);
                if (i == 0) {
                    c.gridy = 0;
                    panel.add(headers.get(j), c);
                }

                c.gridy = i + 1;

                if (j == 1) {
                    JButton terminer = new JButton("X");
                    panel.add(terminer, c);
                    terminer.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            new Rapport();
                        }
                    });
                } else {
                    panel.add(getColumn(crise, j), c);
                }
            }
        }

        contenu.add(panel, BorderLayout.PAGE_START);
        contenu.add(bbar, BorderLayout.SOUTH);
        this.setVisible(true);
    }

    private void getCrises()
    {
        try
        {
            Statement stmt = SqlConnexion.connection.createStatement();
            String query = "SELECT * FROM CRISES";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next())
            {
                BigInteger crise_id = BigInteger.valueOf(rs.getInt("CRISE_ID"));
                String description = rs.getString("DESCRIPTION");

                objets.Crise crise = new Crise(crise_id, description);
                crises.add(crise);
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
        headers.add(new JLabel("Terminer la crise"));
    }

    private JLabel getColumn(Crise crise, int column)
    {
        if (column == 0)
        {
            return new JLabel(crise.getDescription());
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
