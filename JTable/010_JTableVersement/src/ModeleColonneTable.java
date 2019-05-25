// ==========================================================================
// Classe ModeleColonneTable                              Application JTable6
// ==========================================================================

import javax.swing.table.*;
import java.util.*;
import utilitairesMG.divers.*;

public class ModeleColonneTable extends DefaultTableColumnModel
{

// ==========================================================================
// PROPRIETES
// --------------------------------------------------------------------------
// tailleCaractere : la taille du caractere depend de la police utilisee pour
// l'affichage de la JTable. Elle permet de calculer la taille de chaque
// colonne (exprimee en points).
// --------------------------------------------------------------------------
// nombreCaracteresMax et nombreCaracteresMin : nombre de caracteres max et
// min pour l'affichage initial d'une colonne de la table.
// ==========================================================================
    private int tailleCaractere = 10;
    private int nombreCaracteresMin = 8;
    private int nombreCaracteresMax = 20;

    private Vector<Colonne> listeColonnes;

// ==========================================================================
// CONSTRUCTEURS
// ==========================================================================
    public ModeleColonneTable(Vector<Colonne> listeColonnes,
                              int tailleCaractere)
    {
        this.listeColonnes = listeColonnes;
        this.tailleCaractere = tailleCaractere;
        creerColonnes();
    }

    public ModeleColonneTable(Vector<Colonne> listeColonnes)
    {
        this.listeColonnes = listeColonnes;
        creerColonnes();
    }

// ==========================================================================
// METHODES
// ==========================================================================
// Creation des objets TableColumn, avec les options par defaut
// --------------------------------------------------------------------------
    public void creerColonnes()
    {

// --------------------------------------------------------------------------
// Declaration d'une colonne et de ses caracteristiques :
// nom, taille (en nombre de caracteres) et type.
// --------------------------------------------------------------------------
        TableColumn colonne;

        String nomColonne;
        int nombreCaracteresColonne;
        String typeColonne;

// --------------------------------------------------------------------------
// Nombre de caracteres pour l'affichage initial d'une colonne
// --------------------------------------------------------------------------
        int nombreCaracteresAff;

// --------------------------------------------------------------------------
// Nombre de colonnes a creer, et indice de chaque colonne
// --------------------------------------------------------------------------
        int nombreColonnes;
        int c;

// --------------------------------------------------------------------------
// Nombre de colonnes a creer
// --------------------------------------------------------------------------
        nombreColonnes = listeColonnes.size();

// --------------------------------------------------------------------------
// Boucle de creation des colonnes (TableColumn) du modele de colonnes
// --------------------------------------------------------------------------
        for (c = 0; c < nombreColonnes; c++)
        {

// --------------------------------------------------------------------------
// Creation de l'objet TableColumn
// --------------------------------------------------------------------------
            colonne = new TableColumn(c);

// --------------------------------------------------------------------------
// Lecture des caracteristiques de la colonne dans le vecteur listeColonnes
// --------------------------------------------------------------------------
            nomColonne = listeColonnes.elementAt(c).getNom();
            nombreCaracteresColonne = listeColonnes.elementAt(c).getLongueur();
            typeColonne = listeColonnes.elementAt(c).getType();

// --------------------------------------------------------------------------
// Texte de l'entete de la colonne
// --------------------------------------------------------------------------
            colonne.setHeaderValue(nomColonne);

// --------------------------------------------------------------------------
// Nombre de caracteres a afficher pour la colonne. Puis conversion en nombre
// de points. Enfin, modification de la taille preferee de la colonne.
// --------------------------------------------------------------------------
            nombreCaracteresAff = nombreCaracteresColonne;

            if (nombreCaracteresAff > nombreCaracteresMax)
            {
                nombreCaracteresAff = nombreCaracteresMax;
            }
            if (nombreCaracteresAff < nombreCaracteresMin)
            {
                nombreCaracteresAff = nombreCaracteresMin;
            }

            colonne.setPreferredWidth(nombreCaracteresAff * tailleCaractere);

// --------------------------------------------------------------------------
// La colonne est prete. Ajout au modele.
// --------------------------------------------------------------------------
            addColumn(colonne);
        }
    }

// --------------------------------------------------------------------------
// Modification des tailles par defaut des colonnes (en caracteres)
// --------------------------------------------------------------------------
    public void setNombreCaracteresMax(int nombreCaracteresMax)
    {
        this.nombreCaracteresMax = nombreCaracteresMax;
    }

    public void setNombreCaracteresMin(int nombreCaracteresMin)
    {
        this.nombreCaracteresMin = nombreCaracteresMin;
    }

// --------------------------------------------------------------------------
// Modification des caracteristiques par defaut de la colonne c
// --------------------------------------------------------------------------
    public void setTailleColonne(int c, int taille)
    {
        TableColumn colonne = getColumn(c);
        colonne.setPreferredWidth(taille * tailleCaractere);
    }
}
