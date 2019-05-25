// ==========================================================================
// PROJET MAPPING : Controleur
// --------------------------------------------------------------------------
// Liste des contacts, avec leurs secteurs et leurs versements.
// ==========================================================================

import metierMapping.*;
import daoJdbcMapping.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Vector;
import utilitairesMG.graphique.LF;
import utilitairesMG.jdbc.AccesBase;
import utilitairesMG.jdbc.BaseDeDonnees;

public class Controleur
{
    private static Fenetre fenetre;
    private static BaseDeDonnees base;
    private static AccesBase accesBase;

    private static ContactDAO contactDAO;
    private static VersementDAO versementDAO;
    private static SecteurDAO secteurDAO;

// --------------------------------------------------------------------------
// Programme principal.
// --------------------------------------------------------------------------
// Apres chargement du driver et indication de la base de donnees, ce
// programme lit la liste des contacts et "mappe" chacun d'eux. S'il n'y a
// pas d'exception il affiche la fenetre.
// --------------------------------------------------------------------------
    public static void main(String argv[]) throws ParseException
    {
        Vector<Contact> listeContacts;  
        Connection connexion;

// --------------------------------------------------------------------------
// Chargement du (des) driver(s) jdbc
// --------------------------------------------------------------------------
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
// Lecture de la liste des contacts.
// --------------------------------------------------------------------------
        try
        {
            connexion = accesBase.getConnection();

            try
            {
                listeContacts = contactDAO.lireListe();

                for(int i = 0; i < listeContacts.size(); i++)
                {
                    mapper(listeContacts.elementAt(i));
                }

// --------------------------------------------------------------------------
// Affichage initial de la fenetre.
// --------------------------------------------------------------------------
                LF.setLF();

                javax.swing.SwingUtilities.invokeLater
                (
                    new Runnable()
                    {
                        public void run()
                        {
                            fenetre = new Fenetre("ListeContactMapping",
                                                  listeContacts);
                        }
                    }
                );
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

// --------------------------------------------------------------------------
// Mapper un contact :
// --------------------------------------------------------------------------
// Recherche et stockage de ses versements et de son secteur. Si le contact
// passe en parametre n'existe pas, la methode emet une SQLException.
// --------------------------------------------------------------------------
    public static void mapper(Contact contact) throws SQLException
    {

// --------------------------------------------------------------------------
// Lecture du contact, qui peut avoir ete modifie ou detruit. Dans ce dernier
// cas, la methode lire emet une SQLException.
// --------------------------------------------------------------------------
        contactDAO.lire(contact);

// --------------------------------------------------------------------------
// Lecture et affectation du secteur du contact. La SQLException correspond
// au cas ou le secteur aurait ete detruit. Le secteur du contact est alors
// remis a null.
// --------------------------------------------------------------------------
        try
        {
            contact.setSecteur(secteurDAO.lireSecteur(contact));
        }
        catch(SQLException e)
        {
            contact.setSecteur(null);
        }

// --------------------------------------------------------------------------
// Lecture et affectation de la liste des versements du contact.
// La SQLException correspond au cas ou la table des versements aurait ete
// detruite. La liste des versements est alors remise a null.
// --------------------------------------------------------------------------
        try
        {
            contact.setListeVersements(versementDAO.lireListe(contact));
        }
        catch(SQLException e)
        {
            contact.setListeVersements(null);
        }
    }

// --------------------------------------------------------------------------
// Rafraichir un contact.
// --------------------------------------------------------------------------
// Si le contact passe en parametre n'existe plus, il se produit une
// SQLException, et le texte de l'exception (contact inconnu) est envoye a
// la vue pour y etre affiche.
// --------------------------------------------------------------------------
    public static void rafraichir(Contact contact)
    {
        try
        {
            accesBase.getConnection();
            try
            {
                mapper(contact);
                fenetre.afficheContact(contact);
                fenetre.afficheMessage("");
            }
            catch(SQLException e)
            {
                fenetre.afficheMessage(e.getMessage());
            }
            finally
            {
                accesBase.closeConnection();
            }
        }
        catch(SQLException e)
        {
            fenetre.afficheMessage("Echec de la connexion : " + e.getMessage());
        }
    }

// --------------------------------------------------------------------------
// Arret de l'application
// --------------------------------------------------------------------------
    public static void arreter()
    {
        System.exit(0);
    }
}