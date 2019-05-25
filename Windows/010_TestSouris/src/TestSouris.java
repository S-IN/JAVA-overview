// ==========================================================================
// APPLICATION TestSouris : Test des evenements MouseEvent
// --------------------------------------------------------------------------
// La classe Fenetre est son propre ecouteur d'evenements MouseEvent.
// L'ecouteur d'evenements WindowEvent est declare dans une classe a part.
// --------------------------------------------------------------------------
// Visualisation des evenements de type MouseEvent (Mouse, Mouse Motion).
// ==========================================================================
import javax.swing.SwingUtilities;

public class TestSouris
{
    public static void main(String args[])
    {
        SwingUtilities.invokeLater
        (
            new Runnable()
            {
                public void run()
                {
                    Fenetre fenetre = new Fenetre("Evenements souris");
                }
            }
        );
    }
}
