// ==========================================================================
// APPLICATION TestAnimal
// --------------------------------------------------------------------------
// Héritage - redefinition de methode - polymorphisme
// ==========================================================================

public class TestAnimal
{
    public static void main(String argv[])
    {
        Animal tableAnimal[] = new Animal[2];
        int iAnimal;

// --------------------------------------------------------------------------
// Création des objets 
// --------------------------------------------------------------------------
        tableAnimal[0] = new Vache(1);
        tableAnimal[1] = new Animal(2);

// --------------------------------------------------------------------------
// Tant que des animaux sont vivants, on les fait crier et vieillir.
// --------------------------------------------------------------------------
        while (Animal.getNombreAnimauxVivants() > 0)
        {
            for (iAnimal = 0; iAnimal < 2; iAnimal++)
            {
                if (tableAnimal[iAnimal].getVivant())
                {
                    tableAnimal[iAnimal].crier();
                    tableAnimal[iAnimal].vieillir();
                }
            }
        }
    }
}
