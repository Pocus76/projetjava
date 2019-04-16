package util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//----------------------------------------------------------------------------------------------------------------------
/**
 * @author : Pocus
 * @dateCreation : 21/03/2019
 * @description : Classe qui gère l'action du bouton "fermer l'appli"
 */
//----------------------------------------------------------------------------------------------------------------------
public class CloseListener implements ActionListener
{
    public void actionPerformed(ActionEvent e)
    {
        System.exit(0);
    }
}