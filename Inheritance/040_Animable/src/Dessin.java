// ==========================================================================
// APPLICATION TestAnimable : 
// -------------------------------------------------------------------------- 
// Classe Dessin
// ==========================================================================

import java.awt.*;
import java.util.*;
import animationMG.*;

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
        int tabX[] =
        {
            10, 180, 80, 20
        };
        int tabY[] =
        {
            10, 50, 100, 70
        };

        Polygon forme = new Polygon(tabX, tabY, 4);
        return forme;
    }
}