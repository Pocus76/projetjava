package fenetres;

        import mysqlUtil.SqlConnexion;
        import objets.Rapports;

        import util.LogUtils;


        import java.sql.PreparedStatement;
        import java.sql.ResultSet;
        import java.sql.SQLException;
        import java.sql.Statement;
        import javax.swing.*;
        import java.awt.*;
        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;
        import java.awt.event.WindowEvent;

public class Rapport extends JFrame {

    private JLabel Description;
    private JTextArea desc;
    private JButton valider;
    private JButton annuler;
    private JLabel rapport1;
    private JCheckBox rapport;


    public Rapport() {
        super();
        this.setSize(new Dimension(500, 250));
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setUndecorated(true);
        this.setAlwaysOnTop(true);

        JScrollPane jScrollPane = new JScrollPane(new Panel());
        this.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, new Color(153, 86, 0)));
        jScrollPane.setBackground(Color.white);
        jScrollPane.setLayout(null);


        Description = new JLabel("Description");
        jScrollPane.add(Description);
        Description.setBounds(20, 40, 100, 20);


        desc = new JTextArea(10, 2);
        jScrollPane.add(desc);
        desc.setBackground(new java.awt.Color(232, 235, 239));
        desc.setBorder(BorderFactory.createLineBorder(Color.black));
        desc.setBounds(100, 40, 300, 100);

        rapport1 = new JLabel("Succès");
        jScrollPane.add(rapport1);
        rapport1.setBounds(20, 170, 100, 20);

        rapport = new JCheckBox();
        jScrollPane.add(rapport);
        rapport.setBounds(100, 170, 20, 20);

        valider = new JButton("Valider");
        jScrollPane.add(valider);
        valider.setBounds(50, 210, 100, 20);

        annuler = new JButton("Annuler");
        jScrollPane.add(annuler);
        annuler.setBounds(180, 210, 100, 20);
        annuler.addActionListener(new Rapport.AnnulationListener());


        this.getContentPane().add(jScrollPane);


        this.setVisible(true);
    }


    public class ValidationListener implements ActionListener {
        //--------------------------------------------------------------------------------------------------------------
        //--------------------------------------------------------------------------------------------------------------
        public void actionPerformed(ActionEvent e) {

        }

        }

    public class AnnulationListener implements ActionListener {
        //--------------------------------------------------------------------------------------------------------------
        //--------------------------------------------------------------------------------------------------------------
        public void actionPerformed(ActionEvent e) {
            Rapport.this.dispatchEvent(new WindowEvent(Rapport.this, WindowEvent.WINDOW_CLOSING));
        }
    }
}

