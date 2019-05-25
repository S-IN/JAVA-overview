public class EmployeHoraire extends Employe
{
    private Integer nombreHeures;
    private Double salaireHoraire;
    private Integer nombreHeuresSup;
    
    public EmployeHoraire(int matricule, 
                          String nom, 
                          Integer nombreHeures, 
                          Double salaireHoraire, 
                          Integer nombreHeuresSup)
    {
        super(matricule, nom);
        this.nombreHeures = nombreHeures;
        this.salaireHoraire = salaireHoraire;
        this.nombreHeuresSup = nombreHeuresSup;
    }
    
    public Double calculSalaire()
    {
        return (nombreHeures + 2 * nombreHeuresSup) * salaireHoraire;
    }
    
    public String getLibelle()
    {
        return "Employe horaire matricule : ";
    }
}
