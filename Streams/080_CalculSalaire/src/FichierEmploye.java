
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class FichierEmploye
{
    public static ArrayList<Employe> lireEmployes(String nomFichier)
        throws IOException
    {
        File dossier;
        File fichierEntree;

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
        FichierEmploye fe;
        Employe employe;
        Integer matricule;
        String nom;
        Integer nombreHeures;
        Double salaireHoraire;
        Integer nombreHeuresSup;
        Double salaire;
        Double ca;
        
        ArrayList<Employe> liste = new ArrayList<Employe>();

// --------------------------------------------------------------------------
// Ouverture du fichier d'entree et boucle de lecture/remplissage du vecteur
// --------------------------------------------------------------------------
        dossier = new File("C:\\JAVA\\Fichiers");
        fichierEntree = new File(dossier, nomFichier);

        try
        {
            entree = new BufferedReader(
                new InputStreamReader(
                    new FileInputStream(fichierEntree), "Cp1252"));
            try
            {
                ligne = entree.readLine();

                while (ligne != null)
                {

// --------------------------------------------------------------------------
// Traitement de la ligne lue :
// --------------------------------------------------------------------------
                    token = new StringTokenizer(ligne, ";");

// --------------------------------------------------------------------------
// Extraction des proprietes du Contact :
// --------------------------------------------------------------------------
                    mot = token.nextToken();
                    matricule = Integer.parseInt(mot);
                    nom = token.nextToken();
                    mot = token.nextToken();
                    
                    if (mot.compareTo("h") == 0)
                    {
                        mot = token.nextToken();
                        nombreHeures = new Integer(mot);
                        mot = token.nextToken();
                        salaireHoraire = new Double(mot);
                        mot = token.nextToken();
                        nombreHeuresSup = new Integer(mot);
                        employe = new EmployeHoraire(matricule, 
                                                     nom, 
                                                     nombreHeures, 
                                                     salaireHoraire, 
                                                     nombreHeuresSup);
                    }
                    else
                    {
                        mot = token.nextToken();
                        salaire = new Double(mot);
                        mot = token.nextToken();
                        ca = new Double(mot);
                        employe = 
                            new EmployeCommercial(matricule, nom, salaire, ca);
                    }

                    liste.add(employe);

                    ligne = entree.readLine();
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

        return liste;
    }
}
