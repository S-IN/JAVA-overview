// ==========================================================================
// Classe FenetreInterneContact                     Projet GestionBaseMapping
// ==========================================================================

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import utilitairesMG.divers.*;
import utilitairesMG.graphique.table.*;
import metierMapping.*;

public class FenetreInterneContact extends JInternalFrame
                                   implements ActionListener
{

// --------------------------------------------------------------------------
// Barre de menu de la fenetre interne
// --------------------------------------------------------------------------
    private JMenuBar barreMenu;
    private JMenu menuEdition;
    private JMenuItem supprimerLignesItem;
    private JMenuItem restaurerLignesItem;

    private Vector<Contact> contactsSupprimes;

// --------------------------------------------------------------------------
// Zone de contenu de la fenetre
// --------------------------------------------------------------------------
    private JPanel panneauFond;
    private PanneauTable panneauTable;
    private ModeleTableContact modeleTable;

// ==========================================================================
// Constructeur
// ==========================================================================
    public FenetreInterneContact(Vector<Contact> listeContacts,
                                 Vector<Colonne> listeColonnes,
                                 Vector<Secteur> listeSecteurs)
    {
        super("CONTACTS", true, true, true, true);
        prepareFenetre(listeContacts, listeColonnes, listeSecteurs);
    }
    
    private void prepareFenetre(Vector<Contact> listeContacts,
                                Vector<Colonne> listeColonnes,
                                Vector<Secteur> listeSecteurs)
    {
        addInternalFrameListener(new EcouteInternalFrameClosing());

// --------------------------------------------------------------------------
// Creation de la barre de menus
// --------------------------------------------------------------------------
        barreMenu = new JMenuBar();

// --------------------------------------------------------------------------
// Menu Edition
// --------------------------------------------------------------------------
        menuEdition = new JMenu("Edition");

        supprimerLignesItem = new JMenuItem("Supprimer des lignes");
        restaurerLignesItem = new JMenuItem("Restaurer les lignes supprimées");

// --------------------------------------------------------------------------
// Par defaut, les Item du menu sont "Enabled".
// En les initialisant a false, ils seront inutilisables (grises) jusqu'a
// modification dynamique ulterieure...
// --------------------------------------------------------------------------
        restaurerLignesItem.setEnabled(false);

        menuEdition.add(supprimerLignesItem);
        menuEdition.add(restaurerLignesItem);

        supprimerLignesItem.addActionListener(this);
        restaurerLignesItem.addActionListener(this);

        barreMenu.add(menuEdition);

        setJMenuBar(barreMenu);

// --------------------------------------------------------------------------
// Initialisation du Vecteur des lignes supprimees :
// --------------------------------------------------------------------------
        contactsSupprimes = new Vector<Contact>();
// --------------------------------------------------------------------------
// Zone de contenu de la fenetre
// --------------------------------------------------------------------------
        panneauFond = new JPanel();
        panneauFond.setPreferredSize(new Dimension(800, 400));
        panneauFond.setLayout(new BorderLayout());

// --------------------------------------------------------------------------
// Creation du panneau contenant la JTable
// --------------------------------------------------------------------------
        panneauTable = new PanneauTable();
        panneauTable.getPanneauJTable().setBackground(new Color(255, 220, 200));

// --------------------------------------------------------------------------
// Création du modele de table, et ajout a la table
// --------------------------------------------------------------------------
        modeleTable = new ModeleTableContact(listeContacts, listeColonnes);

        panneauTable.getTable().setModel(modeleTable);

// --------------------------------------------------------------------------
// Création du modele de colonne, et ajout a la table
// --------------------------------------------------------------------------
        ModeleColonneTable modeleColonne
            = new ModeleColonneTable(listeColonnes, panneauTable.getTailleM());

// --------------------------------------------------------------------------
// On souhaite changer l'editeur de cellules de la colonne 5
// --------------------------------------------------------------------------
        JComboBox combo = new JComboBox();

        combo.addItem(null);

        for (int i = 0; i < listeSecteurs.size(); i++)
        {
            combo.addItem(listeSecteurs.elementAt(i).getCode());
        }

        DefaultCellEditor editeur = new DefaultCellEditor(combo);
        editeur.setClickCountToStart(2);

        modeleColonne.setEditeurColonne(5, editeur);

        panneauTable.getTable().setColumnModel(modeleColonne);

        panneauFond.add(panneauTable);

        getContentPane().add(panneauFond);

        pack();
        setVisible(true);
    }

// ==========================================================================
// Ecoute des ActionEvent
// ==========================================================================
    public void actionPerformed(ActionEvent e)
    {

// --------------------------------------------------------------------------
// Suppression de lignes
// --------------------------------------------------------------------------
        if (e.getSource() == supprimerLignesItem)
        {
            int nombreLignesSupprimees = panneauTable.supprimerLignes();

            if (nombreLignesSupprimees > 0)
            {
                restaurerLignesItem.setEnabled(true);
            }
        }
        else

// --------------------------------------------------------------------------
// Restauration de lignes
// --------------------------------------------------------------------------
        {
            panneauTable.restaurerLignes();
            restaurerLignesItem.setEnabled(false);
        }
    }

// ==========================================================================
// Affichage d'une erreur de requete
// ==========================================================================
    public void afficheMessage(String message)
    {
        JOptionPane.showMessageDialog(this,
            message,
            "Information",
            JOptionPane.INFORMATION_MESSAGE);
    }

// --------------------------------------------------------------------------
// Ecouteur de l'evenement fermeture de la fenetre interne
// --------------------------------------------------------------------------
    private class EcouteInternalFrameClosing extends InternalFrameAdapter
    {
        public void internalFrameClosing(InternalFrameEvent e)
        {
            Controleur.majContacts(modeleTable.getListeContacts('I'),
                                   modeleTable.getListeContacts('M'),
                                   modeleTable.getListeContactsSupprimes());
        }
    }
}
