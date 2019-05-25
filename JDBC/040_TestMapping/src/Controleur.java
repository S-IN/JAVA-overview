// ==========================================================================
// CLASSE Controleur                                           Projet Mapping
// ==========================================================================

import java.sql.*;
import java.util.*;
import utilitairesMG.jdbc.*;

public class Controleur
{
    private static BaseDeDonnees base;
    private static AccesBase accesBase;

    public static void main(String args[])
    {
        Contact contact;
        Versement versement;
        Secteur secteur;

        ContactDAO contactDAO;
        VersementDAO versementDAO;
        SecteurDAO secteurDAO;

        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Erreur chargement driver : " + e.getMessage());
            System.exit(0);
        }

// --------------------------------------------------------------------------
// Base(s) de donnees utilisee(s)
// --------------------------------------------------------------------------
        base = new BaseDeDonnees(
                "jdbc:sqlserver://mars;databasename=gnmi;" +
                "user=util_bip;password=x");
        base.setFormatDate("dd/MM/yyyy");
 
        accesBase = new AccesBase(base);

// --------------------------------------------------------------------------
// Creation des objets DAO
// --------------------------------------------------------------------------
        contactDAO = new ContactDAO(accesBase);
        versementDAO = new VersementDAO(accesBase);
        secteurDAO = new SecteurDAO(accesBase);

// --------------------------------------------------------------------------
// Connexion et tests de quelques objets du modele
// --------------------------------------------------------------------------
        try
        {
            accesBase.getConnection();

            try
            {
                contact = new Contact();
                contact.setNumero(100);
                contactDAO.lire(contact);
                System.out.println(contact);

                try
                {
                    secteur = secteurDAO.lireSecteur(contact);
                    if(secteur != null)
                    {
                        System.out.println("Secteur : " 
                            + secteur.getLibelle() + "\n");
                    }
                    else
                    {
                        System.out.println("Secteur non renseigné\n");
                    }
                }
                catch (SQLException e)
                {
                    System.out.println(e.getMessage());
                }

                try
                {
                    Vector<Versement> listeVersements =
                        versementDAO.lireListe(contact);

                    if (listeVersements.size() > 0)
                    {
                        System.out.println("Versements du contact : "
                            + contact.getNom() + "\n");

                        for (int i = 0; i < listeVersements.size(); i++)
                        {
                            versement = listeVersements.elementAt(i);
                            System.out.println("Versement : " 
                                + versement.getNumero());
                        }
                    }
                    else
                    {
                        System.out.println("Pas de versement pour ce contact...");
                    }
                }
                catch (SQLException e)
                {
                    System.out.println(e.getMessage());
                }
            }
            catch (SQLException e)
            {
                System.out.println(e.getMessage());
            }

            finally
            {
                accesBase.closeConnection();
            }
        }
        catch (SQLException e)
        {
            System.out.println("Echec de la connexion : " + e.getMessage());
        }
    }
}