
import java.io.IOException;
import java.util.ArrayList;



public class Controleur
{
    public static void main(String args[]) throws IOException
    {
        ArrayList<Employe> listeEmployes;
        listeEmployes = FichierEmploye.lireEmployes("Employe.txt");
        
        System.out.println("Nombre d'employes : " + listeEmployes.size());
        
        for (int i = 0; i < listeEmployes.size(); i++)
        {
            System.out.print(listeEmployes.get(i));
            System.out.println(", salaire : " + 
                listeEmployes.get(i).calculSalaire());
        }
    }
}
