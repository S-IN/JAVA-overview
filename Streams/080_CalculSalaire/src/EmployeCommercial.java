public class EmployeCommercial extends Employe
{
    private Double salaire;
    private Double ca;

    public EmployeCommercial(int matricule, 
                             String nom,
                             Double salaire,
                             Double ca)
    {
        super(matricule, nom);
        this.salaire = salaire;
        this.ca = ca;
    }
    
    public Double calculSalaire()
    {
        return salaire + (0.15 * ca);
    }
    
    public String getLibelle()
    {
        return "Employe commercial matricule : ";
    }
}
