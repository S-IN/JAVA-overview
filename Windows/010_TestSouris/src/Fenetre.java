// ==========================================================================
// Classe Fenetre
// --------------------------------------------------------------------------
// Cette classe implemente les interfaces :
// 
// MouseListener : ecoute des clics, ecoute des entrees/sorties de la souris
//                 dans la fenetre.
//
// MouseMotionListener : ecoute des deplacements de la souris.
// ==========================================================================

import javax.swing.*;
import java.awt.event.*;
import utilitairesMG.graphique.*;

public class Fenetre extends JFrame implements MouseListener,
                                               MouseMotionListener
{

// --------------------------------------------------------------------------
// Constructeur :
// Ici, la fenetre est son propre ecouteur. On appelle les methodes
// addXxxListener des la construction.
// --------------------------------------------------------------------------
    public Fenetre(String s)
    {
        super(s);
        setBounds(200, 100, 300, 200);

        addWindowListener(new EcouteFenetre());

        addMouseListener(this);
        addMouseMotionListener(this);

        setVisible(true);
    }

// --------------------------------------------------------------------------
// Méthodes de l'interface MouseListener
// --------------------------------------------------------------------------
// --------------------------------------------------------------------------
// On clique avec la souris : Pressed + Released
// --------------------------------------------------------------------------
    public void mouseClicked(MouseEvent e)
    {
        System.out.println("mouseClicked : " + e.getX() + " " + e.getY());
    }

// --------------------------------------------------------------------------
// On enfonce un bouton de la souris
// --------------------------------------------------------------------------
    public void mousePressed(MouseEvent e)
    {
        System.out.println("mousePressed : " + e.getX() + " " + e.getY());
    }

// --------------------------------------------------------------------------
// On relâche un bouton de la souris
// --------------------------------------------------------------------------
    public void mouseReleased(MouseEvent e)
    {
        System.out.println("mouseReleased : " + e.getX() + " " + e.getY());
    }

// --------------------------------------------------------------------------
// La souris entre dans la fenêtre
// --------------------------------------------------------------------------
    public void mouseEntered(MouseEvent e)
    {
        System.out.println("mouseEntered : " + e.getX() + " " + e.getY());
    }

// --------------------------------------------------------------------------
// La souris sort de la fenêtre
// --------------------------------------------------------------------------
    public void mouseExited(MouseEvent e)
    {
        System.out.println("mouseExited : " + e.getX() + " " + e.getY());
    }

// --------------------------------------------------------------------------
// Méthodes de l'interface MouseMotionListener
// --------------------------------------------------------------------------
// --------------------------------------------------------------------------
// La souris est dragged
// --------------------------------------------------------------------------
    public void mouseDragged(MouseEvent e)
    {
        System.out.println("mouseDragged : " + e.getX() + " " + e.getY());
    }

// --------------------------------------------------------------------------
// La souris est bougee
// --------------------------------------------------------------------------
    public void mouseMoved(MouseEvent e)
    {
        System.out.println("mouseMoved : " + e.getX() + " " + e.getY());
    }
}
