import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import utilitairesMG.graphique.*;

public class Fenetre extends JFrame implements ActionListener
{
    private JPanel panneauFond;

    private JPanel panneauNord;
    private JCheckBox case1;
    private JLabel texte1;

    private JPanel panneauCentre;
    private JCheckBox case2;
    private JLabel texte2;

    public Fenetre(String s)
    {
        super(s);
        addWindowListener(new EcouteFenetre());

// --------------------------------------------------------------------------
// Creation du panneau de fond :
// --------------------------------------------------------------------------
        panneauFond = new JPanel();
        panneauFond.setLayout(new BorderLayout());

// --------------------------------------------------------------------------
// Creation du panneau Nord : ce panneau, organise en FlowLayout cadre a
// gauche, va contenir deux composants (JCheckBox, JLabel). 
// --------------------------------------------------------------------------
        panneauNord = new JPanel();
        panneauNord.setBackground(Color.white);
        panneauNord.setLayout(new FlowLayout(FlowLayout.LEFT));
        panneauNord.setPreferredSize(new Dimension(0, 50));

// --------------------------------------------------------------------------
// Composants de panneauNord :
// --------------------------------------------------------------------------
        case1 = new JCheckBox("Case 1");
        case1.setOpaque(false);
        case1.addActionListener(this);

        texte1 = new JLabel("Case 1 non sélectionnée");
        texte1.setForeground(Color.red);
        texte1.setToolTipText("Etat de la première case");

        panneauNord.add(case1);
        panneauNord.add(texte1);

// --------------------------------------------------------------------------
// Creation du panneau centre :
// --------------------------------------------------------------------------
        panneauCentre = new JPanel();
        panneauCentre.setBackground(Color.white);
        panneauCentre.setLayout(new FlowLayout(FlowLayout.LEFT));

        case2 = new JCheckBox("Case 2");
        case2.setOpaque(false);
        case2.addActionListener(this);

        texte2 = new JLabel("Case 2 non sélectionnée");
        texte2.setForeground(Color.red);
        texte2.setToolTipText("Etat de la deuxième case");

        panneauCentre.add(case2);
        panneauCentre.add(texte2);

// --------------------------------------------------------------------------
// Placement de panneauNord et panneauCentre dans le panneau de fond :
// --------------------------------------------------------------------------
        panneauFond.add(panneauNord, BorderLayout.NORTH);
        panneauFond.add(panneauCentre);

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
