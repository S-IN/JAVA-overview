// ==========================================================================
// Compter combien de fois chaque lettre est utilisee
// --------------------------------------------------------------------------
// Version utilisant la classe StringBuffer (dans la classe String, il n'y a 
// pas de méthode pour detruire un caractere
// ==========================================================================

public class TpStringEx5
{
    public static void main(String argv[])
    {
        int i;                    // Indice du caractere examine
        char c;                   // Caractere examine
        int compteurC;            // Nombre d'occurences du caractere

        StringBuffer chaine = new StringBuffer("JJJJ'AIME JJJAVA");
        System.out.println("AU DEPART  - " + chaine + "\n");

        while (chaine.length() > 0)
        {
            c = chaine.charAt(0);
            compteurC = 0;

            i = 0;

            while (i < chaine.length())
            {
                if (chaine.charAt(i) == c)
                {
                    compteurC++;
                    chaine.deleteCharAt(i);
                }
                else
                {
                    i++;
                }
            }

            System.out.println(
                "Le caractere " + c + " est present " + compteurC + " fois.");
        }
    }
}
