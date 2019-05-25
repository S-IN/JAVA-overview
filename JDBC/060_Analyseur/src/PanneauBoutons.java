// ==========================================================================
// PROJET GRAPHIQUE JDBC : Classe PanneauBoutons
// ==========================================================================

import javax.swing.*;
import java.awt.*;
import java.util.*;
import utilitairesMG.graphique.*;

// ==========================================================================
// Classe PanneauBoutons
// --------------------------------------------------------------------------
// Une instance de PanneauBoutons a 2 boutons
// --------------------------------------------------------------------------
// Ce panneau est organise en BorderLayout avec :
//    au nord, au sud, a l'est et a l'ouest des panneaux vides pour le decor
//    au centre un JScrollPane avec pour client un JPanelJComponent 
//    contenant les deux boutons
// ==========================================================================
public class PanneauBoutons extends JPanel
{

// --------------------------------------------------------------------------
// Panneaux de presentation (pour encadrer le panneau central.
// Il sera possible d'y mettre un JLabel, ou autre chose...
// --------------------------------------------------------------------------
    private JPanel bordHaut;
    private JPanel bordBas;
    private JPanel bordGauche;
    private JPanel bordDroite;

    private JPanel panneauBoutons;
    private JScrollPane defileBoutons;

// --------------------------------------------------------------------------
// Constructeur
// --------------------------------------------------------------------------
// Pour eviter l'encadrement de la table, on peut enlever les 4 panneaux
// de presentation.
// --------------------------------------------------------------------------
    PanneauBoutons(Vector<JButton> vBoutons)
    {
        setLayout(new BorderLayout());

// --------------------------------------------------------------------------
// Panneaux de presentation
// --------------------------------------------------------------------------
        bordHaut = new JPanel();
        bordHaut.setBackground(Color.white);
        add(bordHaut, BorderLayout.NORTH);

        bordBas = new JPanel();
        bordBas.setBackground(Color.white);
        add(bordBas, BorderLayout.SOUTH);

        bordGauche = new JPanel();
        bordGauche.setBackground(Color.white);
        add(bordGauche, BorderLayout.WEST);

        bordDroite = new JPanel();
        bordDroite.setBackground(Color.white);
        add(bordDroite, BorderLayout.EAST);

        panneauBoutons = new JPanel();
        panneauBoutons.setBackground(Color.white);
        panneauBoutons.setLayout(new FlowLayoutMG());

        for (int i = 0; i < vBoutons.size(); i++)
        {
            panneauBoutons.add(vBoutons.elementAt(i));
        }

        FlowLayoutMG.unifieTailleComposants(panneauBoutons);

        defileBoutons = new JScrollPane(panneauBoutons);
        add(defileBoutons);
    }
}
