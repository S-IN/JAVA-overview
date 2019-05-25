// ==========================================================================
// TestEmploye : constructeurs, surdefinition de methodes
// --------------------------------------------------------------------------
// Chaque employe possede un matricule(int) et un nom(String). 
// Des proprietes supplementaires sont necessaires au traitement.
//
// La classe Employe possede deux constructeurs :
// - un constructeur d'initialisation qui recoit deux parametres (maticule, nom)
// - un constructeur d'initialisation qui ne recoit  que le nom de l'employe. 
//   Le matricule est attribue automatiquement. Dans l'exemple, le premier 
//   employe cree avec ce constructeur recoit le matricule 100, le deuxieme 101...
//
// Elle possede des méthodes qui apparaissent dans la classe principale.
// ==========================================================================

import utilitairesMG.divers.*;

public class TestEmploye
{
    public static void main(String argv[])
    {
        Employe e1, e2, e3;
        String nom;

        e1 = new Employe("MERLE");
        System.out.println(e1);

        e2 = new Employe(12, "COLIBRI");
        System.out.println("\n" + e2);

        e3 = new Employe("VAUTOUR");
        System.out.println("\n" + e3);

        System.out.println("\nNombre d'employés créés : " +
                Employe.getCompteur());
        System.out.println("Prochain matricule automatique : " +
                Employe.getNumeroAutomatique());

        System.out.print("\nNouveau Nom de l'employe " + e3.getNom() + " : ");
        nom = Clavier.readString();
        e3.setNom(nom);
        System.out.println(e3);
    }
}
