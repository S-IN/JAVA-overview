// ======================================================================
// Classe Employe
// ======================================================================

public class Employe
{
// ----------------------------------------------------------------------
// Proprietes
// ----------------------------------------------------------------------
    private int matricule;
    private String nom;

// ----------------------------------------------------------------------
// Methodes
// ----------------------------------------------------------------------
    public void afficheEmploye()
    {
        System.out.println("Nom       : " + nom + "\n" +
                           "Matricule : " + matricule);
    }

    public void setNom(String nom)
    {
        this.nom = nom;
    }

    public void setMatricule(int matricule)
    {
        this.matricule = matricule;
    }
}
