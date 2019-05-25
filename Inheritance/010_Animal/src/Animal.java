// ==========================================================================
// Classe Animal :
// ==========================================================================

public class Animal
{
// --------------------------------------------------------------------------
// Proprietes :
// --------------------------------------------------------------------------
// vivant               : logique indiquant si l'animal est vivant.
// age                  : age de l'animal.
// nombreAnimauxVivants : variable statique indiquant le nb d'animaux vivants
// ageMaximum           : age maximum d'un animal de la la classe Animal.
// --------------------------------------------------------------------------
    private boolean vivant;
    private int age;
    private static int nombreAnimauxVivants = 0;
    private static int ageMaximum = 3;   // Cette propriete est un peu
                                         // artificielle. Il s'agit de faire
                                         // mourir un animal qui a ete cree
                                         // comme une instance de Animal et
                                         // non d'une classe derivee (Vache)

// --------------------------------------------------------------------------
// Constructeur avec paramètre : la création d'un objet animal s'accompagne
// de l'initialisation de son age. L'animal est vivant par défaut.
// --------------------------------------------------------------------------
    public Animal(int age)
    {
        this.age = age;
        vivant = true;
        nombreAnimauxVivants++;
    }

// --------------------------------------------------------------------------
// Methodes :
// --------------------------------------------------------------------------
    public void vieillir()
    {
        age++;

        if (age > getAgeMaximum())
        {
            mourir();
        }
        else
        {
            afficheAge();
        }
    }

    public void afficheAge()
    {
        System.out.println("Cet animal a maintenant " + age + " ans.");
    }

    public int getAgeMaximum()
    {
        return ageMaximum;
    }

    public void mourir()
    {
        vivant = false;
        nombreAnimauxVivants--;
        afficheFairePart();
    }

    public void afficheFairePart()
    {
        System.out.println("Un animal vient de mourir !");
    }

    public void crier()
    {
        System.out.println("Je suis un animal !");
    }

    public static int getNombreAnimauxVivants()
    {
        return nombreAnimauxVivants;
    }

    public int getAge()
    {
        return age;
    }

    public boolean getVivant()
    {
        return vivant;
    }
}
