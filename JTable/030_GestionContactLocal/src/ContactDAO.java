import java.util.*;
import utilitairesMG.divers.*;

public class ContactDAO
{

// -------------------------------------------------------------------------- 
// Creation de la liste des Contacts a afficher dans la JTable
// -------------------------------------------------------------------------- 
    public Vector<Contact> creeListeContacts()
    {
        Vector<Contact> listeContacts = new Vector<Contact>();
        Contact contact;

        contact = new Contact();
        contact.setNumero(100);
        contact.setNom("AIGLE");
        contact.setAdresse("Rue des coucous");
        contact.setCodePostal("94000");
        contact.setVille("CRETEIL");
        contact.setCodeSecteur(1);

        listeContacts.addElement(contact);

        contact = new Contact();
        contact.setNumero(101);
        contact.setNom("VAUTOUR");
        contact.setAdresse("Avenue du Verdier");
        contact.setCodePostal("94100");
        contact.setVille("SAINT MAUR");
        contact.setCodeSecteur(3);

        listeContacts.addElement(contact);

        contact = new Contact();
        contact.setNumero(112);
        contact.setNom("MERLE");
        contact.setAdresse("Rue Aristide Bruant");
        contact.setCodePostal("92120");
        contact.setVille("VANVES");
        contact.setCodeSecteur(3);

        listeContacts.addElement(contact);

        return listeContacts;
    }

// -------------------------------------------------------------------------- 
// Creation de la liste des colonnes a afficher dans la JTable
// -------------------------------------------------------------------------- 
    public Vector<Colonne> creeListeColonnes()
    {
        Vector<Colonne> listeColonnes = new Vector<Colonne>();

// --------------------------------------------------------------------------
// Creation du vecteur des colonnes
// --------------------------------------------------------------------------
        listeColonnes.addElement(
            new Colonne("NUMERO", new Integer(5), "java.lang.Integer"));

        listeColonnes.addElement(
            new Colonne("NOM", new Integer(20), "java.lang.String"));

        listeColonnes.addElement(
            new Colonne("ADRESSE", new Integer(50), "java.lang.String"));

        listeColonnes.addElement(
            new Colonne("CODE_POSTAL", new Integer(12), "java.lang.String"));

        listeColonnes.addElement(
            new Colonne("VILLE", new Integer(20), "java.lang.String"));

        listeColonnes.addElement(
            new Colonne("CODE_SECTEUR", new Integer(1), "java.lang.Integer"));

        return listeColonnes;
    }
}
