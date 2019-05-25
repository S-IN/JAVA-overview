// ==========================================================================
// APPLICATION Souris : 
// --------------------------------------------------------------------------
// La classe Fenetre est son propre ecouteur d'evenements MouseEvent.
// L'ecouteur d'evenements WindowEvent est declare dans une classe a part.
// --------------------------------------------------------------------------
// Recuperation des evenements de type MouseEvent (Mouse, Mouse Motion) et
// MouseWheelEvent qui se produisent sur la fenetre.
// ==========================================================================

import javax.swing.SwingUtilities;

public class Souris
{
    private static Fenetre fenetre;
    
    public static void main(String args[])
    {
        SwingUtilities.invokeLater
        (
            new Runnable()
            {
                public void run()
                {
                    fenetre = new Fenetre("Deplacement vertical");
                }
            }
        );
    }
}
