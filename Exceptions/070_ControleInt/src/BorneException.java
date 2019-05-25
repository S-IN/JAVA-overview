// ==========================================================================
// BorneException : Exception personnelle
// ==========================================================================

public class BorneException extends Exception
{
    private static final long serialVersionUID = 17L;   // Pour le warning de Java 6

    public BorneException(int valeur, int min, int max)
    {
        super("Valeur " + valeur + " hors de [" + min + ", " + max + "]");
    }
}