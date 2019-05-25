// ==========================================================================
// Classe Garage :  
// ==========================================================================

public class Garage
{
// --------------------------------------------------------------------------
// Proprietes :
// --------------------------------------------------------------------------
// nomGarage : nom du garage (fixe pour tous les garages)
// adresseGarage : adresse du garage
// --------------------------------------------------------------------------
    private static String nomGarage = "VOLIERE SA";
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
        return nomGarage + " - " + adresseGarage;
    }

    public static String getNomGarage()
    {
        return nomGarage;
    }
}
