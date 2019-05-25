// ==========================================================================
// Classe Employe
// ==========================================================================

public class Employe
{

// --------------------------------------------------------------------------
// Proprietes
// --------------------------------------------------------------------------
    private static int compteur;
    private static int numeroAutomatique = 100;
    private int matricule;
    private String nom;

// --------------------------------------------------------------------------
// Constructeurs
// --------------------------------------------------------------------------
    public Employe(int matricule, String nom)
    {
        compteur++;
        this.matricule = matricule;
        this.nom = nom;
    }

    public Employe(String nom)
    {
        compteur++;
        this.matricule = numeroAutomatique;
        this.nom = nom;
        numeroAutomatique++;
    }

// --------------------------------------------------------------------------
// Methodes
// --------------------------------------------------------------------------
    public void setNom(String nom)
    {
        this.nom = nom;
    }

    public String getNom()
    {
        return nom;
    }

    public static int getCompteur()
    {
       return compteur;
    }

    public static int getNumeroAutomatique()
    {
       return numeroAutomatique;
    }

    public String toString()
    {
        return "Nom       : " + nom + "\n" +
               "Matricule : " + matricule;
    }
}