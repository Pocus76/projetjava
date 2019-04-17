package fenetres;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;


public class Litige extends JFrame {




    public Litige(){
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Litige");
        this.setSize(800, 700);

        //Les données du tableau
        Object[][] data = {
                {"kjh", "2kjhns", "kjh","lol"},
                {"Bkjhde", "2kjhkjhns", "1kjh","jpp"},
                {"IamkjhBow", "kjhns", "kjh","jtb"},
                {"FunkjhMan", "3jh", "kjhm","xd"}
        };

        //Les titres des colonnes
        String  table[] = {"Mission", "Message", "Dégât","Intérêt"};
        JTable tableau = new JTable(data, title);
        //Nous ajoutons notre tableau à notre contentPane dans un scroll
        //Sinon les titres des colonnes ne s'afficheront pas !
        this.getContentPane().add(new JScrollPane(tableau));


        this.setVisible(true);
    }

}
