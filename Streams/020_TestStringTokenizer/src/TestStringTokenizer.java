// ========================================================================
// APPLICATION TestStringTokenizer :
// ========================================================================

import java.util.*;
import java.io.*;

public class TestStringTokenizer
{
    public static void main(String[] args) throws IOException
    {
        String ligne;
        StringTokenizer token;
        int nombreUnites;

        ligne = "Je suis;une hirondelle;de cheminée";
        token = new StringTokenizer(ligne, ";");

        nombreUnites = token.countTokens();
        System.out.print("La chaine : \"" + ligne + "\"");
        System.out.println(" a " + nombreUnites + " unités séparées par des ;");

        System.out.println("\nCes unités sont :");
        
        while (token.hasMoreTokens())
        {
            System.out.println(token.nextToken());
        }
    }
}
