// ==========================================================================
// PROJET GRAPHIQUE JDBC : Classe PanneauTable
// ==========================================================================

import javax.swing.*;          // Pour JFrame, JPanel
import java.awt.*;             // Pour le FlowLayout, Graphics

// ==========================================================================
// Classe PanneauTable
// --------------------------------------------------------------------------
// Une instance de PanneauTable est un panneau organise en BorderLayout :
//    au nord, au sud, a l'est et a l'ouest des panneaux vides pour le decor
//    au centre un JScrollPane avec pour client une JTable
// ==========================================================================
public class PanneauTable extends JPanel
{

// --------------------------------------------------------------------------
// Panneaux de presentation (pour encadrer le panneau central.
// --------------------------------------------------------------------------
    private JPanel bordHaut;
    private JPanel bordBas;
    private JPanel bordGauche;
    private JPanel bordDroite;

// --------------------------------------------------------------------------
// Panneau contenant la table a visualiser, et son defileur.
// --------------------------------------------------------------------------
    private JScrollPane defileTable;
    private JPanel panneauDefileTable;

// --------------------------------------------------------------------------
// Constructeur
// --------------------------------------------------------------------------
    PanneauTable(JTable table)
    {
        setLayout(new BorderLayout());

// --------------------------------------------------------------------------
// Panneaux de presentation
// --------------------------------------------------------------------------
        bordHaut = new JPanel();
        add(bordHaut, BorderLayout.NORTH);

        bordBas = new JPanel();
        add(bordBas, BorderLayout.SOUTH);

        bordGauche = new JPanel();
        add(bordGauche, BorderLayout.WEST);

        bordDroite = new JPanel();
        add(bordDroite, BorderLayout.EAST);

// --------------------------------------------------------------------------
// Creation du panneau contenant la table
// --------------------------------------------------------------------------
        panneauDefileTable = new JPanel();
        panneauDefileTable.setLayout(new BorderLayout());

        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        defileTable = new JScrollPane(table);

        panneauDefileTable.add(defileTable);
        add(panneauDefileTable, BorderLayout.CENTER);
    }
}
