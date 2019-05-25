// ==========================================================================
// Classe Controleur                                Projet GestionBaseReseau
// ==========================================================================

import daoServeurObjets.VersementDAO;
import daoServeurObjets.ContactDAO;
import daoServeurObjets.PriseServeur;
import daoServeurObjets.AccesServeur;
import daoServeurObjets.SecteurDAO;
import java.io.*;
import java.util.*;
import utilitairesMG.divers.*;
import utilitairesMG.graphique.*;
import metierMapping.*;

public class Controleur
{

// --------------------------------------------------------------------------
// PROPRIETES
// --------------------------------------------------------------------------
    private static Fenetre maFenetre;

    private static PriseServeur priseServeur;
    private static AccesServeur accesServeur;

    private static SecteurDAO secteurDAO;
    private static ContactDAO contactDAO;
    private static VersementDAO versementDAO;

// --------------------------------------------------------------------------
// Methode main : lancement de l'application
// --------------------------------------------------------------------------
    public static void main(String args[])
    {

// --------------------------------------------------------------------------
// PriseReseau utilisee pour l'acces aux donnees
// --------------------------------------------------------------------------
        priseServeur = new PriseServeur("localhost", 8189);
        priseServeur.setFormatDate("dd/MM/yyyy");
        accesServeur = new AccesServeur(priseServeur);

// --------------------------------------------------------------------------
// Creation des objets DAO
// --------------------------------------------------------------------------
        secteurDAO = new SecteurDAO(accesServeur);
        contactDAO = new ContactDAO(accesServeur);
        versementDAO = new VersementDAO(accesServeur);

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
                    maFenetre = new Fenetre("GestionBaseReseau");
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
            listeContacts = contactDAO.lireListe();
            listeColonnes = contactDAO.getListeColonnes();
            listeSecteurs = secteurDAO.lireListe();

            maFenetre.afficheContacts(listeContacts,
                                      listeColonnes,
                                      listeSecteurs);
        }
        catch (ClassNotFoundException | IOException e)
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
            listeVersements = versementDAO.lireListe();
            listeColonnes = versementDAO.getListeColonnes();

            maFenetre.afficheVersements(listeVersements,
                                        listeColonnes);
        }
        catch (ClassNotFoundException | IOException e)
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
            listeSecteurs = secteurDAO.lireListe();
            listeColonnes = secteurDAO.getListeColonnes();

            maFenetre.afficheSecteurs(listeSecteurs,
                                      listeColonnes);
        }
        catch (ClassNotFoundException | IOException e)
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
        int i;

// --------------------------------------------------------------------------
// Mise a jour de l'affichage de la fenetre principale. Ici, cela consiste
// a revalider le menu d'affichage de la table CONTACT.
// --------------------------------------------------------------------------
        maFenetre.valideItemContact();

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
            catch (ClassNotFoundException | IOException e)
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
            catch (ClassNotFoundException | IOException e)
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
            catch (ClassNotFoundException | IOException e)
            {
                maFenetre.afficheMessage(e.getMessage());
            }
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
        int i;

// --------------------------------------------------------------------------
// Mise a jour de l'affichage de la fenetre principale. Ici, cela consiste
// a revalider le menu d'affichage de la table VERSEMENT.
// --------------------------------------------------------------------------
        maFenetre.valideItemVersement();

// --------------------------------------------------------------------------
// 1. Destruction des Versements du vecteur versementsSupprimes.
// --------------------------------------------------------------------------
        for (i = 0; i < versementsSupprimes.size(); i++)
        {
            versement = versementsSupprimes.elementAt(i);
            try
            {
                versementDAO.detruire(versement);
            }
            catch (ClassNotFoundException | IOException e)
            {
                maFenetre.afficheMessage(e.getMessage());
            }
        }

// --------------------------------------------------------------------------
// 2. Sauvegarde du vecteur des Versements inseres dans la base de donnees.
// --------------------------------------------------------------------------
        for (i = 0; i < versementsInseres.size(); i++)
        {
            versement = versementsInseres.elementAt(i);

            try
            {
                versementDAO.creer(versement);
            }
            catch (ClassNotFoundException | IOException e)
            {
                maFenetre.afficheMessage(e.getMessage());
            }
        }

// --------------------------------------------------------------------------
// 3. Sauvegarde du vecteur des Versements modifies dans la base de donnees.
// --------------------------------------------------------------------------
        for (i = 0; i < versementsModifies.size(); i++)
        {
            versement = versementsModifies.elementAt(i);

            try
            {
                versementDAO.modifier(versement);
            }
            catch (ClassNotFoundException | IOException e)
            {
                maFenetre.afficheMessage(e.getMessage());
            }
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
        int i;

// --------------------------------------------------------------------------
// Mise a jour de l'affichage de la fenetre principale. Ici, cela consiste
// a revalider le menu d'affichage de la table CONTACT.
// --------------------------------------------------------------------------
        maFenetre.valideItemSecteur();

// --------------------------------------------------------------------------
// 1. Destruction des Secteurs du vecteur secteursSupprimes.
// --------------------------------------------------------------------------
        for (i = 0; i < secteursSupprimes.size(); i++)
        {
            secteur = secteursSupprimes.elementAt(i);
            try
            {
                secteurDAO.detruire(secteur);
            }
            catch (ClassNotFoundException | IOException e)
            {
                maFenetre.afficheMessage(e.getMessage());
            }
        }

// --------------------------------------------------------------------------
// 2. Sauvegarde du vecteur des Secteurs inseres dans la base de donnees.
// --------------------------------------------------------------------------
        for (i = 0; i < secteursInseres.size(); i++)
        {
            secteur = secteursInseres.elementAt(i);

            try
            {
                secteurDAO.creer(secteur);
            }
            catch (ClassNotFoundException | IOException e)
            {
                maFenetre.afficheMessage(e.getMessage());
            }
        }

// --------------------------------------------------------------------------
// 3. Sauvegarde du vecteur des Secteurs modifies dans la base de donnees.
// --------------------------------------------------------------------------
        for (i = 0; i < secteursModifies.size(); i++)
        {
            secteur = secteursModifies.elementAt(i);

            try
            {
                secteurDAO.modifier(secteur);
            }
            catch (ClassNotFoundException | IOException e)
            {
                maFenetre.afficheMessage(e.getMessage());
            }
        }
    }
}