import java.io.*;
import java.util.*;

public class LectureVersements
{
    public static void main(String args[]) throws IOException
    {
        File dossier;
        File fichierEntree;

// --------------------------------------------------------------------------
// Variables pour la lecture du fichier binaire :
// --------------------------------------------------------------------------
        FichierVersements fv;
        Vector<Versement> listeVersements;

// --------------------------------------------------------------------------
// Nom du fichier a lister
// --------------------------------------------------------------------------
        dossier = new File("C:\\JAVA\\Fichiers");
        fichierEntree = new File(dossier, "versements.dat");

        try
        {
            fv = new FichierVersements(fichierEntree, "r");

            try
            {
                System.out.println("\n\nContenu du fichier "
                    + fichierEntree.getName() + "\n");
                listeVersements = fv.lireListe();

                for (int i = 0; i < listeVersements.size(); i++)
                {
                    System.out.println(listeVersements.elementAt(i));
                }
            }
            finally
            {
                fv.close();
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Le fichier " + fichierEntree.getName()
                + " est inconnu.");
        }
    }
}
