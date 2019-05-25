// ==========================================================================
// Classe Controleur                                Application ServeurObjets
// --------------------------------------------------------------------------
// Controleur : programme principal de lancement du serveur.
// ==========================================================================

import utilitairesMG.jdbc.*;
import utilitairesMG.graphique.*;

public class Controleur
{

// --------------------------------------------------------------------------
// Le controleur connait :
//    la fenetre de l'application
//    le serveur
//    la base de donnees qui sera utilisee dans les threads clients
// --------------------------------------------------------------------------
    private static Fenetre maFenetre;
    private static ServeurObjets serveur;
    private static BaseDeDonnees base;

// --------------------------------------------------------------------------
// Lancement du serveur
// --------------------------------------------------------------------------
    public static void main(String args[])
    {

// --------------------------------------------------------------------------
// Chargement du driver SQL
// --------------------------------------------------------------------------
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

// --------------------------------------------------------------------------
// Base de donnees utilisee par le serveur
// --------------------------------------------------------------------------
            base = new BaseDeDonnees("jdbc:sqlserver://mars;user=UTIL_BIP;"
                + "password=x;databasename=gnmi");
            base.setFormatDate("dd/MM/yyyy");
            
// --------------------------------------------------------------------------
// Affichage de l'ecran d'accueil
// --------------------------------------------------------------------------
            javax.swing.SwingUtilities.invokeLater
            (
                new Runnable()
                {
                    public void run()
                    {
                        LF.setLF();
                        maFenetre = new Fenetre("Serveur d'objets");
                    }
                }
            );
        }
        catch (ClassNotFoundException e)
        {
            System.out.println(e.getMessage() + " : Driver inconnu !");
        } 
    }

// --------------------------------------------------------------------------
// Demarrage du serveur
// --------------------------------------------------------------------------
    public static void demarrerServeur()
    {
        serveur = new ServeurObjets(base);
        serveur.start();
    }
    
    public static void serveurDemarre()
    {
        maFenetre.allume();
    }

// --------------------------------------------------------------------------
// Arret du serveur
// --------------------------------------------------------------------------
    public static void arreterServeur()
    {
        serveur.interrupt();
    }

    public static void serveurArrete()
    {
        maFenetre.eteint();
    }

// --------------------------------------------------------------------------
// Envoi de messages a l'ecran
// --------------------------------------------------------------------------
    public static void traiterTexte(String s)
    {
        maFenetre.afficheTexte(s);
    }

// --------------------------------------------------------------------------
// Arret de l'application
// --------------------------------------------------------------------------
    public static void arreter()
    {
        System.exit(0);
    }
}