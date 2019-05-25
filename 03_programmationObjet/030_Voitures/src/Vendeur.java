// ==========================================================================
// Classe Vendeur :  
// ==========================================================================

public class Vendeur
{
// --------------------------------------------------------------------------
// Proprietes :
// --------------------------------------------------------------------------
    private String prenomVendeur;
    private String nomVendeur;
    private Garage garageVendeur;

// --------------------------------------------------------------------------
// Constructeur :
// --------------------------------------------------------------------------
    public Vendeur(String prenomVendeur,
                   String nomVendeur,
                   Garage garageVendeur)
    {
        this.prenomVendeur = prenomVendeur;
        this.nomVendeur = nomVendeur;
        this.garageVendeur = garageVendeur;
    }

// --------------------------------------------------------------------------
// Methodes :
// --------------------------------------------------------------------------
    public String toString()
    {
        return "\n" + prenomVendeur + " " + nomVendeur + "\n" + garageVendeur;
    }
}
