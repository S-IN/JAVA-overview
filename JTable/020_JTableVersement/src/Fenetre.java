// ==========================================================================
// Classe Fenetre                                         Application JTable6
// ==========================================================================

import javax.swing.*;
import java.util.*;
import java.awt.*;
import utilitairesMG.divers.*;
import utilitairesMG.graphique.EcouteFenetre;
import utilitairesMG.graphique.table.ModeleColonneTable;

public class Fenetre extends JFrame
{
    private JPanel panneauFond;

    private JTable table;
    private ModeleTableVersement modeleTable;
    private ModeleColonneTable modeleColonne;

    private JScrollPane defileur;

// --------------------------------------------------------------------------
// Constructeur
// --------------------------------------------------------------------------
    public Fenetre(String s, Vector<Versement> listeVersements,
                             Vector<Colonne> listeColonnes)
    {
        super(s);
        addWindowListener(new EcouteFenetre());

// --------------------------------------------------------------------------
// Création de l'objet JPanel :
// --------------------------------------------------------------------------
        panneauFond = new JPanel();
        panneauFond.setLayout(new BorderLayout());
        panneauFond.setPreferredSize(new Dimension(650, 150));

// --------------------------------------------------------------------------
// Création de l'objet JTable :
// --------------------------------------------------------------------------
        table = new JTable();
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

// --------------------------------------------------------------------------
// Recherche de la largeur en nombre de points de la lettre M de la police
// de la JTable.
// --------------------------------------------------------------------------
        Font fontParDefaut = table.getFont();
        int tailleM = table.getFontMetrics(fontParDefaut).stringWidth("M");

// --------------------------------------------------------------------------
// Creation des modeles de table et de colonne. 
// --------------------------------------------------------------------------
        modeleTable = new ModeleTableVersement(listeVersements, listeColonnes);
        modeleColonne = new ModeleColonneTable(listeColonnes, tailleM);

// --------------------------------------------------------------------------
// Ajout des modeles a la JTable. 
// --------------------------------------------------------------------------
        table.setModel(modeleTable);
        table.setColumnModel(modeleColonne);
        defileur = new JScrollPane(table);
        defileur.getViewport().setBackground(new Color(255, 170, 220));

        panneauFond.add(defileur);

        getContentPane().add(panneauFond);

        pack();
        setVisible(true);
    }
}
