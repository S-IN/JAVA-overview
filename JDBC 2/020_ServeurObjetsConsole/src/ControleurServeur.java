// ==========================================================================
// Classe ControleurServeur    
// --------------------------------------------------------------------------
// Controle (demarrage et arret) du programme serveur.
// ==========================================================================

import java.io.*;
import utilitairesMG.jdbc.*;
import utilitairesMG.divers.*;

public class ControleurServeur
{
    public static void main(String[] argv) throws IOException
    {
        ServeurObjets serveur;
        BaseDeDonnees base;

// --------------------------------------------------------------------------
// Chargement du driver SQL
// --------------------------------------------------------------------------
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

// --------------------------------------------------------------------------
// Base de donnees utilisee par le serveur, ouverture de la connexion
// --------------------------------------------------------------------------
            base = new BaseDeDonnees("jdbc:sqlserver://mars;user=UTIL_BIP;"
                + "password=x;databasename=gnmi");
            base.setFormatDate("dd/MM/yyyy");

// --------------------------------------------------------------------------
// Lancement du serveur
// --------------------------------------------------------------------------
            serveur = new ServeurObjets(base);

            System.out.println("Demarrage du serveur d'objets...");
            serveur.start();

            System.out.println("\nTapez Entree pour l'arreter...\n");
            Clavier.readString();

            serveur.interrupt();
            System.out.println("Le serveur a ete arrete...");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
    }
}