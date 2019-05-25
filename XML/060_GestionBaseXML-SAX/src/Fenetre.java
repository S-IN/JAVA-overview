// ==========================================================================
// Classe Fenetre                                   Projet GestionBaseMapping
// ==========================================================================

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.beans.*;
import java.util.*;
import utilitairesMG.divers.*;
import utilitairesMG.graphique.fenetreinterne.*;
import utilitairesMG.graphique.*;
import metierMapping.*;


// ==========================================================================
// Classe Fenetre
// --------------------------------------------------------------------------
// Fenetre principale de l'application projet JDBC.
// ==========================================================================

public class Fenetre extends JFrame implements ActionListener
{

// --------------------------------------------------------------------------
// PROPRIETES
// --------------------------------------------------------------------------

// --------------------------------------------------------------------------
// Barre de menu
// --------------------------------------------------------------------------
   private JMenuBar barreMenu;
   private JMenu menuTables;
   private JMenuItem itemContact;
   private JMenuItem itemVersement;
   private JMenuItem itemSecteur;

// --------------------------------------------------------------------------
// Zone de contenu de la fenetre
// --------------------------------------------------------------------------
   private JPanel panneauFond;

// --------------------------------------------------------------------------
// Panneau du centre : affichage des fenetres internes
// --------------------------------------------------------------------------
   private JDesktopPaneMG panneauFenetres;
   private JScrollPaneMG defilePanneauFenetres;

   private FenetreInterneContact fenetreContact;
   private FenetreInterneVersement fenetreVersement;
   private FenetreInterneSecteur fenetreSecteur;

// --------------------------------------------------------------------------
// CONSTRUCTEUR
// --------------------------------------------------------------------------
   public Fenetre(String s)
   {
      super(s);
      addWindowListener(new EcouteFenetre());

// --------------------------------------------------------------------------
// Creation du menu de la fenetre
// --------------------------------------------------------------------------
      itemContact = new JMenuItem("Contact");
      itemContact.setAccelerator(
         KeyStroke.getKeyStroke('C', InputEvent.ALT_MASK));
      itemVersement = new JMenuItem("Versement");
      itemVersement.setAccelerator(
         KeyStroke.getKeyStroke('V', InputEvent.ALT_MASK));
      itemSecteur = new JMenuItem("Secteur");
      itemSecteur.setAccelerator(
         KeyStroke.getKeyStroke('S', InputEvent.ALT_MASK));

      itemContact.addActionListener(this);
      itemVersement.addActionListener(this);
      itemSecteur.addActionListener(this);

      menuTables = new JMenu("Tables");
      menuTables.add(itemContact);
      menuTables.add(itemVersement);
      menuTables.add(itemSecteur);

      barreMenu = new JMenuBar();
      barreMenu.add(menuTables);

      setJMenuBar(barreMenu);

// --------------------------------------------------------------------------
// Zone de contenu de la fenetre
// --------------------------------------------------------------------------
      panneauFond = new JPanel();
      panneauFond.setLayout(new BorderLayout());

// --------------------------------------------------------------------------
// Panneau de type JDesktopPane (qui contient les fenetres internes)
// --------------------------------------------------------------------------
      panneauFenetres = new JDesktopPaneMG();
      panneauFenetres.setBackground(new Color(170, 170, 255));
      panneauFenetres.setPreferredSize(new Dimension(900, 600));

// --------------------------------------------------------------------------
// Cette option provoque l'affichage d'un rectangle et non de toute la
// fenetre lors du draggage de la fenetre. C'est moins beau mais ca va plus
// vite...
// --------------------------------------------------------------------------
      //panneauFenetres.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);

      defilePanneauFenetres = new JScrollPaneMG(panneauFenetres);

      panneauFond.add(defilePanneauFenetres);

      getContentPane().add(panneauFond);

      pack();
      setVisible(true);
   }

// --------------------------------------------------------------------------
// Traitement du menu
// --------------------------------------------------------------------------
   public void actionPerformed(ActionEvent e)
   {
      if(e.getSource()== itemContact)
      {
         Controleur.creeListeContacts();
      }
      else
      {
         if(e.getSource()== itemVersement)
         {
            Controleur.creeListeVersements();
         }
         else
         {
            Controleur.creeListeSecteurs();
         }
      }
   }

// --------------------------------------------------------------------------
// Fermeture de la fenetre interne contact
// --------------------------------------------------------------------------
   public void valideItemContact()
   {
      itemContact.setEnabled(true);
   }

// --------------------------------------------------------------------------
// Fermeture de la fenetre interne versement
// --------------------------------------------------------------------------
   public void valideItemVersement()
   {
      itemVersement.setEnabled(true);
   }

// --------------------------------------------------------------------------
// Fermeture de la fenetre interne secteur
// --------------------------------------------------------------------------
   public void valideItemSecteur()
   {
      itemSecteur.setEnabled(true);
   }

// --------------------------------------------------------------------------
// Affichage d'un message
// --------------------------------------------------------------------------
   public void afficheMessage(String message)
   {
      JOptionPane.showMessageDialog(this,
                                    message,
                                    "Information",
                                    JOptionPane.INFORMATION_MESSAGE);
   }

// --------------------------------------------------------------------------
// Affichage de la table des CONTACTS (dans la fenetre interne contact)
// --------------------------------------------------------------------------
   public void afficheFenetreContact(Vector<Contact> listeContacts,
                                     Vector<Colonne> listeColonnes,
                                     Vector<Secteur> listeSecteurs)
   {
      itemContact.setEnabled(false);

      fenetreContact = new FenetreInterneContact(listeContacts,
                                                 listeColonnes,
                                                 listeSecteurs);
      panneauFenetres.add(fenetreContact);
      panneauFenetres.revalidate();

      try
      {
         fenetreContact.setSelected(true);
      }
      catch(PropertyVetoException ex)
      {
      }
   }

// --------------------------------------------------------------------------
// Affichage de la table des VERSEMENTS (dans la fenetre interne versement)
// --------------------------------------------------------------------------
   public void afficheFenetreVersement(Vector<Versement> listeVersements,
                                       Vector<Colonne> listeColonnes)
   {
      itemVersement.setEnabled(false);

      fenetreVersement = new FenetreInterneVersement(listeVersements,
                                                     listeColonnes);
      panneauFenetres.add(fenetreVersement);
      panneauFenetres.revalidate();

      try
      {
         fenetreVersement.setSelected(true);
      }
      catch(PropertyVetoException ex)
      {
      }
   }

// --------------------------------------------------------------------------
// Affichage de la table des SECTEURS (dans la fenetre interne secteur)
// --------------------------------------------------------------------------
   public void afficheFenetreSecteur(Vector<Secteur> listeSecteurs,
                                     Vector<Colonne> listeColonnes)
   {
      itemSecteur.setEnabled(false);

      fenetreSecteur = new FenetreInterneSecteur(listeSecteurs,
                                                 listeColonnes);
      panneauFenetres.add(fenetreSecteur);
      panneauFenetres.revalidate();

      try
      {
         fenetreSecteur.setSelected(true);
      }
      catch(PropertyVetoException ex)
      {
      }
   }
}