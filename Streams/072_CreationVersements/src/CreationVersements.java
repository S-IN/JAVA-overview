// ==========================================================================
// CreationVersements : Creation du fichier des versements (versements.dat)
// --------------------------------------------------------------------------
// Ce fichier binaire est cree en lisant le fichier texte versements.txt
// ==========================================================================

import java.io.*;
import java.text.*;
import utilitairesMG.divers.*;
import java.util.*;

public class CreationVersements
{
    public static void main(String args[]) throws IOException
    {
        File dossier;
        File fichierEntree;
        File fichierSortie;

// --------------------------------------------------------------------------
// Variables pour la lecture et l'analyse du fichier texte en entree :
// --------------------------------------------------------------------------
        BufferedReader entree;
        String ligne;

        StringTokenizer token;
        String mot;
        int n;

// --------------------------------------------------------------------------
// Variables pour l'ecriture dans le fichier binaire en sortie :
// --------------------------------------------------------------------------
        FichierVersements fv;
        Versement versement;

// --------------------------------------------------------------------------
// Vecteur pour trier les versements par date avant creation du fichier :
// --------------------------------------------------------------------------
        Vector<Comparable> vVersements = new Vector<Comparable>();

// --------------------------------------------------------------------------
// Ouverture du fichier d'entree et boucle de lecture/remplissage du vecteur
// --------------------------------------------------------------------------
        dossier = new File("C:\\JAVA\\Fichiers");
        fichierEntree = new File(dossier, "versements.txt");
        fichierSortie = new File(dossier, "versements.dat");

        try
        {
            entree = new BufferedReader(
                new InputStreamReader(
                    new FileInputStream(fichierEntree), "Cp1252"));

// --------------------------------------------------------------------------
// Remplissage du vecteur d'objets Versement :
// --------------------------------------------------------------------------
            try
            {
                ligne = entree.readLine();

                while (ligne != null)
                {

// --------------------------------------------------------------------------
// Creation du contact correspondant a la ligne lue
// --------------------------------------------------------------------------
                    versement = new Versement();

// --------------------------------------------------------------------------
// Traitement de la ligne lue :
// --------------------------------------------------------------------------
                    token = new StringTokenizer(ligne, ";");

// --------------------------------------------------------------------------
// Extraction des proprietes du Contact :
// --------------------------------------------------------------------------
                    mot = token.nextToken();
                    n = Integer.parseInt(mot);
                    versement.setNumero(n);

                    mot = token.nextToken();
                    try
                    {
                        DateFr dateFr = new DateFr(mot);
                        versement.setDate(dateFr.getTime());
                    }
                    catch (ParseException e)
                    {
                    }

                    mot = token.nextToken();
                    versement.setMontant(new Double(mot));

                    mot = token.nextToken();
                    n = Integer.parseInt(mot);
                    versement.setNumeroContact(n);

// --------------------------------------------------------------------------
// Ajout du Versement au vecteur :
// --------------------------------------------------------------------------
                    System.out.println(versement);
                    vVersements.addElement(versement);

                    ligne = entree.readLine();
                }

// --------------------------------------------------------------------------
// Tri du vecteur :
// --------------------------------------------------------------------------
                Collections.sort(vVersements);

// --------------------------------------------------------------------------
// Recopie du vecteur de Versements dans le fichier binaire :
// --------------------------------------------------------------------------
                fv = new FichierVersements(fichierSortie, "rw");

                try
                {
                    for (int i = 0; i < vVersements.size(); i++)
                    {
                        fv.ecrire((Versement) vVersements.elementAt(i));
                    }
                }
                finally
                {
                    fv.close();
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
                + " n'existe pas");
        }
    }
}
