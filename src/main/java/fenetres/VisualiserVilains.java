package fenetres;

import objets.Incident;
import objets.SuperCivil;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class VisualiserVilains extends JFrame {
    ArrayList<SuperCivil> superVilains = new ArrayList<SuperCivil>();

    public VisualiserVilains(Incident incident) {
        super();
        superVilains = incident.getSuperVilains();
        this.setSize(new Dimension(1300, 700));
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        Container contenu = this.getContentPane();
        this.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, new Color(153, 86, 10, 10)));
        contenu.setBackground(Color.white);


        if (superVilains.size() == 0) {
            contenu.setLayout(null);
            JLabel emptyText = new JLabel("Aucun super vilain associé");
            contenu.add(emptyText);
            emptyText.setFont(new Font(emptyText.getFont().getName(), Font.BOLD, 15));
            emptyText.setBounds(65, 10, 180, 20);
        } else {
            contenu.setLayout(new GridLayout(0, 5));
            for (int i = 0; i < superVilains.size(); i += 1) {
                if (i == 0) {
                    contenu.add(new JLabel("Nom"));
                    contenu.add(new JLabel("Pouvoirs"));
                    contenu.add(new JLabel("Faiblesses"));
                    contenu.add(new JLabel("Score"));
                    contenu.add(new JLabel("Commentaire"));
                }
                SuperCivil superVilain = superVilains.get(i);
                contenu.add(new JLabel(superVilain.getNom()));
                contenu.add(new JLabel(superVilain.getPouvoir()));
                contenu.add(new JLabel(superVilain.getFaiblesse()));
                contenu.add(new JLabel(superVilain.getScore().toString()));
                contenu.add(new JLabel(superVilain.getCommentaire()));
            }
        }

        this.setVisible(true);
    }
}
