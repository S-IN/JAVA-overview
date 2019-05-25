// ==========================================================================
// APPLICATION TestVoiture : TestVoiture avec tableaux
// ==========================================================================

import utilitairesMG.divers.*;

public class TestVoiture
{
    public static void main(String argv[])
    {
        Garage g1, g2;
        Vendeur tabVendeur[];       // Table des vendeurs
        Voiture tabVoiture[];       // Table des voitures
        int iVoiture;               // Indices
        int iVendeur;

        int nombreVoitures;         // Dimension de la table des voitures

        String nomVendeur;          // Nom du vendeur de la voiture
        int numeroMoteur;           // Numero de moteur de la voiture

// --------------------------------------------------------------------------
// Creation des garages
// --------------------------------------------------------------------------
        g1 = new Garage("Avenue des Champs Elysees 75008 PARIS");
        g2 = new Garage();

// --------------------------------------------------------------------------
// Creation du tableau des vendeurs
// --------------------------------------------------------------------------
        tabVendeur = new Vendeur[3];
        tabVendeur[0] = new Vendeur("Oie", "BERNACHE", g1);
        tabVendeur[1] = new Vendeur("Aigle", "ROYAL", g2);
        tabVendeur[2] = new Vendeur("Balbuzard", "PECHEUR", g1);

// --------------------------------------------------------------------------
// Creation et affichage des voitures
// --------------------------------------------------------------------------
        System.out.print("Entrer le nombre de voitures a creer : ");
        nombreVoitures = Clavier.readInt();
        tabVoiture = new Voiture[nombreVoitures];

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
            while ((iVendeur < tabVendeur.length)
                && (tabVendeur[iVendeur].getNomVendeur().
                    compareToIgnoreCase(nomVendeur) != 0))
            {
                iVendeur++;
            }

// --------------------------------------------------------------------------
// Vendeur non trouve
// --------------------------------------------------------------------------
            if (iVendeur >= tabVendeur.length)
            {
                if (numeroMoteur == 0)
                {
                    tabVoiture[iVoiture] = 
                        new Voiture();
                }
                else
                {
                    tabVoiture[iVoiture] = 
                        new Voiture(numeroMoteur);
                }
            }

// --------------------------------------------------------------------------
// Vendeur trouve
// --------------------------------------------------------------------------
            else
            {
                if (numeroMoteur == 0)
                {
                    tabVoiture[iVoiture] = 
                        new Voiture(tabVendeur[iVendeur]);
                }
                else
                {
                    tabVoiture[iVoiture] = 
                        new Voiture(numeroMoteur, tabVendeur[iVendeur]);
                }
            }
        }

// --------------------------------------------------------------------------
// Boucle d'impression des voitures
// --------------------------------------------------------------------------
        System.out.println("\n\nListe des voitures :\n");

        for (iVoiture = 0; iVoiture < nombreVoitures; iVoiture++)
        {
            System.out.println(tabVoiture[iVoiture]);
        }
    }
}
