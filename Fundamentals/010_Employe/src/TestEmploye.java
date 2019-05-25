// ======================================================================
// TestEmploye : 
// Classes, objets, proprietes, methodes, constructeur par defaut.
// ======================================================================

public class TestEmploye
{
    public static void main(String argv[])
    {
        Employe e;
        String nom;
        int matricule;

        e = new Employe();
        e.afficheEmploye();

        System.out.print("\nAprès changement de nom :\n");
        nom = "VAUTOUR FAUVE";
        e.setNom(nom);
        e.afficheEmploye();

        System.out.print("\nAprès changement de matricule :\n");
        matricule = 17;
        e.setMatricule(matricule);
        e.afficheEmploye();
    }
}
