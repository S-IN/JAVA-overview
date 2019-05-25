// ==========================================================================
// Classe Controleur                                Projet GestionBaseReseau
// ==========================================================================

import daoServeurXMLSax.*;
import java.io.*;
import java.util.*;
import utilitairesMG.divers.*;
import utilitairesMG.graphique.*;
import metierMapping.*;
import javax.xml.parsers.*;
import org.xml.sax.*;

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
        LF.setLF();
        maFenetre = new Fenetre("GestionBaseXML-SAX");
    }

// --------------------------------------------------------------------------
// Creation du vecteur des contacts et du vecteur des colonnes a afficher.
// Appel de l'affichage de la fenetre contact...
// --------------------------------------------------------------------------
    public static void creeListeContacts()
    {
        Vector<Contact> listeContacts;
        Vector<Colonne> listeColonnes;

        Vector<Secteur> listeSecteurs;

        try
        {
            listeContacts = contactDAO.lireListe();
            listeColonnes = contactDAO.getListeColonnes();
            listeSecteurs = secteurDAO.lireListe();

            maFenetre.afficheFenetreContact(listeContacts,
                                            listeColonnes,
                                            listeSecteurs);
        }
        catch (ParserConfigurationException | SAXException | IOException e)
        {
            maFenetre.afficheMessage(e.getMessage());
        }
    }

// --------------------------------------------------------------------------
// Creation du vecteur des versements et du vecteur des colonnes a afficher.
// --------------------------------------------------------------------------
    public static void creeListeVersements()
    {
        Vector<Versement> listeVersements;
        Vector<Colonne> listeColonnes;

        try
        {
            listeVersements = versementDAO.lireListe();
            listeColonnes = versementDAO.getListeColonnes();

            maFenetre.afficheFenetreVersement(listeVersements,
                                              listeColonnes);
        }
        catch (ParserConfigurationException | SAXException | IOException e)
        {
            maFenetre.afficheMessage(e.getMessage());
        }
    }

// --------------------------------------------------------------------------
// Creation du vecteur des secteurs et du vecteur des colonnes a afficher.
// --------------------------------------------------------------------------
    public static void creeListeSecteurs()
    {
        Vector<Secteur> listeSecteurs;
        Vector<Colonne> listeColonnes;

        try
        {
            listeSecteurs = secteurDAO.lireListe();
            listeColonnes = secteurDAO.getListeColonnes();

            maFenetre.afficheFenetreSecteur(listeSecteurs,
                                            listeColonnes);
        }
        catch (ParserConfigurationException | SAXException | IOException e)
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
                nombreModifs = contactDAO.detruire(contact);
            }
            catch (ParserConfigurationException | SAXException | IOException e)
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
            catch (ParserConfigurationException | SAXException | IOException e)
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
            catch (ParserConfigurationException | SAXException | IOException e)
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
        int nombreDeVersements;
        int i;
        int nombreModifs;

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
                nombreModifs = versementDAO.detruire(versement);
            }
            catch (ParserConfigurationException | SAXException | IOException e)
            {
                maFenetre.afficheMessage(e.getMessage());
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
            }
            catch (ParserConfigurationException | SAXException | IOException e)
            {
                maFenetre.afficheMessage(e.getMessage());
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
            }
            catch (ParserConfigurationException | SAXException | IOException e)
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
        int nombreDeSecteurs;
        int i;
        int nombreModifs;

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
                nombreModifs = secteurDAO.detruire(secteur);
            }
            catch (ParserConfigurationException | SAXException | IOException e)
            {
                maFenetre.afficheMessage(e.getMessage());
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
            }
            catch (ParserConfigurationException | SAXException | IOException e)
            {
                maFenetre.afficheMessage(e.getMessage());
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
            }
            catch (ParserConfigurationException | SAXException | IOException e)
            {
                maFenetre.afficheMessage(e.getMessage());
            }
        }
    }
}
