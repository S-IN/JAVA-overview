// ==========================================================================
// Classe Controleur                              Projet GestionContactReseau
// ==========================================================================

import daoServeurObjets.*;
import java.util.*;
import utilitairesMG.divers.*;
import utilitairesMG.graphique.*;
import metierMapping.*;
import java.io.*;

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

    public static void main(String args[])
    {

// --------------------------------------------------------------------------
// PriseServeur utilisee pour l'acces aux donnees
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
                    maFenetre = new Fenetre("GestionContactReseau");
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
// Arret de l'application.
// --------------------------------------------------------------------------
    public static void arreter()
    {
        System.exit(0);
    }
}