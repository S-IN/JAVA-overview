// ==========================================================================
// Connexion a une base de donnees SQL Server et affichage d'une requete.
// ==========================================================================

import java.sql.*;
import java.util.*;
import utilitairesMG.jdbc.*;
import utilitairesMG.divers.*;

public class Controleur
{
    private static BaseDeDonnees base;
    private static AccesBase accesBase;
    private static Fenetre maFenetre;

// ==========================================================================
// Demarrage du controleur
// ==========================================================================
    public static void main(String args[])
    {

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
// Base(s) de donnees utilisee(s)
// --------------------------------------------------------------------------
        base = new BaseDeDonnees("jdbc:sqlserver://mars;user=UTIL_BIP;"
            + "password=x;databasename=gnmi");
        base.setFormatDate("dd/MM/yyyy");
        accesBase = new AccesBase(base);

// --------------------------------------------------------------------------
// Affichage de la fenetre de l'application
// --------------------------------------------------------------------------
        maFenetre = new Fenetre("SelectMVC");
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
            accesBase.getConnection();

            try
            {
                jeuResultat = accesBase.executeQuery(texteRequete);
                listeLignes = jeuResultat.getLignes();
                listeColonnes = jeuResultat.getColonnes();

                maFenetre.afficheTable(listeLignes, listeColonnes);
            }
            catch (SQLException e)
            {
                maFenetre.afficheMessage(e.getMessage());
            }
            finally
            {
                accesBase.closeConnection();
            }
        }
        catch (SQLException e)
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