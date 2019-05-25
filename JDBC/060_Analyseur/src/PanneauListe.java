// ==========================================================================
// PROJET GRAPHIQUE JDBC : Classe PanneauListe
// ==========================================================================

import javax.swing.*;          // Pour JFrame, JPanel
import java.awt.*;             // Pour le FlowLayout, Graphics

// ==========================================================================
// Classe PanneauListe
// --------------------------------------------------------------------------
// Une instance de PanneauListe est un panneau organise en BorderLayout :
//    au nord, au sud, a l'est et a l'ouest des panneaux vides pour le decor
//    au centre un JScrollPane avec pour client une JList
// ==========================================================================
public class PanneauListe extends JPanel
{

// --------------------------------------------------------------------------
// Panneaux de presentation (pour encadrer le panneau central.
// --------------------------------------------------------------------------
    private JPanel bordHaut;
    private JPanel bordBas;
    private JPanel bordGauche;
    private JPanel bordDroite;

// --------------------------------------------------------------------------
// Panneau contenant la liste a visualiser, et son defileur.
// --------------------------------------------------------------------------
    private JScrollPane defileListe;
    private JPanel panneauDefileListe;

// --------------------------------------------------------------------------
// Constructeur
// --------------------------------------------------------------------------
    PanneauListe(JLabel enteteListe, JList zoneListe)
    {
        setLayout(new BorderLayout());

// --------------------------------------------------------------------------
// Panneaux de presentation
// --------------------------------------------------------------------------
        bordHaut = new JPanel();
        bordHaut.add(enteteListe);
        add(bordHaut, BorderLayout.NORTH);

        bordBas = new JPanel();
        add(bordBas, BorderLayout.SOUTH);

        bordGauche = new JPanel();
        add(bordGauche, BorderLayout.WEST);

        bordDroite = new JPanel();
        add(bordDroite, BorderLayout.EAST);

// --------------------------------------------------------------------------
// Creation du panneau contenant la liste
// --------------------------------------------------------------------------
        panneauDefileListe = new JPanel();
        panneauDefileListe.setLayout(new BorderLayout());

        zoneListe.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        defileListe = new JScrollPane(zoneListe);

        panneauDefileListe.add(defileListe);
        add(panneauDefileListe);
    }
}
