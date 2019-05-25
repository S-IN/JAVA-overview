// ==========================================================================
// Classe Voiture :  
// ==========================================================================

public class Voiture
{
// --------------------------------------------------------------------------
// Proprietes :
// --------------------------------------------------------------------------
// kVoiture : compteur du nombre de voitures instanciees.
// numVoiture : numéro de voiture de l'objet courant.
// vendeurVoiture : vendeur de la voiture.
// moteurVoiture : moteur de la voiture.
// --------------------------------------------------------------------------
    private static int kVoiture = 0;

    private int numVoiture;
    private Vendeur vendeurVoiture;
    private Moteur moteurVoiture;

// --------------------------------------------------------------------------
// Constructeurs : utilisent kVoiture pour initialiser numVoiture.
// --------------------------------------------------------------------------
// Constructeur sans paramètre.
// --------------------------------------------------------------------------
    public Voiture()
    {
        kVoiture++;
        numVoiture = kVoiture;
        moteurVoiture = new Moteur();
    }

// --------------------------------------------------------------------------
// Constructeur avec paramètre : idem que le précédent mais ce constructeur
// utilise une variable num de type entier pour initialiser le numéro de 
// moteur de la voiture.
// --------------------------------------------------------------------------
    public Voiture(int num)
    {
        kVoiture++;
        numVoiture = kVoiture;
        moteurVoiture = new Moteur(num);
    }

// --------------------------------------------------------------------------
// Constructeur avec paramètre : idem que le premier mais ce constructeur
// utilise une variable vendeurVoiture de type Vendeur pour initialiser le
// nom du vendeur de la voiture.
// --------------------------------------------------------------------------
    public Voiture(Vendeur vendeurVoiture)
    {
        kVoiture++;
        numVoiture = kVoiture;
        moteurVoiture = new Moteur();
        this.vendeurVoiture = vendeurVoiture;
    }

// --------------------------------------------------------------------------
// Constructeur avec paramètres : combinaison des deux constructeurs
// précédents.
// --------------------------------------------------------------------------
    public Voiture(int num, Vendeur vendeurVoiture)
    {
        kVoiture++;
        numVoiture = kVoiture;
        moteurVoiture = new Moteur(num);
        this.vendeurVoiture = vendeurVoiture;
    }

// --------------------------------------------------------------------------
// Surcharge de la methode toString de la classe Object
// --------------------------------------------------------------------------
// Cette méthode est exécutée lors de l'appel "System.out.println(v1)".
// Quand la methode println s'exécute, elle cherche une méthode toString 
// correspondant à l'objet. Si elle ne la trouve pas, elle exécute celle de
// la classe Object, qui affichera l'adresse mémoire de v1...
// --------------------------------------------------------------------------
    public String toString()
    {
        if (vendeurVoiture != null)
        {
            return "\nVOITURE   : " + numVoiture
                + "\nMOTEUR    : " + moteurVoiture
                + "\nVENDEUR   : " + vendeurVoiture;
        }
        else
        {
            return "\nVOITURE   : " + numVoiture
                + "\nMOTEUR    : " + moteurVoiture
                + "\nVENDEUR INCONNU";
        }
    }
}
