// ==========================================================================
// Classe Rectangle
// ==========================================================================

public class Rectangle extends Figure
{
    private int largeur;        // Largeur du rectangle
    private int hauteur;        // Hauteur du rectangle

// --------------------------------------------------------------------------
// Constructeur
// --------------------------------------------------------------------------
    public Rectangle(int largeur, int hauteur)
    {
        this.largeur = largeur;
        this.hauteur = hauteur;
    }

// --------------------------------------------------------------------------
// Methodes
// --------------------------------------------------------------------------
    public Float surface()
    {
        return ((float) largeur * (float) hauteur);
    }

    public String toString()
    {
        return ("Rectangle de largeur " + largeur
            + ", de hauteur " + hauteur
            + ", Surface : " + surface());
    }
}
