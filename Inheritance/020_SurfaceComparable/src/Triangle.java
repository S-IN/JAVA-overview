// ==========================================================================
// Classe Triangle
// ==========================================================================

public class Triangle extends Figure
{
    private int base;           // Largeur de la base du triangle
    private int hauteur;        // Hauteur du triangle

// --------------------------------------------------------------------------
// Constructeur
// --------------------------------------------------------------------------
    public Triangle(int base, int hauteur)
    {
        this.base = base;
        this.hauteur = hauteur;
    }

// --------------------------------------------------------------------------
// Methodes
// --------------------------------------------------------------------------
    public Float surface()
    {
        return (base * hauteur) / 2.f;
    }

    public String toString()
    {
        return ("Triangle de base " + base
            + ", de hauteur " + hauteur
            + ", Surface : " + surface());
    }
}
