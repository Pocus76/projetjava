package fenetres;
//----------------------------------------------------------------------------------------------------------------------

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author :
 * @dateCreation :
 * @description :
 */
//----------------------------------------------------------------------------------------------------------------------
public class CloseListener implements ActionListener
{
    public void actionPerformed(ActionEvent e)
    {
        System.exit(0);
    }
}