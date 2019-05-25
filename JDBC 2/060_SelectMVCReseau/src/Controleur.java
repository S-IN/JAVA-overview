// ==========================================================================
// Classe Controleur                              Application SelectMVCReseau
// --------------------------------------------------------------------------
// Connexion a un serveur lui meme connecte a un serveur de donnees (3 tiers)
// ==========================================================================

import daoServeurObjets.AccesServeur;
import daoServeurObjets.PriseServeur;
import java.util.*;
import utilitairesMG.divers.*;
import utilitairesMG.jdbc.*;
import java.io.*;
import utilitairesMG.graphique.LF;

public class Controleur
{
    private static PriseServeur priseServeur;
    private static AccesServeur accesServeur;
    private static Fenetre maFenetre;

// ==========================================================================
// Demarrage du controleur
// ==========================================================================
    public static void main(String args[])
    {

// --------------------------------------------------------------------------
// PriseServeur utilisee
// --------------------------------------------------------------------------
        priseServeur = new PriseServeur("localhost", 8189);
        accesServeur = new AccesServeur(priseServeur);

// --------------------------------------------------------------------------
// Affichage de la fenetre de l'application
// --------------------------------------------------------------------------
        LF.setLF();
        maFenetre = new Fenetre("SelectMVCReseau");
    }

// ==========================================================================
// Execution d'une requete
// --------------------------------------------------------------------------
// Dans cet exemple, la connexion est faite et liberee a chaque requete 
// ==========================================================================
    public static void executeRequete(String texteRequete)
    {
        JeuResultat jeuResultat;
        Vector<Colonne> listeColonnes;
        Vector<Vector<Object>> listeLignes;

        try
        {
            jeuResultat = accesServeur.executeQuery(texteRequete);
            listeLignes = jeuResultat.getLignes();
            listeColonnes = jeuResultat.getColonnes();

            maFenetre.afficheTable(listeLignes, listeColonnes);
        }
        catch (ClassNotFoundException e)
        {
            maFenetre.afficheMessage(e.getMessage());
        }
        catch (IOException e)
        {
            maFenetre.afficheMessage(e.getMessage());
        }
    }

// ==========================================================================
// Arret de l'application 
// ==========================================================================
    public static void arreter()
    {
        System.exit(0);
    }
}
