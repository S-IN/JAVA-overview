// ==========================================================================
// Classe Cercle
// ==========================================================================

public class Cercle extends Figure
{
    private int rayon;          // Rayon du cercle

// --------------------------------------------------------------------------
// Constructeur
// --------------------------------------------------------------------------
    public Cercle(int rayon)
    {
        this.rayon = rayon;
    }

// --------------------------------------------------------------------------
// Methodes
// --------------------------------------------------------------------------
    public Float surface()
    {
        return (rayon * rayon) * (float)Math.PI;
    }

    public String toString()
    {
        return ("Cercle de rayon " + rayon
            + ", Surface : " + surface());
    }
}
