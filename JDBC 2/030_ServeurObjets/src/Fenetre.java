// ==========================================================================
// Classe Fenetre    
// ==========================================================================

import javax.swing.*;       // Pour JFrame, JPanel
import java.awt.*;          // Pour le BorderLayout
import java.awt.event.*;    // Pour les evenements (WindowEvent)

public class Fenetre extends JFrame implements ActionListener
{
    private JPanel panneauFond;

    private JPanel monPanneauHaut;
    private JLabel titre;

    private JPanel monPanneauCentre;
    private JTextArea monTexte;
    private JScrollPane defileur;

    private JPanel monPanneauBas;
    private JButton bouton;
    private ImageIcon allume = new ImageIcon("green-on.gif");
    private ImageIcon eteint = new ImageIcon("green-off.gif");

// ==========================================================================
// Constructeur
// ==========================================================================
    public Fenetre(String s)
    {
        super(s);
        addWindowListener(new EcouteWindowClosing());
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

// --------------------------------------------------------------------------
// panneauFond
// --------------------------------------------------------------------------
        panneauFond = new JPanel();
        panneauFond.setLayout(new BorderLayout());

// --------------------------------------------------------------------------
// monPanneauHaut : panneau du haut de l'ecran qui contiendra le titre
// --------------------------------------------------------------------------
        monPanneauHaut = new JPanel();
        monPanneauHaut.setBackground(Color.blue);

        titre = new JLabel("SERVEUR D'OBJETS");
        Font fontParDefaut = titre.getFont();
        titre.setFont(new Font(fontParDefaut.getName(), Font.PLAIN, 32));
        titre.setForeground(Color.yellow);

        monPanneauHaut.add(titre);

// --------------------------------------------------------------------------
// monPanneauCentre : panneau qui contiendra la zone de texte
// --------------------------------------------------------------------------
        monPanneauCentre = new JPanel();
        monPanneauCentre.setLayout(new BorderLayout());

        monTexte = new JTextArea();
        monTexte.setEditable(false);
        monTexte.setHighlighter(null);
        monTexte.setFont(new Font("Courier new", Font.PLAIN, 12));

        defileur = new JScrollPane(monTexte);
        defileur.setPreferredSize(new Dimension(450, 150));

        monPanneauCentre.add(defileur);

// --------------------------------------------------------------------------
// monPanneauBas : panneau qui contiendra le bouton marche/arret
// --------------------------------------------------------------------------
        monPanneauBas = new JPanel();
        monPanneauBas.setBackground(Color.blue);
        monPanneauBas.setLayout(new FlowLayout(FlowLayout.RIGHT));

        bouton = new JButton("ON / OFF", eteint);
        bouton.addActionListener(this);

        monPanneauBas.add(bouton);

// --------------------------------------------------------------------------
// Ajout des 3 panneaux au panneau de fond
// --------------------------------------------------------------------------
        panneauFond.add(monPanneauHaut, BorderLayout.NORTH);
        panneauFond.add(monPanneauCentre);
        panneauFond.add(monPanneauBas, BorderLayout.SOUTH);

        getContentPane().add(panneauFond);

        pack();
        setVisible(true);
    }

// ==========================================================================
// Affichage dans la JTextArea
// ==========================================================================
    public void afficheTexte(String s)
    {
        monTexte.append(s + "\n");
        monTexte.setCaretPosition(monTexte.getText().length());
    }

    public void allume()
    {
        bouton.setIcon(allume);
        afficheTexte("Le serveur a démarré.");
    }

    public void eteint()
    {
        bouton.setIcon(eteint);
        afficheTexte("Le serveur est arrêté.");
    }
    
// ==========================================================================
// Methode d'ecoute du JButton
// ==========================================================================
    public void actionPerformed(ActionEvent e)
    {
        if (bouton.getIcon() == allume)
        {
            Controleur.arreterServeur();
        }
        else
        {
            Controleur.demarrerServeur();
        }
    }

// --------------------------------------------------------------------------
// Ecouteur de l'evenement fermeture de la fenetre
// --------------------------------------------------------------------------
    private class EcouteWindowClosing extends WindowAdapter
    {
        public void windowClosing(WindowEvent e)
        {
            if (bouton.getIcon() == allume)
            {
                afficheTexte(
                    "Veuillez arrêter le serveur avant de quitter...");
            }
            else
            {
                Controleur.arreter();
            }
        }
    }
}
