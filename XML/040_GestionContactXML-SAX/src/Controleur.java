// ==========================================================================
// Classe Controleur                              Projet GestionContactReseau
// ==========================================================================

import daoServeurXMLSax.*;
import java.util.*;
import utilitairesMG.divers.*;
import utilitairesMG.graphique.*;
import metierMapping.*;
import java.io.*;
import javax.xml.parsers.*;
import org.xml.sax.*;

public class Controleur
{

// --------------------------------------------------------------------------
// PROPRIETES
// --------------------------------------------------------------------------
    private static Fenetre maFenetre;
    private static ContactDAO contactDAO;
    private static SecteurDAO secteurDAO;
    private static PriseServeur priseServeur;
    private static AccesServeur accesServeur;

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
        contactDAO = new ContactDAO(accesServeur);
        secteurDAO = new SecteurDAO(accesServeur);

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
                    maFenetre = new Fenetre("GestionContactXml-SAX");
                }
            }
        );
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
        maFenetre.afficheMessage("Gestion des versements non réalisée");
    }

// --------------------------------------------------------------------------
// Creation du vecteur des secteurs et du vecteur des colonnes a afficher.
// --------------------------------------------------------------------------
    public static void creeListeSecteurs()
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
            catch (ParserConfigurationException | IOException | SAXException e)
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
// Arret de l'application.
// --------------------------------------------------------------------------
    public static void arreter()
    {
        System.exit(0);
    }
}