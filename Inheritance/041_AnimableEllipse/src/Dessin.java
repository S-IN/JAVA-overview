// ==========================================================================
// APPLICATION TestAnimable : 
// -------------------------------------------------------------------------- 
// Classe Dessin
// ==========================================================================

import java.awt.*;
import java.util.*;
import animationMG.*;
import java.awt.geom.Ellipse2D;

public class Dessin extends DessinAbstract
{
    private int rouge, vert, bleu;
    private int transparence;

    public Dessin()
    {
        Random r = new Random();
        rouge = r.nextInt(256);
        vert = r.nextInt(256);
        bleu = r.nextInt(256);
        transparence = r.nextInt(206) + 50;
    }

    public Color getCouleur()
    {
        return new Color(rouge, vert, bleu);
    }

    public int getTransparence()
    {
        return transparence;
    }

    public Shape getDessin()
    {
        Ellipse2D forme = new Ellipse2D.Double(0, 0, 50, 100);
        return forme;
    }
}