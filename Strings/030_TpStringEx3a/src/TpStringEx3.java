// ==========================================================================
// Remplacer JAVA par C++.
// ==========================================================================

public class TpStringEx3
{
    public static void main(String arg[])
    {
        String chaine;

        chaine = "J'utilise JAVA avec brio !";
        System.out.println("AU DEPART : " + chaine);

// --------------------------------------------------------------------------
// Remplacement de java par c++, avec replaceAll(). Mais attention aux 
// expressions regulieres !!!
// --------------------------------------------------------------------------
        chaine = chaine.replaceAll("JAVA", "C++");
        System.out.println("A LA FIN  : " + chaine);
        
        chaine = chaine.replaceAll("C++", "JAVA");
        System.out.println("A LA FIN  : " + chaine);
    }
}
