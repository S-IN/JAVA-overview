// ==========================================================================
// Remplacer les espaces par des _  (underscore).					 
// ==========================================================================

public class TpStringEx2
{
    public static void main(String arg[])
    {
        String chaine;

        chaine = "J'utilise JAVA avec brio !";
        System.out.println("AU DEPART : " + chaine);

        chaine = chaine.replace(' ', '_');  // La reaffectation de chaine est
                                            // necessaire car replace() renvoie
                                            // une NOUVELLE chaine
        System.out.println("A LA FIN  : " + chaine);
    }
}
