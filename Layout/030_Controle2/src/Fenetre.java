import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import utilitairesMG.graphique.*;

public class Fenetre extends JFrame implements ActionListener
{
    private JPanel panneauFond;

    private JPanel panneauCommande;

    private JPanel panneauCommandeHaut;
    private JScrollPane defileurCommande;
    private JLabel texte;
    private JSpinner spinner;

    private JPanel panneauCommandeBas;
    private JButton boutonExecuter;

    private JPanel panneauCases;
    private JScrollPane defileur;

    private JPanel panneauEtat;
    private JButton boutonEtat;

// --------------------------------------------------------------------------
// CONSTRUCTEUR
// --------------------------------------------------------------------------
    public Fenetre(String titre)
    {
        super(titre);
        addWindowListener(new EcouteFenetre());

// --------------------------------------------------------------------------
// Creation du panneau de fond et ajout des composants
// --------------------------------------------------------------------------
        panneauFond = new JPanel();
        panneauFond.setPreferredSize(new Dimension(400, 400));
        panneauFond.setLayout(new BorderLayout());

// --------------------------------------------------------------------------
// Creation du panneau de commande
// --------------------------------------------------------------------------
// Ce panneau est divise en 2 : 
// - un panneau du haut avec le JLabel et le JSpinner
// - un panneau du bas avec le JButton
// --------------------------------------------------------------------------
        panneauCommande = new JPanel();
        panneauCommande.setLayout(new BorderLayout());

        panneauCommandeHaut = new JPanel();
        texte = new JLabel("Nombre de JCheckBox a creer : ");

// --------------------------------------------------------------------------
// Le modele de Spinner permet de limiter la plage numerique
// --------------------------------------------------------------------------
        SpinnerModel model = new SpinnerNumberModel(0, 0, 100, 1);
        spinner = new JSpinner(model);

        panneauCommandeHaut.add(texte);
        panneauCommandeHaut.add(spinner);
        defileurCommande = new JScrollPane(panneauCommandeHaut);
        defileurCommande.setPreferredSize(new Dimension(0, 60));

// --------------------------------------------------------------------------
// Explication pour les tailles preferees :
// --------------------------------------------------------------------------
// La taille preferee du panneauCommandeHaut est calculee automatiquement
// pour contenir le texte et le spinner. C'est l'effet visuel voulu. Des que
// la largeur est insuffisante pour afficher le texte et le spinner, le
// scrolling apparait. C'est aussi pour cela qu'on laisse le gestionnaire de 
// par defaut :  FlowLayout. 
// --------------------------------------------------------------------------
// La taille preferee du defileur sert uniquement a en augmenter un peu la 
// hauteur. 
// --------------------------------------------------------------------------
        panneauCommandeBas = new JPanel();
        boutonExecuter = new JButton("EXECUTER");
        boutonExecuter.addActionListener(this);

        panneauCommandeBas.add(boutonExecuter);

        panneauCommande.add(defileurCommande, BorderLayout.NORTH);
        panneauCommande.add(panneauCommandeBas);

// --------------------------------------------------------------------------
// Creation du panneau des cases (au depart vide) et de son defileur
// --------------------------------------------------------------------------
        panneauCases = new JPanel();
        panneauCases.setLayout(new FlowLayoutMG());
        defileur = new JScrollPane(panneauCases);

        panneauEtat = new JPanel();
        boutonEtat = new JButton("ETAT");
        boutonEtat.addActionListener(this);

        panneauEtat.add(boutonEtat);

        panneauFond.add(panneauCommande, BorderLayout.NORTH);
        panneauFond.add(defileur);
        panneauFond.add(panneauEtat, BorderLayout.SOUTH);

        add(panneauFond);

        pack();
        setVisible(true);
    }

// --------------------------------------------------------------------------
// METHODE D'ECOUTE DES EVENEMENTS (CLICS SUR LES BOUTONS)
// --------------------------------------------------------------------------
    public void actionPerformed(ActionEvent e)
    {
        Integer nombreCases;
        JCheckBox caseACocher;

        Component[] composantsPanneauCases;

        if (e.getSource() == boutonExecuter)
        {
            panneauCases.removeAll();

// --------------------------------------------------------------------------
// Création des cases a cocher et ajout au panneauCases
// --------------------------------------------------------------------------
            nombreCases = (Integer)spinner.getValue();

            for (int i = 1; i <= nombreCases; i++)
            {
                caseACocher = new JCheckBox("Case " + i);
                caseACocher.setOpaque(false);
                panneauCases.add(caseACocher);
            }

            FlowLayoutMG.unifieTailleComposants(panneauCases);

// --------------------------------------------------------------------------
// validate() : calcul de la nouvelle taille du panneauCases
// revalidate() : on en informe le JScrollPane defileur
// repaint() : on redessine
// --------------------------------------------------------------------------
            panneauCases.validate();
            panneauCases.revalidate();
            panneauCases.repaint();
        }
        else
        {
            composantsPanneauCases = panneauCases.getComponents();

            for (int i = 0; i < composantsPanneauCases.length; i++)
            {
                System.out.print(((JCheckBox)composantsPanneauCases[i]).
                                 getText());

                if (((JCheckBox) composantsPanneauCases[i]).isSelected())
                {
                    System.out.println(" selectionnee");
                }
                else
                {
                    System.out.println(" non selectionnee");
                }
            }

            System.out.println("\n");
        }
    }
}
