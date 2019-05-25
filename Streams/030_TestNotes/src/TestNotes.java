// ==========================================================================
// TestNotes : lecture d'un fichier texte, calcul et ecriture formatee
//             dans un autre.
// --------------------------------------------------------------------------
// Version complete.
// ==========================================================================

import java.io.*;
import java.util.*;

public class TestNotes
{
    public static void main(String args[]) throws IOException
    {
        File dossier;
        File fichierEntree;
        File fichierSortie;
        BufferedReader entree;
        PrintWriter sortie;
        String ligne;

        String nom;
        int totalNotes;
        int compteurNotes;

        StringTokenizer token;

        dossier = new File("C:\\JAVA\\fichiers");
        fichierEntree = new File(dossier, "notes.txt");
        fichierSortie = new File(dossier, "resultats.txt");

        try
        {
            entree = new BufferedReader(
                new InputStreamReader(
                    new FileInputStream(fichierEntree), "Cp1252"));
            try
            {
                sortie = new PrintWriter(fichierSortie, "Cp1252");
                try
                {
                    ligne = entree.readLine();

                    while (ligne != null)
                    {
                        token = new StringTokenizer(ligne, ";");
                        nom = token.nextToken();

                        totalNotes = 0;
                        compteurNotes = 0;

                        while (token.hasMoreTokens())
                        {
                            totalNotes += Integer.parseInt(token.nextToken());
                            compteurNotes++;
                        }

                        sortie.println("Le total des " + compteurNotes
                            + " notes de " + nom
                            + " est de : " + totalNotes);
                        ligne = entree.readLine();
                    }
                }
                finally
                {
                    sortie.close();
                }
            }
            finally
            {
                entree.close();
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Le fichier " + fichierEntree.getName()
                + " est inconnu.");
        }
    }
}
