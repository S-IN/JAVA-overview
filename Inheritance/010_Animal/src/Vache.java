// ==========================================================================
// Classe Vache :
// ==========================================================================

public class Vache extends Animal
{
    private static int ageMaximum = 5;

// --------------------------------------------------------------------------
// Constructeur de la classe Vache :
// --------------------------------------------------------------------------
// Appel du constructeur de la classe Animal : super(int a)
// --------------------------------------------------------------------------
    public Vache(int a)
    {
        super(a);
    }

// --------------------------------------------------------------------------
// Methodes :
// --------------------------------------------------------------------------
    public void afficheAge()
    {
        System.out.println("Cette vache a maintenant  " + getAge() + " ans.");
    }

    public int getAgeMaximum()
    {
        return ageMaximum;
    }

    public void afficheFairePart()
    {
        System.out.println("Une vache vient de mourir !");
    }

    public void crier()
    {
        System.out.println("MEUH !");
    }
}
