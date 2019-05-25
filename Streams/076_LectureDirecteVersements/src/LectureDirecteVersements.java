// ==========================================================================
// LectureDirecteVersements
// ==========================================================================

import java.io.*;
import utilitairesMG.divers.*;

public class LectureDirecteVersements
{
    public static void main(String args[]) throws IOException
    {
        File dossier;
        File fichier;

        FichierVersements fv;
        int numEnreg;
        boolean finSaisie = false;
        
        Versement versement = new Versement();

// --------------------------------------------------------------------------
// Ouverture du fichier
// --------------------------------------------------------------------------
        dossier = new File("C:\\JAVA\\Fichiers");
        fichier = new File(dossier, "versements.dat");

        try
        {
            fv = new FichierVersements(fichier, "rw");

// --------------------------------------------------------------------------
// Enregistrement a modifier :
// --------------------------------------------------------------------------
            try
            {
                System.out.print("Numero d'enregistrement a lire : ");
                numEnreg = Clavier.readInt();
                
                do
                {
                    try
                    {
                        fv.lire(versement, numEnreg);
                        System.out.println(versement);
                        finSaisie = true;
                    }
                    catch (EOFException e)  
                    {
                        System.out.println(numEnreg 
                            + " depasse le dernier indice du fichier ("
                            + ((fichier.length() / 27) - 1) + "). ");
                        System.out.print("Numero d'enregistrement a lire : ");
                        numEnreg = Clavier.readInt();
                    }
                    catch (IOException e)
                    {
                        System.out.println(
                            "Indice negatif...");
                        System.out.print("Numero d'enregistrement a lire : ");
                        numEnreg = Clavier.readInt();
                    }
                } 
                while (!finSaisie);
            }
            catch (NumberFormatException e)
            {
                System.out.println("Indice incorrect...");
            }
            finally
            {
                System.out.println("Fermeture");
                fv.close();
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Le fichier " + fichier.getName()
                + " est inconnu.");
        }
    }
}
