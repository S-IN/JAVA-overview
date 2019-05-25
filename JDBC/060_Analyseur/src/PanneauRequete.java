// ==========================================================================
// PROJET GRAPHIQUE JDBC : Classe PanneauRequete
// ==========================================================================

import javax.swing.*;       // Pour JFrame, JPanel
import java.awt.*;          // Pour le FlowLayout, Graphics

// ==========================================================================
// Classe PanneauRequete
// --------------------------------------------------------------------------
// Une instance de PanneauRequete cree un panneau tout equipe.
// --------------------------------------------------------------------------
// Ce panneau est organise en BorderLayout avec :
//    au nord le JLabel enteteRequete
//    au sud  le JLabel erreurRequete
//    a l'est et a l'ouest deux panneaux bords pour le decor
//    au centre un JScrollPane contenant la JTextArea zoneRequete
// ==========================================================================
public class PanneauRequete extends JPanel
{

// --------------------------------------------------------------------------
// Panneaux de presentation (pour encadrer le panneau central.
// Il sera possible d'y mettre un JLabel, ou autre chose...
// --------------------------------------------------------------------------
    private JPanel bordHaut;
    private JPanel bordBas;
    private JPanel bordGauche;
    private JPanel bordDroite;

    private JScrollPane defileRequete;

    PanneauRequete(JLabel enteteRequete,
        JTextArea zoneRequete,
        JLabel erreurRequete)
    {
        setLayout(new BorderLayout());

// --------------------------------------------------------------------------
// Panneaux de presentation
// --------------------------------------------------------------------------
        bordHaut = new JPanel();
        bordHaut.setBackground(Color.white);
        bordHaut.add(enteteRequete, BorderLayout.NORTH);
        add(bordHaut, BorderLayout.NORTH);

        bordBas = new JPanel();
        bordBas.setBackground(Color.white);
        erreurRequete.setForeground(Color.red);
        bordBas.add(erreurRequete, BorderLayout.SOUTH);
        add(bordBas, BorderLayout.SOUTH);

        bordGauche = new JPanel();
        bordGauche.setBackground(Color.white);
        add(bordGauche, BorderLayout.WEST);

        bordDroite = new JPanel();
        bordDroite.setBackground(Color.white);
        add(bordDroite, BorderLayout.EAST);

// --------------------------------------------------------------------------
// Creation du defileur sur la TextArea :
// La police par defaut du JLabel est : "FontUI resource", Font.BOLD, 12
// --------------------------------------------------------------------------
        zoneRequete.setForeground(Color.blue);
        zoneRequete.setFont(new Font("FontUI resource", Font.BOLD, 12));
        defileRequete = new JScrollPane(zoneRequete);

        add(defileRequete, BorderLayout.CENTER);
    }
}
