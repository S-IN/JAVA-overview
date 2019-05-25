// ==========================================================================
// Classe Controle1                                     Application Controle1
// -------------------------------------------------------------------------- 
// Fenetre avec 2 CASES A COCHER et deux Labels pour avoir l'etat des cases
// Utilisation de GridLayout et FlowLayout...
// ==========================================================================

public class Controle1
{
    public static void main(String args[])
    {
        javax.swing.SwingUtilities.invokeLater
        (
            new Runnable() 
            {
                public void run() 
                {
                    Fenetre fenetre = new Fenetre("2 CASES A COCHER");
                }
            }
        );
    }
}