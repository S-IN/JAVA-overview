// ==========================================================================
// APPLICATION TestVoiture : TestVoiture avec vecteurs
// ==========================================================================

import java.util.*;               
import utilitairesMG.divers.*;    

public class TestVoiture
{
    public static void main(String argv[])
    {
        Garage g1, g2;
        Vector<Vendeur> vVendeur;   // Vecteur des vendeurs
        Vector<Voiture> vVoiture;   // Vecteur des voitures
        int iVoiture;               // Indices
        int iVendeur;

        int nombreVoitures;         // Nombre de voitures a creer

        String nomVendeur;          // Nom du vendeur de la voiture
        int numeroMoteur;           // Numero de moteur de la voiture

// --------------------------------------------------------------------------
// Creation des garages
// --------------------------------------------------------------------------
        g1 = new Garage("Avenue des Champs Elysees 75008 PARIS");
        g2 = new Garage();

// --------------------------------------------------------------------------
// Creation du vecteur des vendeurs
// --------------------------------------------------------------------------
        vVendeur = new Vector<Vendeur>();
        vVendeur.add(new Vendeur("Oie", "BERNACHE", g1));
        vVendeur.add(new Vendeur("Aigle", "ROYAL", g2));
        vVendeur.add(new Vendeur("Balbuzard", "PECHEUR", g1));

// --------------------------------------------------------------------------
// Creation du vecteur des voitures 
// --------------------------------------------------------------------------
        vVoiture = new Vector<Voiture>();
        System.out.print("Entrer le nombre de voitures a creer : ");
        nombreVoitures = Clavier.readInt();

// --------------------------------------------------------------------------
// Boucle de saisie des caracteristiques des voitures
// --------------------------------------------------------------------------
        for (iVoiture = 0; iVoiture < nombreVoitures; iVoiture++)
        {
            System.out.println("\n\nVoiture numero " + (iVoiture + 1) + " :");

            System.out.print("Numero de moteur (ou 0) : ");
            numeroMoteur = Clavier.readInt();

            System.out.print("Nom du vendeur : ");
            nomVendeur = Clavier.readString();

// --------------------------------------------------------------------------
// Boucle de recherche du nom de vendeur dans les objets de tabVendeur
// --------------------------------------------------------------------------
            iVendeur = 0;
            while ((iVendeur < vVendeur.size()) && 
                   (vVendeur.get(iVendeur).getNomVendeur().
                   compareTo(nomVendeur) != 0))
            {
                iVendeur++;
            }

// --------------------------------------------------------------------------
// Vendeur non trouve
// --------------------------------------------------------------------------
            if (iVendeur >= vVendeur.size())
            {
                if (numeroMoteur == 0)
                {
                    vVoiture.add(new Voiture());
                }
                else
                {
                    vVoiture.add(new Voiture(numeroMoteur));
                }
            }

// --------------------------------------------------------------------------
// Vendeur trouve
// --------------------------------------------------------------------------
            else
            {
                if (numeroMoteur == 0)
                {
                    vVoiture.add(
                        new Voiture(vVendeur.get(iVendeur)));
                }
                else
                {
                    vVoiture.add(
                        new Voiture(numeroMoteur, vVendeur.get(iVendeur)));
                }
            }
        }

// --------------------------------------------------------------------------
// Boucle d'impression des voitures
// --------------------------------------------------------------------------
        System.out.println("\n\nListe des voitures :\n");

        for (iVoiture = 0; iVoiture < vVoiture.size(); iVoiture++)
        {
            System.out.println(vVoiture.get(iVoiture));
        }
    }
}
