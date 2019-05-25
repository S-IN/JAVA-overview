// ==========================================================================
// Classe FenetreInterneContact                    Projet GestionContactLocal
// ==========================================================================

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import utilitairesMG.divers.*;
import utilitairesMG.graphique.table.*;

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

// --------------------------------------------------------------------------
// Zone de contenu de la fenetre
// --------------------------------------------------------------------------
    private JPanel panneauFond;

    private JTable table;
    private ModeleTableContact modeleTable;
    private ModeleColonneTable modeleColonne;

    private JScrollPane defileur;

// ==========================================================================
// Constructeur
// ==========================================================================
    public FenetreInterneContact(Vector<Contact> listeContacts,
                                 Vector<Colonne> listeColonnes)
    {
        super("CONTACTS", true, true, true, true);
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
// Zone de contenu de la fenetre
// --------------------------------------------------------------------------
        panneauFond = new JPanel();
        panneauFond.setLayout(new BorderLayout());
        panneauFond.setPreferredSize(new Dimension(710, 150));

// --------------------------------------------------------------------------
// Creation de l'objet JTable 
// --------------------------------------------------------------------------
        table = new JTable();
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

// --------------------------------------------------------------------------
// Recherche de la largeur en nombre de points de la lettre M de la police
// par defaut de la JTable.
// --------------------------------------------------------------------------
        Font fontParDefaut = table.getFont();
        int tailleM = table.getFontMetrics(fontParDefaut).stringWidth("M");

// --------------------------------------------------------------------------
// Creation des modeles de table et de colonne. 
// --------------------------------------------------------------------------
        modeleTable = new ModeleTableContact(listeContacts, listeColonnes);
        modeleColonne = new ModeleColonneTable(listeColonnes, tailleM);

// --------------------------------------------------------------------------
// Changement de l'editeur de la colonne 5 (CodeSecteur)
// --------------------------------------------------------------------------
        JComboBox combo = new JComboBox();
        combo.addItem(null);
        for (int i = 1; i <= 4; i++)
        {
            combo.addItem(i);
        }

        DefaultCellEditor editeur = new DefaultCellEditor(combo);
        editeur.setClickCountToStart(2);

        modeleColonne.setEditeurColonne(5, editeur);

// --------------------------------------------------------------------------
// Ajout des modeles a la JTable. 
// --------------------------------------------------------------------------
        table.setModel(modeleTable);
        table.setColumnModel(modeleColonne);
        defileur = new JScrollPane(table);
        defileur.getViewport().setBackground(new Color(255, 220, 200));

        panneauFond.add(defileur);

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
            int[] lignesSelectionnees = table.getSelectedRows();

            if (lignesSelectionnees.length > 0)
            {
                int n = JOptionPane.showConfirmDialog(
                        this,
                        "Confirmez-vous la suppression des lignes sélectionnées ?",
                        "Confirmation suppression",
                        JOptionPane.YES_NO_OPTION);

                if (n == 0)
                {
                    modeleTable.supprimer(lignesSelectionnees);
                    table.revalidate();
                    if (modeleTable.getListeContactsSupprimes().size() > 0)
                    {
                        restaurerLignesItem.setEnabled(true);
                    }
                }
            }
        }
        else

// --------------------------------------------------------------------------
// Restauration de lignes
// --------------------------------------------------------------------------
        {
            modeleTable.restaurer();
            table.revalidate();
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
            Controleur.majContacts(modeleTable.getListeContacts('M'),
                                   modeleTable.getListeContacts('I'),
                                   modeleTable.getListeContactsSupprimes());
        }
    }
}