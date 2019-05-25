// ==========================================================================
// CLASSE TriContactsTexte             
// --------------------------------------------------------------------------
// Lecture d'un fichier texte, tri et ecriture formatee dans un autre.
// ==========================================================================

import java.io.*;
import java.util.*;

public class TriContactsTexte
{
    public static void main(String args[]) throws IOException
    {
        File dossier;
        File fichierEntree;
        File fichierSortie;
        BufferedReader entree;
        PrintWriter sortie;
        String ligne;

        ArrayList<Contact> liste = new ArrayList<Contact>();
        Contact contact;

        StringTokenizer token;
        String mot;
        int n;

        dossier = new File("C:\\JAVA\\Fichiers");
        fichierEntree = new File(dossier, "contacts.txt");
        fichierSortie = new File(dossier, "contactsTries.txt");

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
// --------------------------------------------------------------------------
// Creation du contact correspondant a la ligne lue
// --------------------------------------------------------------------------
                        contact = new Contact();

// --------------------------------------------------------------------------
// Traitement de la ligne lue
// --------------------------------------------------------------------------
                        token = new StringTokenizer(ligne, ";");

                        mot = token.nextToken();
                        n = Integer.parseInt(mot);
                        contact.setNumero(n);

                        mot = token.nextToken();
                        contact.setNom(mot);

                        mot = token.nextToken();
                        contact.setAdresse(mot);

                        mot = token.nextToken();
                        contact.setCodePostal(mot);

                        mot = token.nextToken();
                        contact.setVille(mot);

                        mot = token.nextToken();
                        n = Integer.parseInt(mot);
                        contact.setCodeSecteur(n);

// --------------------------------------------------------------------------
// Ajout a la liste
// --------------------------------------------------------------------------
                        liste.add(contact);
                        ligne = entree.readLine();
                    }

// --------------------------------------------------------------------------
// Tri
// --------------------------------------------------------------------------
                    Collections.sort(liste);

// --------------------------------------------------------------------------
// Ecriture dans le fichier texte
// --------------------------------------------------------------------------
                    for (int i = 0; i < liste.size(); i++)
                    {
                        sortie.println(liste.get(i));
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
