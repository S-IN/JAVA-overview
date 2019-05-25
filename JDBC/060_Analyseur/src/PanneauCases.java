// ==========================================================================
// PROJET GRAPHIQUE JDBC : Classe PanneauCases
// ==========================================================================

import javax.swing.*;
import java.awt.*;
import java.util.*;
import utilitairesMG.graphique.*;

// ==========================================================================
// Classe PanneauCases
// --------------------------------------------------------------------------
// Une instance de PanneauCases a des JCheckBox transmises par un Vector
// --------------------------------------------------------------------------
// Ce panneau est organise en BorderLayout avec :
//    au nord, au sud, a l'est et a l'ouest des panneaux vides pour le decor
//    au centre un JScrollPane avec pour client un JPanelJComponent 
//    contenant les JCheckBox
// ==========================================================================
public class PanneauCases extends JPanel
{

// --------------------------------------------------------------------------
// Panneaux de presentation (pour encadrer le panneau central.
// Il sera possible d'y mettre un JLabel, ou autre chose...
// --------------------------------------------------------------------------
    private JPanel bordHaut;
    private JPanel bordBas;
    private JPanel bordGauche;
    private JPanel bordDroite;

    private JPanel panneauCases;
    private JScrollPane defileCases;

// --------------------------------------------------------------------------
// Constructeur
// --------------------------------------------------------------------------
// Pour eviter l'encadrement de la table, on peut enlever les 4 panneaux
// de presentation.
// --------------------------------------------------------------------------
    PanneauCases(JLabel enteteCases, Vector<JCheckBox> vCases)
    {
        setLayout(new BorderLayout());

// --------------------------------------------------------------------------
// Panneaux de presentation
// --------------------------------------------------------------------------
        bordHaut = new JPanel();
        bordHaut.add(enteteCases);
        add(bordHaut, BorderLayout.NORTH);

        bordBas = new JPanel();
        add(bordBas, BorderLayout.SOUTH);

        bordGauche = new JPanel();
        add(bordGauche, BorderLayout.WEST);

        bordDroite = new JPanel();
        add(bordDroite, BorderLayout.EAST);

        panneauCases = new JPanel();
        panneauCases.setLayout(new FlowLayoutMG());

        for (int i = 0; i < vCases.size(); i++)
        {
            panneauCases.add(vCases.elementAt(i));
        }

        FlowLayoutMG.unifieTailleComposants(panneauCases);

        defileCases = new JScrollPane(panneauCases);
        add(defileCases);
    }
}
