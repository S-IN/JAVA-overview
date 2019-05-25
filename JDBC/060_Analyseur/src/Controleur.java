// ==========================================================================
// PROJET GRAPHIQUE JDBC : PROGRAMME COMPLET (Classe Controleur)
// ==========================================================================

import java.sql.*;
import java.util.*;
import utilitairesMG.divers.*;
import utilitairesMG.graphique.LF;
import utilitairesMG.jdbc.*;

public class Controleur
{
    private static BaseDeDonnees base;
    private static AccesBase accesBase;
    private static FenetreAnalyseur maFenetre;

    public static void main(String argv[])
    {
        Vector listeTables;

// --------------------------------------------------------------------------
// Chargement du driver SQL
// --------------------------------------------------------------------------
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Driver inconnu : " + e.getMessage());
            System.exit(0);
        }

// --------------------------------------------------------------------------
// Adresse de la base de donnees a utiliser
// L'utilisation de cette base supppose que le driver correspondant au
// systeme de gestion de la BDD (ici SQL*Server) soit charge. Ici, il est
// charge lors de l'appel de la JVM (Shift-F11).
// --------------------------------------------------------------------------
        base = new BaseDeDonnees(
                "jdbc:sqlserver://mars;databasename=gnmi;" +
                "user=util_bip;password=x");
        accesBase = new AccesBase(base);

        try
        {
            accesBase.getConnection();
        }
        catch (SQLException sqlex)
        {
            System.out.println(sqlex.getMessage());
            System.exit(0);
        }

// --------------------------------------------------------------------------
// Lecture de la liste des tables de la base de donnees
// --------------------------------------------------------------------------
        try
        {
            listeTables = accesBase.getTables();

// --------------------------------------------------------------------------
// Affichage de la fenetre de l'application
// --------------------------------------------------------------------------
            LF.setLF();
            maFenetre
                = new FenetreAnalyseur("Analyseur de requetes", listeTables);
        }
        catch (SQLException sqlex)
        {
            System.out.println("Erreur creation liste tables.\n"
                + sqlex.getMessage());
        }
    }

// ==========================================================================
// Execution d'une requete
// ==========================================================================
    public static void executeRequete(String texteRequete)
    {
        JeuResultat jeuResultat;
        Vector<Colonne> colonnes;
        Vector<Vector<Object>> lignes;

        try
        {
            jeuResultat = accesBase.executeQuery(texteRequete);
            colonnes = jeuResultat.getColonnes();
            lignes = jeuResultat.getLignes();

            maFenetre.afficheSelect(colonnes, lignes);
        }
        catch (SQLException sqlex)
        {
            maFenetre.afficheErreur(sqlex.getMessage());
        }
    }

// ==========================================================================
// Recherche de la liste des colonnes d'une table
// ==========================================================================
    public static void listeColonnes(String texteRequete)
    {
        JeuResultat jeuResultat;
        Vector<Colonne> colonnes;

        try
        {
            jeuResultat = accesBase.executeQuery(texteRequete);
            colonnes = jeuResultat.getColonnes();

            maFenetre.afficheListeColonnes(colonnes);
        }
        catch (SQLException sqlex)
        {
            maFenetre.afficheErreur(sqlex.getMessage());
        }
    }

// ==========================================================================
// Arret de l'application
// ==========================================================================
    public static void arreter()
    {
        try
        {
            accesBase.closeConnection();
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }

        System.exit(0);
    }
}