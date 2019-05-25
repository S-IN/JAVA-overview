// ==========================================================================
// Classe Moteur :  
// ==========================================================================

public class Moteur
{
// --------------------------------------------------------------------------
// Proprietes :
// --------------------------------------------------------------------------
// kMoteur : numéro de moteur à attribuer à tout nouveau moteur (compteur).
// numMoteur : numéro du moteur de l'objet courant.
// --------------------------------------------------------------------------
    private static int kMoteur = 1000;
    private String numMoteur;

// --------------------------------------------------------------------------
// Constructeurs :
// --------------------------------------------------------------------------
// Constructeur sans paramètre : utilise kMoteur pour initialiser le numéro
// de moteur numMoteur.
// --------------------------------------------------------------------------
    public Moteur()
    {
        kMoteur++;
        numMoteur = "NMOT" + kMoteur;
    }

// --------------------------------------------------------------------------
// Constructeur avec paramètre : utilise une variable num de type entier 
// pour initialiser numMoteur.
// --------------------------------------------------------------------------
    public Moteur(int num)
    {
        numMoteur = "NMOT" + num;
    }

// --------------------------------------------------------------------------
// Methode toString
// --------------------------------------------------------------------------
    public String toString()
    {
        return numMoteur;
    }
}
