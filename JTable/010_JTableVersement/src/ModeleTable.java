// ==========================================================================
// Classe ModeleTable                                     Application JTable6
// -------------------------------------------------------------------------- 
// Modele de table constitue a partir d'un vecteur de lignes et d'un vecteur
// de colonnes (objets "Colonne").
// ==========================================================================

import javax.swing.table.*;
import java.util.*;
import utilitairesMG.divers.*;

public class ModeleTable extends AbstractTableModel
{

    private Vector<Vector<Object>> listeLignes;
    private Vector<Colonne> listeColonnes;

// -------------------------------------------------------------------------- 
// Constructeurs
// -------------------------------------------------------------------------- 
    public ModeleTable()
    {
    }

    public ModeleTable(Vector<Vector<Object>> listeLignes,
                       Vector<Colonne> listeColonnes)
    {
        this.listeLignes = listeLignes;
        this.listeColonnes = listeColonnes;
    }

// -------------------------------------------------------------------------- 
// Initialisation de la liste des lignes
// -------------------------------------------------------------------------- 
    public void setListeLignes(Vector<Vector<Object>> listeLignes)
    {
        this.listeLignes = listeLignes;
    }

// -------------------------------------------------------------------------- 
// Initialisation de la liste des colonnes
// -------------------------------------------------------------------------- 
    public void setListeColonnes(Vector<Colonne> listeColonnes)
    {
        this.listeColonnes = listeColonnes;
    }

// -------------------------------------------------------------------------- 
// Getters pour les differentes listes
// -------------------------------------------------------------------------- 
    public Vector<Vector<Object>> getListeLignes()
    {
        return listeLignes;
    }

    public Vector<Colonne> getListeColonnes()
    {
        return listeColonnes;
    }

// -------------------------------------------------------------------------- 
// getRowCount : nombre de lignes de la JTable
// -------------------------------------------------------------------------- 
    public int getRowCount()
    {
        return listeLignes.size();
    }

// -------------------------------------------------------------------------- 
// getColumnCount : nombre de colonnes de la JTable
// -------------------------------------------------------------------------- 
    public int getColumnCount()
    {
        return listeColonnes.size();
    }

// -------------------------------------------------------------------------- 
// getValueAt : "Object" qui s'affiche en ligne lig et en colonne col
// -------------------------------------------------------------------------- 
    public Object getValueAt(int lig, int col)
    {
        Object valeur;
        Vector<Object> ligne;

        if (lig < listeLignes.size())
        {
            ligne = listeLignes.elementAt(lig);
            valeur = ligne.elementAt(col);
        }
        else
        {
            valeur = null;
        }

        return valeur;
    }

// --------------------------------------------------------------------------
// Recupere la valeur tapee et la met dans le vecteur
// --------------------------------------------------------------------------
    public void setValueAt(Object valeur, int lig, int col)
    {
        Vector<Object> ligne;

        if (lig < listeLignes.size())
        {
            ligne = listeLignes.elementAt(lig);
            ligne.setElementAt(valeur, col);
        }
        else
        {
            if (valeur != null)
            {
                ligne = new Vector<Object>();
                for (int iCol = 0; iCol < listeColonnes.size(); iCol++)
                {
                    ligne.addElement(null);
                }
                listeLignes.addElement(ligne);
                ligne.setElementAt(valeur, col);
            }
        }
    }

// -------------------------------------------------------------------------- 
// getColumnClass : retourne la classe des objets de la colonne col.
// -------------------------------------------------------------------------- 
// Ce renseignement permet a la JTable d'adapter l'affichage au type de
// donnees de la colonne. Ainsi, les booleens apparaissent en case a cocher, 
// les valeurs numeriques sont cadrees a droite...
// -------------------------------------------------------------------------- 
// Cette methode servira pour toutes les colonnes dont l'editeur par defaut
// n'a pas ete remplace (voir ModeleColonneTable.java)
// -------------------------------------------------------------------------- 
    public Class getColumnClass(int col)
    {
        Class classe = null;

        try
        {
            classe = Class.forName(listeColonnes.elementAt(col).getType());
        }
        catch (ClassNotFoundException e)
        {
        }

        return classe;
    }
}
