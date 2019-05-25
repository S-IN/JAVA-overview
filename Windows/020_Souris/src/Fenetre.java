// ==========================================================================
// Classe Fenetre
// --------------------------------------------------------------------------
// Cette classe implemente les interfaces :
// 
// MouseListener : 
//     ecoute des clics, ecoute des entrees/sorties de la souris dans la 
//     fenetre (MouseEvent).
//
// MouseMotionListener : 
//    ecoute des deplacements de la souris (MouseEvent).
//
// MouseWheelListener : 
//    ecoute des evenements de rotation de la roue de la souris 
//    (MouseWheelEvent).
// ==========================================================================

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import utilitairesMG.graphique.*;

public class Fenetre extends JFrame implements MouseListener,
                                        MouseMotionListener,
                                        MouseWheelListener
{

// --------------------------------------------------------------------------
// debutDragged : position de la souris au moment du debut de "draggage". 
//               En fait, il s'agit de la position de la souris au moment du
//               "pressed" qui precede le "draggage"...
// --------------------------------------------------------------------------
    private Point debutDragged;

// --------------------------------------------------------------------------
// Constructeur : 
// --------------------------------------------------------------------------
    public Fenetre(String s)
    {
        super(s);

        addWindowListener(new EcouteFenetre());

        setBounds(200, 100, 300, 200);

        addMouseListener(this);
        addMouseMotionListener(this);
        addMouseWheelListener(this);

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
        if (getTitle().compareTo("Deplacement vertical") == 0)
        {
            setTitle("Deplacement horizontal");
        }
        else
        {
            setTitle("Deplacement vertical");
        }
    }

// --------------------------------------------------------------------------
// On enfonce un bouton de la souris
// --------------------------------------------------------------------------
    public void mousePressed(MouseEvent e)
    {
        debutDragged = e.getPoint();
    }

// --------------------------------------------------------------------------
// On relâche un bouton de la souris
// --------------------------------------------------------------------------
    public void mouseReleased(MouseEvent e)
    {
    }

// --------------------------------------------------------------------------
// La souris entre dans la fenêtre
// --------------------------------------------------------------------------
    public void mouseEntered(MouseEvent e)
    {
    }

// --------------------------------------------------------------------------
// La souris sort de la fenêtre
// --------------------------------------------------------------------------
    public void mouseExited(MouseEvent e)
    {
    }

// --------------------------------------------------------------------------
// Méthodes de l'interface MouseMotionListener
// --------------------------------------------------------------------------
// --------------------------------------------------------------------------
// La souris est dragged
// --------------------------------------------------------------------------
    public void mouseDragged(MouseEvent e)
    {
        setLocation(getLocation().x + e.getX() - debutDragged.x,
            getLocation().y + e.getY() - debutDragged.y);
    }

// --------------------------------------------------------------------------
// La souris est bougee
// --------------------------------------------------------------------------
    public void mouseMoved(MouseEvent e)
    {
    }

// --------------------------------------------------------------------------
// Méthode de l'interface MouseWheelListener
// --------------------------------------------------------------------------
// --------------------------------------------------------------------------
// La roue de la souris est bougee
// --------------------------------------------------------------------------
    public void mouseWheelMoved(MouseWheelEvent e)
    {
        if (getTitle().compareTo("Deplacement vertical") == 0)
        {
            setLocation(getLocation().x, 
                        getLocation().y + e.getUnitsToScroll());
        }
        else
        {
            setLocation(getLocation().x + e.getUnitsToScroll(), 
                        getLocation().y);
        }
    }
}
