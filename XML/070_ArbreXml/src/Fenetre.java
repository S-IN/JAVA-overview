// ==========================================================================
// Classe Fenetre                                        Application ArbreXml
// --------------------------------------------------------------------------
// Fenetre avec menu, panneau pour l'affichage d'une JTable, et zone de
// message (erreurs).
// ==========================================================================

import javax.swing.*;
import java.awt.*;
import org.w3c.dom.*;
import java.io.*;
import java.awt.event.*;

// ==========================================================================
// Classe Fenetre
// ==========================================================================
public class Fenetre extends JFrame implements ActionListener
{

// --------------------------------------------------------------------------
// Panneau de fond
// --------------------------------------------------------------------------
    private JPanel panneauFond;

// --------------------------------------------------------------------------
// Barre de menu
// --------------------------------------------------------------------------
    private JMenuBar barreMenu;
    private JMenu menuFichiers;
    private JMenuItem itemOuvrir;
    private JMenuItem itemQuitter;

    private JTree arbre;
    private JScrollPane defileArbre;

// --------------------------------------------------------------------------
// Panneau du bas : zone de message
// --------------------------------------------------------------------------
    private JPanel panneauMessage;
    private JLabel zoneMessage;

// ==========================================================================
// Constructeur
// ==========================================================================
    public Fenetre(String s)
    {
        super(s);
        addWindowListener(new EcouteWindowClosing());

// --------------------------------------------------------------------------
// Creation du menu de la fenetre
// --------------------------------------------------------------------------
        itemOuvrir = new JMenuItem("Ouvrir");
        itemOuvrir.setAccelerator(
            KeyStroke.getKeyStroke('O', InputEvent.ALT_MASK));

        itemOuvrir.addActionListener(this);

        itemQuitter = new JMenuItem("Quitter");
        itemQuitter.setAccelerator(
            KeyStroke.getKeyStroke('Q', InputEvent.ALT_MASK));

        itemQuitter.addActionListener(this);

        menuFichiers = new JMenu("Fichiers");
        menuFichiers.add(itemOuvrir);
        menuFichiers.add(itemQuitter);

        barreMenu = new JMenuBar();
        barreMenu.add(menuFichiers);

        setJMenuBar(barreMenu);

// --------------------------------------------------------------------------
// Creation et ajout des composants
// --------------------------------------------------------------------------
        panneauFond = new JPanel();
        panneauFond.setLayout(new BorderLayout());
        panneauFond.setBackground(Color.white);
        panneauFond.setPreferredSize(new Dimension(650, 250));

// --------------------------------------------------------------------------
// Panneau du bas : panneauMessage
// --------------------------------------------------------------------------
        panneauMessage = new JPanel();
        panneauMessage.setLayout(new BorderLayout());

        zoneMessage = new JLabel(" ");
        zoneMessage.setForeground(Color.red);

        panneauMessage.add(zoneMessage);

        panneauFond.add(panneauMessage, BorderLayout.SOUTH);

        getContentPane().add(panneauFond);

        pack();
        setVisible(true);
    }

// ==========================================================================
// Traitement du menu
// ==========================================================================
    public void actionPerformed(ActionEvent e)
    {
        int confirmation;
        JFileChooser choixFichier;
        File fichierXml;

        if (e.getSource() == itemQuitter)
        {
            Controleur.arreter();
        }
        else
        {
            choixFichier = new JFileChooser();
            choixFichier.setCurrentDirectory(new File("C:\\JAVA\\Fichiers"));
            choixFichier.setFileFilter(new FiltreFichiers());
            confirmation = choixFichier.showOpenDialog(this);

            if (confirmation == JFileChooser.APPROVE_OPTION)
            {
                fichierXml = choixFichier.getSelectedFile();
                Controleur.litXml(fichierXml);
            }
        }
    }

// ==========================================================================
// Affichage de l'arbre correspondant au Document DOM 
// ==========================================================================
    public void afficheArbre(Document document)
    {
        if (defileArbre != null)
        {
            panneauFond.remove(defileArbre);
        }

// --------------------------------------------------------------------------
// Création de l'objet JTree :
// --------------------------------------------------------------------------
        arbre = new JTree((new JTreeDom(document)).getRacineJTreeDom());

        defileArbre = new JScrollPane(arbre);
        panneauFond.add(defileArbre);

        panneauFond.validate();
        panneauFond.repaint();

        zoneMessage.setText(" ");
    }

// ==========================================================================
// Affichage d'un message dans la zoneMessage 
// ==========================================================================
    public void afficheMessage(String message)
    {
        zoneMessage.setText(message);
    }

// --------------------------------------------------------------------------
// Ecouteur de l'evenement fermeture de la fenetre
// --------------------------------------------------------------------------
    private class EcouteWindowClosing extends WindowAdapter
    {
        public void windowClosing(WindowEvent e)
        {
            Controleur.arreter();
        }
    }
}
