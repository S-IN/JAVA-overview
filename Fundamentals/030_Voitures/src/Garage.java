// ==========================================================================
// Classe Garage :  
// ==========================================================================

public class Garage
{
// --------------------------------------------------------------------------
// Proprietes :
// --------------------------------------------------------------------------
// nomMarque     : nom de la marque (fixe pour tous les garages)
// adresseGarage : adresse du garage
// --------------------------------------------------------------------------
    private static String nomMarque = "VOLIERE SA";
    private String adresseGarage;

// --------------------------------------------------------------------------
// Constructeurs :
// --------------------------------------------------------------------------
    public Garage(String adresseGarage)
    {
        this.adresseGarage = adresseGarage;
    }

    public Garage()
    {
        adresseGarage = "Rue Paradis 13001 MARSEILLE";
    }

// --------------------------------------------------------------------------
// Methodes :
// --------------------------------------------------------------------------
    public String toString()
    {
        return nomMarque + " - " + adresseGarage;
    }

    public static String getNomMarque()
    {
        return nomMarque;
    }
}
