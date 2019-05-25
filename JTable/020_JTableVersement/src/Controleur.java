// ==========================================================================
// Classe Controleur   
// -------------------------------------------------------------------------- 
// La JTable est remplie avec un vecteur d'objets Versement. Elle utilise un
// modele de table ET un modele de colonne.
// ==========================================================================

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;
import utilitairesMG.graphique.*;
import utilitairesMG.divers.*;

public class Controleur
{

// -------------------------------------------------------------------------- 
// Programme principal de l'application
// -------------------------------------------------------------------------- 
    public static void main(String args[])
    {
        Vector<Colonne> listeColonnes;
        Vector<Versement> listeVersements;

// -------------------------------------------------------------------------- 
// Creation des vecteurs des donnees a afficher.
// -------------------------------------------------------------------------- 
        listeVersements = creeListeVersements();
        listeColonnes = creeListeColonnes();

// -------------------------------------------------------------------------- 
// Affichage de la fenetre.
// -------------------------------------------------------------------------- 
        javax.swing.SwingUtilities.invokeLater
        (
            new Runnable()
            {
                public void run()
                {
                    LF.setLF();
                    Fenetre fenetre = 
                        new Fenetre("JTable éditable des versements", 
                                    listeVersements, 
                                    listeColonnes);
                }
            }
        );
    }

// -------------------------------------------------------------------------- 
// Creation de la liste des Versements a afficher dans la JTable
// -------------------------------------------------------------------------- 
// Remarque : listeVersements est un vecteur d'objets Versement...
// -------------------------------------------------------------------------- 
    public static Vector<Versement> creeListeVersements()
    {
        Vector<Versement> listeVersements = new Vector<Versement>();
        Versement versement;

        versement = new Versement();
        versement.setNumero(1);
        versement.setDate(new Date());
        versement.setMontant(new BigDecimal(17));
        versement.setNumeroContact(100);

        listeVersements.addElement(versement);

        versement = new Versement();
        versement.setNumero(3);
        try
        {
            versement.setDate(new DateFr("31/12/2017").getTime());
        }
        catch(ParseException e)
        {
        }
        versement.setMontant(new BigDecimal(17.45));
        versement.setNumeroContact(100);

        listeVersements.addElement(versement);

        return listeVersements;
    }

// -------------------------------------------------------------------------- 
// Creation de la liste des colonnes a afficher dans la JTable
// -------------------------------------------------------------------------- 
    public static Vector<Colonne> creeListeColonnes()
    {
        Vector<Colonne> listeColonnes = new Vector<Colonne>();

// --------------------------------------------------------------------------
// Creation du vecteur des colonnes
// --------------------------------------------------------------------------
        listeColonnes.addElement(
            new Colonne("NUMERO", new Integer(5), "java.lang.Integer"));

        listeColonnes.addElement(
            new Colonne("DATE", new Integer(10), "java.util.Date"));

        listeColonnes.addElement(
            new Colonne("MONTANT", new Integer(15), "java.math.BigDecimal"));

        listeColonnes.addElement(
            new Colonne("N_CONTACT", new Integer(5), "java.lang.Integer"));

        return listeColonnes;
    }
}
