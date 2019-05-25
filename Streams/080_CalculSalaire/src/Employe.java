public abstract class Employe
{
    private int matricule;
    private String nom;
    
    public Employe(int matricule, String nom)
    {
        this.matricule = matricule;
        this.nom = nom;
    }
    
    public abstract Double calculSalaire();

    public abstract String getLibelle();
    
    public String toString()
    {
        return getLibelle() + matricule + ", nom : " + nom;
    }
}
