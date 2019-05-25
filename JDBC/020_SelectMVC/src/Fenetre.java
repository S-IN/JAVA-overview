// ==========================================================================
// Classe Fenetre                                       Application SelectMVC
// --------------------------------------------------------------------------
// Cette fenetre a deux panneaux :
// Le premier contient une zone de saisie (JTextField) et une zone de
// message (JLabel).
// Le deuxieme contient une JTable remplie par la requete saisie dans le 
// premier panneau.
// ==========================================================================

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import utilitairesMG.divers.*;
import utilitairesMG.graphique.table.*;

public class Fenetre extends JFrame implements ActionListener
{

// --------------------------------------------------------------------------
// Panneau de fond
// --------------------------------------------------------------------------
    private JPanel panneauFond;

// --------------------------------------------------------------------------
// Panneau du haut : saisie de la requete
// --------------------------------------------------------------------------
    private JPanel panneauSaisie;

    private JPanel bordHaut;       // Panneaux de decoration pour entourer la 
    private JPanel bordBas;        // zone de saisie. Le JLabel zoneMessage
    private JPanel bordGauche;     // sera placé dans le panneau bordBas.
    private JPanel bordDroite;

    private JTextField saisie;
    private JLabel zoneMessage;

// --------------------------------------------------------------------------
// Panneau du bas : affichage de la table
// --------------------------------------------------------------------------
    private JPanel panneauTable;

    private JTable table;
    private JScrollPane defileTable;

// ==========================================================================
// Constructeur
// ==========================================================================
    public Fenetre(String s)
    {
        super(s);
        addWindowListener(new EcouteWindowClosing());

// --------------------------------------------------------------------------
// Creation et ajout des composants
// --------------------------------------------------------------------------
        panneauFond = new JPanel();
        panneauFond.setLayout(new BorderLayout());

// --------------------------------------------------------------------------
// Panneau du haut : panneauSaisie
// --------------------------------------------------------------------------
        panneauSaisie = new JPanel();
        panneauSaisie.setLayout(new BorderLayout());
        panneauSaisie.setBackground(Color.yellow);

// --------------------------------------------------------------------------
// Panneaux de presentation
// --------------------------------------------------------------------------
        bordHaut = new JPanel();
        bordHaut.setBackground(Color.white);
        bordHaut.add(new JLabel(" Saisir la requete a executer : "));
        panneauSaisie.add(bordHaut, BorderLayout.NORTH);

        bordBas = new JPanel();
        bordBas.setBackground(Color.white);
        bordBas.setPreferredSize(bordHaut.getPreferredSize());
        zoneMessage = new JLabel();
        zoneMessage.setForeground(Color.red);
        bordBas.add(zoneMessage);
        panneauSaisie.add(bordBas, BorderLayout.SOUTH);

        bordGauche = new JPanel();
        bordGauche.setBackground(Color.white);
        panneauSaisie.add(bordGauche, BorderLayout.WEST);

        bordDroite = new JPanel();
        bordDroite.setBackground(Color.white);
        panneauSaisie.add(bordDroite, BorderLayout.EAST);

        saisie = new JTextField("");
        saisie.setForeground(Color.blue);
        saisie.addActionListener(this);

        panneauSaisie.add(saisie);

// --------------------------------------------------------------------------
// Panneau du bas : panneauTable
// --------------------------------------------------------------------------
        panneauTable = new JPanel();
        panneauTable.setBackground(Color.yellow);
        panneauTable.setPreferredSize(new Dimension(500, 300));
        panneauTable.setLayout(new BorderLayout());

        panneauFond.add(panneauSaisie, BorderLayout.NORTH);
        panneauFond.add(panneauTable);

        getContentPane().add(panneauFond);

        pack();
        setVisible(true);
    }

// ==========================================================================
// Methode d'ecoute du JTextField   
// ==========================================================================
    public void actionPerformed(ActionEvent e)
    {
        String texteRequete;

// --------------------------------------------------------------------------
// Recuperation de la requete saisie et execution :
// --------------------------------------------------------------------------
        texteRequete = saisie.getText();
        Controleur.executeRequete(texteRequete);
    }

// ==========================================================================
// Affichage de la JTable  
// ==========================================================================
    public void afficheTable(Vector<Vector<Object>> listeLignes,
        Vector<Colonne> listeColonnes)
    {
        if (defileTable != null)
        {
            panneauTable.remove(defileTable);
        }

        table = new JTable(new ModeleTable(listeLignes, listeColonnes),
            new ModeleColonneTable(listeColonnes));

        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        defileTable = new JScrollPane(table);
        defileTable.getViewport().setBackground(Color.yellow);

        panneauTable.add(defileTable);

        zoneMessage.setText("");
        panneauFond.validate();
        panneauFond.repaint();
    }

// ==========================================================================
// Affichage d'une erreur de requete  
// ==========================================================================
    public void afficheMessage(String messageErreur)
    {
        zoneMessage.setText(messageErreur);
    }

// ==========================================================================
// Classe interne pour le traitement de la fermeture de la fenetre  
// ==========================================================================
    private class EcouteWindowClosing extends WindowAdapter
    {
        public void windowClosing(WindowEvent e)
        {
            Controleur.arreter();
        }
    }
}
