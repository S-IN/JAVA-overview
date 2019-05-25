// ==========================================================================
// Classe Figure : figures geometriques
// --------------------------------------------------------------------------
// Figure implemente l'interface Comparable typee : Comparable<Figure>
// La methode compareTo prend un parametre de type Figure. 
// ==========================================================================

public abstract class Figure implements Comparable<Figure>
{
    public int compareTo(Figure f)
    {
        return surface().compareTo(f.surface());
    }

// --------------------------------------------------------------------------
// Methode abstraite surface (permet de compiler Figure). Elle sera 
// surchargee dans les classes derivees
// --------------------------------------------------------------------------
    public abstract Float surface();
}
