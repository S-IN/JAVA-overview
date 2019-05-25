// ==========================================================================
// PROJET GRAPHIQUE JDBC : PROGRAMME COMPLET (Classe Analyseur)
// --------------------------------------------------------------------------
// Le panneau 1 est un PanneauRequete
// Le panneau 2 est un PanneauBoutons
// Le panneau 3 est un PanneauTable
// Le panneau 4 est un PanneauListe
// Le panneau 5 est un PanneauCases
// ==========================================================================

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.event.*;
import java.util.*;
import utilitairesMG.divers.*;
import utilitairesMG.graphique.table.*;

// ==========================================================================
// Classe MaFenetre
// ==========================================================================
public class FenetreAnalyseur extends JFrame implements ActionListener,
    ListSelectionListener
{

// ==========================================================================
// Proprietes
// ==========================================================================
    private PanneauRequete monPanneau1;
    private JLabel enteteRequete;
    private JTextArea zoneRequete;
    private JLabel erreurRequete;

    private PanneauBoutons monPanneau2;
    private Vector<JButton> vBoutons;

    private JPanel monPanneau12;

    private JPanel monPanneau3;
    private JTable table;

    private PanneauListe monPanneau4;
    private JLabel enteteListe;
    private JList zoneListe;
    private DefaultListModel elementsListe;
    private int ligneSelectionnee = -1;

    private JPanel monPanneau5;
    private JLabel enteteCases;
    private Vector<JCheckBox> vCases;
    private Vector<JCheckBox> vCasesSelect;

    private JPanel monPanneau45;
    private JPanel monPanneau345;

// ==========================================================================
// Constructeur :
// ==========================================================================
    FenetreAnalyseur(String s, Vector listeTables)
    {
        super(s);
        addWindowListener(new EcouteFenetre());

// --------------------------------------------------------------------------
// Creation des composants a ajouter au panneau monPanneau1 :
// --------------------------------------------------------------------------
        enteteRequete = new JLabel(" Saisir la requete a executer : ");
        zoneRequete = new JTextArea(4, 40);
        erreurRequete = new JLabel(" ");

// --------------------------------------------------------------------------
// Creation de monPanneau1 :
// --------------------------------------------------------------------------
        monPanneau1 = new PanneauRequete(enteteRequete,
            zoneRequete,
            erreurRequete);

// --------------------------------------------------------------------------
// Creation du vecteur de boutons a ajouter au panneau monPanneau2 :
// --------------------------------------------------------------------------
        vBoutons = new Vector<JButton>();
        vBoutons.addElement(new JButton("Exécuter"));
        vBoutons.addElement(new JButton("Assistant"));

        for (int i = 0; i < vBoutons.size(); i++)
        {
            (vBoutons.elementAt(i)).addActionListener(this);
        }

// --------------------------------------------------------------------------
// Creation du panneau 2 :
// --------------------------------------------------------------------------
        monPanneau2 = new PanneauBoutons(vBoutons);
        monPanneau2.setBackground(Color.white);

// --------------------------------------------------------------------------
// Creation du panneau monPanneau12 :
// --------------------------------------------------------------------------
        monPanneau12 = new JPanel();
        monPanneau12.setLayout(new BorderLayout());

        monPanneau12.add(monPanneau1, BorderLayout.NORTH);
        monPanneau12.add(monPanneau2);

// --------------------------------------------------------------------------
// Creation du panneau destine a recevoir le PanneauTable resultat de la
// requete. Ce panneau est rempli dynamiquement. Il est vide au depart.
// --------------------------------------------------------------------------
        monPanneau3 = new JPanel();

// --------------------------------------------------------------------------
// Creation de la JList a ajouter au panneau monPanneau4 :
// --------------------------------------------------------------------------
        enteteListe = new JLabel(" Sélectionner la table a traiter : ");

        elementsListe = new DefaultListModel();
        for (int i = 0; i < listeTables.size(); i++)
        {
            elementsListe.addElement(listeTables.elementAt(i));
        }

        zoneListe = new JList(elementsListe);
        zoneListe.addListSelectionListener(this);

// ---------------------------------------------------------------------
// Creation de monPanneau4 :
// ---------------------------------------------------------------------
        monPanneau4 = new PanneauListe(enteteListe, zoneListe);

// ---------------------------------------------------------------------
// Creation de l'entete de la zone de cases a cocher :
// ---------------------------------------------------------------------
        enteteCases
            = new JLabel(" Cocher les colonnes a sélectionner : ");

// ---------------------------------------------------------------------
// Creation du panneau destine a recevoir le PanneauCases resultat de la
// requete sur les colonnes de la table selectionnee dans le panneau 4.
// Ce panneau est rempli dynamiquement. Il est vide au depart.
// ---------------------------------------------------------------------
        monPanneau5 = new JPanel();

// --------------------------------------------------------------------------
// Creation du panneau monPanneau45 : GridLayout
// --------------------------------------------------------------------------
        monPanneau45 = new JPanel();
        monPanneau45.setLayout(new GridLayout(1, 2));
        monPanneau45.add(monPanneau4);
        monPanneau45.add(monPanneau5);

// --------------------------------------------------------------------------
// Creation du panneau monPanneau345 : CardLayout
// --------------------------------------------------------------------------
        monPanneau345 = new JPanel();
        monPanneau345.setLayout(new CardLayout());
        monPanneau345.add(monPanneau45, "toto");
        monPanneau345.add(monPanneau3, "tata");

        getContentPane().add(monPanneau12, BorderLayout.NORTH);
        getContentPane().add(monPanneau345, BorderLayout.CENTER);

        pack();
        setVisible(true);
    }

// ==========================================================================
// actionPerformed
// --------------------------------------------------------------------------
// Traitement d'une action sur les boutons Executer, Assistant ou sur les 
// cases à cocher...
// ==========================================================================
    public void actionPerformed(ActionEvent e)
    {
        JCheckBox caseCliquee;
        String select;

        if (e.getSource() == vBoutons.elementAt(0))
        {
            if (zoneRequete.getSelectedText() != null)
            {
                select = zoneRequete.getSelectedText();
            }
            else
            {
                select = zoneRequete.getText();
            }
            Controleur.executeRequete(select);
        }
        else if (e.getSource() == vBoutons.elementAt(1))
        {
            ((CardLayout) (monPanneau345.getLayout())).first(monPanneau345);
        }
        else
        {
            caseCliquee = (JCheckBox) e.getSource();

            if (caseCliquee.isSelected())
            {
                vCasesSelect.addElement(caseCliquee);
            }
            else
            {
                vCasesSelect.removeElement(caseCliquee);
            }

            zoneRequete.setText(afficheRequete());
        }
    }

// ==========================================================================
// Affichage du resultat du select  
// ==========================================================================
    public void afficheSelect(Vector<Colonne> colonnes,
        Vector<Vector<Object>> lignes)
    {
        monPanneau345.remove(monPanneau3);
        table = new JTable(new ModeleTable(lignes, colonnes),
            new ModeleColonneTable(colonnes));

        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        monPanneau3 = new PanneauTable(table);

        erreurRequete.setText(" ");
        monPanneau345.add(monPanneau3, "tata");

        validate();
        repaint();

        ((CardLayout) (monPanneau345.getLayout())).last(monPanneau345);
    }

// ==========================================================================
// Affichage d'une erreur de requete  
// ==========================================================================
    public void afficheErreur(String messageErreur)
    {
        erreurRequete.setText(messageErreur);
    }

// ==========================================================================
// valueChanged
// --------------------------------------------------------------------------
// Traitement d'une action sur la liste des tables...
// ==========================================================================
    public void valueChanged(ListSelectionEvent e)
    {
        int i;
        int nombreColonnes;
        String nomColonne;

        Controleur.listeColonnes("SELECT * FROM "
            + zoneListe.getSelectedValue());
    }

// ==========================================================================
// Affichage du resultat du select de la liste des colonnes  
// ==========================================================================
    public void afficheListeColonnes(Vector<Colonne> colonnes)
    {
        int i;
        int nombreColonnes;
        String nomColonne;

        monPanneau45.remove(monPanneau5);

        nombreColonnes = colonnes.size();

        vCases = new Vector<JCheckBox>();
        vCasesSelect = new Vector<JCheckBox>();

        for (i = 0; i < nombreColonnes; i++)
        {
            nomColonne = colonnes.elementAt(i).getNom();
            vCases.addElement(new JCheckBox(nomColonne));
            (vCases.elementAt(i)).addActionListener(this);
        }

        monPanneau5 = new PanneauCases(enteteCases, vCases);
        monPanneau45.add(monPanneau5);

        validate();
        repaint();
        ligneSelectionnee = zoneListe.getSelectedIndex();

        zoneRequete.setText(afficheRequete());
    }

// ==========================================================================
// afficheRequete
// ==========================================================================
    public String afficheRequete()
    {
        String texteRequete = "SELECT  ";
        int i;

        if (vCasesSelect.size() == 0)
        {
            texteRequete += "*  ";
        }
        else
        {
            texteRequete += (vCasesSelect.elementAt(0)).getText();

            i = 1;
            while (i < vCasesSelect.size())
            {
                texteRequete += ", ";
                texteRequete += (vCasesSelect.elementAt(i)).getText();
                i++;
            }
            texteRequete += " ";
        }

        texteRequete += "FROM ";
        texteRequete += zoneListe.getSelectedValue();

        return texteRequete;
    }

// ==========================================================================
// Classe interne pour le traitement de la fermeture de la fenetre  
// ==========================================================================
    private class EcouteFenetre extends WindowAdapter
    {
        public void windowClosing(WindowEvent e)
        {
            Controleur.arreter();
        }
    }
}
