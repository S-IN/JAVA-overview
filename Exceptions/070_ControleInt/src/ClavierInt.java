// ==========================================================================
// ClavierInt : saisie clavier et test des bornes
// ==========================================================================

import utilitairesMG.divers.*;

public class ClavierInt
{
// --------------------------------------------------------------------------
// Lecture d'un entier :
// --------------------------------------------------------------------------
    public static int readInt(int min, int max) throws BorneException
    {
        int retour;

        retour = Clavier.readInt();
        if ((retour < min) || (retour > max))
        {
            throw new BorneException(retour, min, max);
        }
        return retour;
    }
}