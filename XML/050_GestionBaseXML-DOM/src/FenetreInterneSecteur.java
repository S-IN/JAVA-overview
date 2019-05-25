// ==========================================================================
// Classe FenetreInterneSecteur                     Projet GestionBaseMapping
// ==========================================================================

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import utilitairesMG.divers.*;
import utilitairesMG.graphique.table.*;
import metierMapping.*;

public class FenetreInterneSecteur extends JInternalFrame
                                   implements ActionListener
{

// --------------------------------------------------------------------------
// Barre de menu de la fenetre interne
// --------------------------------------------------------------------------
    private JMenuBar barreMenu;
    private JMenu menuEdition;
    private JMenuItem supprimerLignesItem;
    private JMenuItem restaurerLignesItem;

    private Vector<Secteur> secteursSupprimes;

// --------------------------------------------------------------------------
// Zone de contenu de la fenetre
// --------------------------------------------------------------------------
    private JPanel panneauFond;
    private PanneauTable panneauTable;
    private ModeleTableSecteur modeleTable;

// ==========================================================================
// Constructeur
// ==========================================================================
    public FenetreInterneSecteur(Vector<Secteur> listeSecteurs,
                                 Vector<Colonne> listeColonnes)
    {
        super("SECTEURS", true, true, true, true);

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
        secteursSupprimes = new Vector<Secteur>();
// --------------------------------------------------------------------------
// Zone de contenu de la fenetre
// --------------------------------------------------------------------------
        panneauFond = new JPanel();
        panneauFond.setPreferredSize(new Dimension(400, 250));
        panneauFond.setLayout(new BorderLayout());

// --------------------------------------------------------------------------
// Creation du panneau contenant la JTable
// --------------------------------------------------------------------------
        panneauTable = new PanneauTable();
        panneauTable.getPanneauJTable().setBackground(new Color(220, 255, 200));

// --------------------------------------------------------------------------
// Création du modele de table, et ajout a la table
// --------------------------------------------------------------------------
        modeleTable = new ModeleTableSecteur(listeSecteurs, listeColonnes);

        panneauTable.getTable().setModel(modeleTable);

// --------------------------------------------------------------------------
// Création du modele de colonne, et ajout a la table
// --------------------------------------------------------------------------
        ModeleColonneTable modeleColonne
            = new ModeleColonneTable(listeColonnes, panneauTable.getTailleM());

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
            Controleur.majSecteurs(modeleTable.getListeSecteurs('I'),
                                   modeleTable.getListeSecteurs('M'),
                                   modeleTable.getListeSecteursSupprimes());
        }
    }
}
