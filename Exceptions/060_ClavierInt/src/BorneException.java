// ==========================================================================
// BorneException : Exception personnelle
// ==========================================================================

public class BorneException extends Exception
{
    public BorneException(int valeur, int min, int max)
    {
        super("Valeur " + valeur + " hors de [" + min + ", " + max + "]");
    }
}