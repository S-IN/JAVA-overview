// ==========================================================================
// Fenetre avec 2 CASES A COCHER et deux Labels pour avoir l'etat des cases
// Utilisation de GridLayout et FlowLayout...
// ==========================================================================

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import utilitairesMG.graphique.*;

public class Fenetre extends JFrame implements ActionListener
{
    private JPanel panneauFond;

    private JPanel panneauHaut;
    private JCheckBox case1;
    private JLabel texte1;

    private JPanel panneauBas;
    private JCheckBox case2;
    private JLabel texte2;

    public Fenetre(String s)
    {
        super(s);
        addWindowListener(new EcouteFenetre());

// --------------------------------------------------------------------------
// Creation du panneau de fond : on l'organise en GridLayout de deux lignes 
// et une colonne. Chaque case va contenir un panneau contenant deux 
// composants (JCheckBox, JLabel). 
// --------------------------------------------------------------------------
        panneauFond = new JPanel();
        panneauFond.setLayout(new GridLayout(2, 1));

// --------------------------------------------------------------------------
// Creation du panneau a mettre en haut de panneauFond :
// --------------------------------------------------------------------------
        panneauHaut = new JPanel();
        panneauHaut.setLayout(new FlowLayout(FlowLayout.LEFT));
        panneauHaut.setBackground(Color.white);

        case1 = new JCheckBox("Case 1");
        case1.setBackground(Color.white);
        case1.addActionListener(this);

        texte1 = new JLabel("Case 1 non sélectionnée");
        texte1.setForeground(Color.red);
        texte1.setToolTipText("Etat de la première case");

        panneauHaut.add(case1);
        panneauHaut.add(texte1);

// --------------------------------------------------------------------------
// Creation du panneau a mettre en bas de panneauFond :
// --------------------------------------------------------------------------
        panneauBas = new JPanel();
        panneauBas.setLayout(new FlowLayout(FlowLayout.LEFT));
        panneauBas.setBackground(Color.white);

        case2 = new JCheckBox("Case 2");
        case2.setBackground(Color.white);
        case2.addActionListener(this);

        texte2 = new JLabel("Case 2 non sélectionnée");
        texte2.setForeground(Color.red);
        texte2.setToolTipText("Etat de la deuxième case");

        panneauBas.add(case2);
        panneauBas.add(texte2);

// --------------------------------------------------------------------------
// Ajout de panneauHaut et panneauBas a panneauFond :
// --------------------------------------------------------------------------
        panneauFond.add(panneauHaut);
        panneauFond.add(panneauBas);

// --------------------------------------------------------------------------
// Placement du panneauFond dans la zone de contenu de la fenetre :
// --------------------------------------------------------------------------
        add(panneauFond);

        pack();
        setVisible(true);
    }

// --------------------------------------------------------------------------
// Methode de l'interface ActionListener :
// --------------------------------------------------------------------------
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == case1)
        {
            if (case1.isSelected())
            {
                texte1.setText("Case 1 sélectionnée");
            }
            else
            {
                texte1.setText("Case 1 non sélectionnée");
            }
        }
        else 
        {
            if (case2.isSelected())
            {
                texte2.setText("Case 2 sélectionnée");
            }
            else
            {
                texte2.setText("Case 2 non sélectionnée");
            }
        }
    }
}
