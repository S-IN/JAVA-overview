// ==========================================================================
// Classe Controleur                                Projet GestionContactJdbc
// ==========================================================================

import java.util.*;
import utilitairesMG.divers.*;
import utilitairesMG.graphique.*;
import utilitairesMG.jdbc.*;
import java.sql.*;
import daoJdbcMapping.*;
import metierMapping.*;

public class Controleur
{

// --------------------------------------------------------------------------
// PROPRIETES
// --------------------------------------------------------------------------
    private static Fenetre maFenetre;
    private static ContactDAO contactDAO;
    private static SecteurDAO secteurDAO;
    private static BaseDeDonnees base;
    private static AccesBase accesBase;

// --------------------------------------------------------------------------
// Methode main : lancement de l'application
// --------------------------------------------------------------------------
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
        base = new BaseDeDonnees(
                "jdbc:sqlserver://mars;databasename=gnmi;" +
                "user=util_bip;password=x");
        base.setFormatDate("dd/MM/yyyy");

        accesBase = new AccesBase(base);

// --------------------------------------------------------------------------
// Creation des objets DAO
// --------------------------------------------------------------------------
        contactDAO = new ContactDAO(accesBase);
        secteurDAO = new SecteurDAO(accesBase);

// --------------------------------------------------------------------------
// Affichage de la fenetre de l'application
// --------------------------------------------------------------------------
        javax.swing.SwingUtilities.invokeLater
        (
            new Runnable()
            {
                public void run()
                {
                    LF.setLF();
                    maFenetre = new Fenetre("GestionContactJdbc");
                }
            }
        );
    }

// --------------------------------------------------------------------------
// Creation du vecteur des contacts et du vecteur des colonnes a afficher.
// Appel de l'affichage de la fenetre contact...
// --------------------------------------------------------------------------
    public static void demandeContacts()
    {
        Vector<Contact> listeContacts;
        Vector<Colonne> listeColonnes;
        Vector<Secteur> listeSecteurs;

        try
        {
            accesBase.getConnection();

            try
            {
                listeContacts = contactDAO.lireListe();
                listeColonnes = contactDAO.getListeColonnes();
                listeSecteurs = secteurDAO.lireListe();

                maFenetre.afficheContacts(listeContacts,
                                          listeColonnes,
                                          listeSecteurs);
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

// --------------------------------------------------------------------------
// Creation du vecteur des versements et du vecteur des colonnes a afficher.
// --------------------------------------------------------------------------
    public static void demandeVersements()
    {
        maFenetre.afficheMessage("Gestion des versements non réalisée");
    }

// --------------------------------------------------------------------------
// Creation du vecteur des secteurs et du vecteur des colonnes a afficher.
// --------------------------------------------------------------------------
    public static void demandeSecteurs()
    {
        maFenetre.afficheMessage("Gestion des secteurs non réalisée");
    }

// --------------------------------------------------------------------------
// Mise a jour de la table CONTACT.
// --------------------------------------------------------------------------
// Cette methode est appelee lors de la fermeture de la fenetre interne
// Contact.
// --------------------------------------------------------------------------
    public static void majContacts(Vector<Contact> contactsInseres,
                                   Vector<Contact> contactsModifies,
                                   Vector<Contact> contactsSupprimes)
    {
        Contact contact;
        int i;
        Connection connexion;

// --------------------------------------------------------------------------
// Mise a jour de l'affichage de la fenetre principale. Ici, cela consiste
// a revalider le menu d'affichage de la table CONTACT.
// --------------------------------------------------------------------------
        maFenetre.valideItemContact();

        try
        {

// --------------------------------------------------------------------------
// Transaction : cela suppose que la connexion est gardee entre chaque
// operation (creation, modification...). Sinon, la connexion etant perdue
// apres l'operation et le commit non fait, la modif est perdue...
// --------------------------------------------------------------------------
            connexion = accesBase.getConnection();
            connexion.setAutoCommit(false);

            try
            {
// --------------------------------------------------------------------------
// 1. Destruction des contacts du vecteur contactsSupprimes.
// --------------------------------------------------------------------------
                for (i = 0; i < contactsSupprimes.size(); i++)
                {
                    contact = contactsSupprimes.elementAt(i);
                    try
                    {
                        contactDAO.detruire(contact);
                    }
                    catch (SQLException e)
                    {
                        maFenetre.afficheMessage(e.getMessage());
                    }
                }

// --------------------------------------------------------------------------
// 2. Sauvegarde du vecteur des Contacts inseres dans la base de donnees.
// --------------------------------------------------------------------------
                for (i = 0; i < contactsInseres.size(); i++)
                {
                    contact = contactsInseres.elementAt(i);

                    try
                    {
                        contactDAO.creer(contact);
                    }
                    catch (SQLException e)
                    {
                        maFenetre.afficheMessage(e.getMessage());
                    }
                }

// --------------------------------------------------------------------------
// 3. Sauvegarde du vecteur des Contacts modifies dans la base de donnees.
// --------------------------------------------------------------------------
                for (i = 0; i < contactsModifies.size(); i++)
                {
                    contact = contactsModifies.elementAt(i);

                    try
                    {
                        contactDAO.modifier(contact);
                    }
                    catch (SQLException e)
                    {
                        maFenetre.afficheMessage(e.getMessage());
                    }
                }

// --------------------------------------------------------------------------
// Fin de la transaction.
// --------------------------------------------------------------------------
                connexion.commit();
            }
            finally
            {
                connexion.close();
            }
        }
        catch (SQLException e)
        {
            maFenetre.afficheMessage(e.getMessage());
        }
    }

// --------------------------------------------------------------------------
// Arret de l'application.
// --------------------------------------------------------------------------
    public static void arreter()
    {
        System.exit(0);
    }
}
