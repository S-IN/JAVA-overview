// ==========================================================================
// Classe Controleur                                Projet GestionBaseMapping
// ==========================================================================

import java.sql.*;
import java.util.*;
import utilitairesMG.divers.*;
import utilitairesMG.graphique.*;
import utilitairesMG.jdbc.*;
import daoJdbcMapping.*;
import metierMapping.*;

public class Controleur
{

// --------------------------------------------------------------------------
// PROPRIETES
// --------------------------------------------------------------------------
    private static BaseDeDonnees base;
    private static AccesBase accesBase;
    private static Fenetre maFenetre;

    private static SecteurDAO secteurDAO;
    private static ContactDAO contactDAO;
    private static VersementDAO versementDAO;

// --------------------------------------------------------------------------
// Methode main : lancement de l'application
// --------------------------------------------------------------------------
    public static void main(String args[])
    {

// --------------------------------------------------------------------------
// Stockage du nom du driver SQL
// --------------------------------------------------------------------------
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println(e.getMessage());
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
        secteurDAO = new SecteurDAO(accesBase);
        contactDAO = new ContactDAO(accesBase);
        versementDAO = new VersementDAO(accesBase);

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
                    maFenetre = new Fenetre("GestionBaseMapping");
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
        Vector<Versement> listeVersements;
        Vector<Colonne> listeColonnes;

        try
        {
            accesBase.getConnection();

            try
            {
                listeVersements = versementDAO.lireListe();
                listeColonnes = versementDAO.getListeColonnes();

                maFenetre.afficheVersements(listeVersements,
                                            listeColonnes);
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
// Creation du vecteur des secteurs et du vecteur des colonnes a afficher.
// --------------------------------------------------------------------------
    public static void demandeSecteurs()
    {
        Vector<Secteur> listeSecteurs;
        Vector<Colonne> listeColonnes;

        try
        {
            accesBase.getConnection();

            try
            {
                listeSecteurs = secteurDAO.lireListe();
                listeColonnes = secteurDAO.getListeColonnes();

                maFenetre.afficheSecteurs(listeSecteurs,
                                          listeColonnes);
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
        int nombreDeContacts;
        int i;
        int nombreModifs;
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

// --------------------------------------------------------------------------
// 1. Destruction des contacts du vecteur contactsSupprimes.
// --------------------------------------------------------------------------
            try
            {
                for (i = 0; i < contactsSupprimes.size(); i++)
                {
                    contact = contactsSupprimes.elementAt(i);
                    try
                    {
                        nombreModifs = contactDAO.detruire(contact);
                    }
                    catch (SQLException e)
                    {
                        maFenetre.afficheMessage(e.getMessage());
                    }
                }

// --------------------------------------------------------------------------
// 2. Sauvegarde du vecteur des Contacts inseres dans la base de donnees.
// --------------------------------------------------------------------------
                nombreDeContacts = contactsInseres.size();

                for (i = 0; i < nombreDeContacts; i++)
                {
                    contact = contactsInseres.elementAt(i);

                    try
                    {
                        nombreModifs = contactDAO.creer(contact);
                    }
                    catch (SQLException e)
                    {
                        maFenetre.afficheMessage(e.getMessage());
                    }
                }

// --------------------------------------------------------------------------
// 3. Sauvegarde du vecteur des Contacts modifies dans la base de donnees.
// --------------------------------------------------------------------------
                nombreDeContacts = contactsModifies.size();

                for (i = 0; i < nombreDeContacts; i++)
                {
                    contact = contactsModifies.elementAt(i);

                    try
                    {
                        nombreModifs = contactDAO.modifier(contact);
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
// Mise a jour de la table VERSEMENT.
// --------------------------------------------------------------------------
// Cette methode est appelee lors de la fermeture de la fenetre interne
// Versement.
// --------------------------------------------------------------------------
    public static void majVersements(Vector<Versement> versementsInseres,
                                     Vector<Versement> versementsModifies,
                                     Vector<Versement> versementsSupprimes)
    {
        Versement versement;
        int nombreDeVersements;
        int i;
        int nombreModifs;
        Connection connexion;

// --------------------------------------------------------------------------
// Mise a jour de l'affichage de la fenetre principale. Ici, cela consiste
// a revalider le menu d'affichage de la table VERSEMENT.
// --------------------------------------------------------------------------
        maFenetre.valideItemVersement();

        try
        {

// --------------------------------------------------------------------------
// Transaction : cela suppose que la connexion est gardee entre chaque
// operation (creation, modification...). Sinon, la connexion etant perdue
// apres l'operation et le commit non fait, la modif est perdue...
// --------------------------------------------------------------------------
            connexion = accesBase.getConnection();
            connexion.setAutoCommit(false);

// --------------------------------------------------------------------------
// 1. Destruction des Versements du vecteur versementsSupprimes.
// --------------------------------------------------------------------------
            try
            {
                for (i = 0; i < versementsSupprimes.size(); i++)
                {
                    versement = versementsSupprimes.elementAt(i);
                    try
                    {
                        nombreModifs = versementDAO.detruire(versement);
                        System.out.println("Destruction : " + nombreModifs);
                    }
                    catch (SQLException e)
                    {
                        System.out.println(e.getMessage());
                    }
                }

// --------------------------------------------------------------------------
// 2. Sauvegarde du vecteur des Versements inseres dans la base de donnees.
// --------------------------------------------------------------------------
                nombreDeVersements = versementsInseres.size();

                for (i = 0; i < nombreDeVersements; i++)
                {
                    versement = versementsInseres.elementAt(i);

                    try
                    {
                        nombreModifs = versementDAO.creer(versement);
                        System.out.println("Insertion : " + nombreModifs);
                    }
                    catch (SQLException e)
                    {
                        System.out.println(e.getMessage());
                    }
                }

// --------------------------------------------------------------------------
// 3. Sauvegarde du vecteur des Versements modifies dans la base de donnees.
// --------------------------------------------------------------------------
                nombreDeVersements = versementsModifies.size();

                for (i = 0; i < nombreDeVersements; i++)
                {
                    versement = versementsModifies.elementAt(i);

                    try
                    {
                        nombreModifs = versementDAO.modifier(versement);
                        System.out.println("Modif : " + nombreModifs);
                    }
                    catch (SQLException e)
                    {
                        System.out.println(e.getMessage());
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
            System.out.println(e.getMessage());
        }
    }

// --------------------------------------------------------------------------
// Mise a jour de la table SECTEUR.
// --------------------------------------------------------------------------
// Cette methode est appelee lors de la fermeture de la fenetre interne
// Contact.
// --------------------------------------------------------------------------
    public static void majSecteurs(Vector<Secteur> secteursInseres,
                                   Vector<Secteur> secteursModifies,
                                   Vector<Secteur> secteursSupprimes)
    {
        Secteur secteur;
        int nombreDeSecteurs;
        int i;
        int nombreModifs;
        Connection connexion;

// --------------------------------------------------------------------------
// Mise a jour de l'affichage de la fenetre principale. Ici, cela consiste
// a revalider le menu d'affichage de la table CONTACT.
// --------------------------------------------------------------------------
        maFenetre.valideItemSecteur();

        try
        {

// --------------------------------------------------------------------------
// Transaction : cela suppose que la connexion est gardee entre chaque
// operation (creation, modification...). Sinon, la connexion etant perdue
// apres l'operation et le commit non fait, la modif est perdue...
// --------------------------------------------------------------------------
            connexion = accesBase.getConnection();
            connexion.setAutoCommit(false);

// --------------------------------------------------------------------------
// 1. Destruction des Secteurs du vecteur secteursSupprimes.
// --------------------------------------------------------------------------
            try
            {
                for (i = 0; i < secteursSupprimes.size(); i++)
                {
                    secteur = secteursSupprimes.elementAt(i);
                    try
                    {
                        nombreModifs = secteurDAO.detruire(secteur);
                        System.out.println("Destruction : " + nombreModifs);
                    }
                    catch (SQLException e)
                    {
                        System.out.println(e.getMessage());
                    }
                }

// --------------------------------------------------------------------------
// 2. Sauvegarde du vecteur des Secteurs inseres dans la base de donnees.
// --------------------------------------------------------------------------
                nombreDeSecteurs = secteursInseres.size();

                for (i = 0; i < nombreDeSecteurs; i++)
                {
                    secteur = secteursInseres.elementAt(i);

                    try
                    {
                        nombreModifs = secteurDAO.creer(secteur);
                        System.out.println("Insertion : " + nombreModifs);
                    }
                    catch (SQLException e)
                    {
                        System.out.println(e.getMessage());
                    }
                }

// --------------------------------------------------------------------------
// 3. Sauvegarde du vecteur des Secteurs modifies dans la base de donnees.
// --------------------------------------------------------------------------
                nombreDeSecteurs = secteursModifies.size();

                for (i = 0; i < nombreDeSecteurs; i++)
                {
                    secteur = secteursModifies.elementAt(i);

                    try
                    {
                        nombreModifs = secteurDAO.modifier(secteur);
                        System.out.println("Modif : " + nombreModifs);
                    }
                    catch (SQLException e)
                    {
                        System.out.println(e.getMessage());
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
            System.out.println(e.getMessage());
        }
    }
}