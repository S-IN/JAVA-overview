// ==========================================================================
// Classe ModeleTableVersement                            Application JTable4
// --------------------------------------------------------------------------
// Modele de table constitue a partir d'un vecteur de versements.
// ==========================================================================

import java.util.*;
import utilitairesMG.divers.*;
import utilitairesMG.graphique.table.*;
import metierMapping.*;
import java.math.*;

public class ModeleTableVersement extends ModeleTable
{

// --------------------------------------------------------------------------
// CONSTRUCTEUR
// --------------------------------------------------------------------------
// On transforme la liste d'objets 'Versement' en  lignes (Vector<Object>)
// pour alimenter le ModeleTable
// --------------------------------------------------------------------------
    public ModeleTableVersement(Vector<Versement> listeVersements,
                                Vector<Colonne> listeColonnes)
    {
        Vector<Vector<Object>> listeLignes = new Vector<Vector<Object>>();

        for (int i = 0; i < listeVersements.size(); i++)
        {
            Vector<Object> ligne = new Vector<Object>();
            Versement v = listeVersements.elementAt(i);

            ligne.addElement(v.getNumero());
            ligne.addElement(v.getDate());
            ligne.addElement(v.getMontant());
            ligne.addElement(v.getNumeroContact());

            listeLignes.addElement(ligne);
        }

        setListeLignes(listeLignes);
        setListeColonnes(listeColonnes);
    }

// --------------------------------------------------------------------------
// Recuperation de la liste des versements a partir de la liste des lignes
// du ModeleTable
// --------------------------------------------------------------------------
    public Vector<Versement> getListeVersements()
    {
        Vector<Versement> listeVersements = new Vector<Versement>();
        Vector<Vector<Object>> listeLignes = getListeLignes();

        for (int i = 0; i < listeLignes.size(); i++)
        {
            Vector<Object> ligne = listeLignes.elementAt(i);
            Versement v = new Versement();

            v.setNumero((Integer) ligne.elementAt(0));
            v.setDate((Date) ligne.elementAt(1));
            v.setMontant((BigDecimal) ligne.elementAt(2));
            v.setNumeroContact((Integer) ligne.elementAt(3));

            listeVersements.addElement(v);
        }

        return listeVersements;
    }

// --------------------------------------------------------------------------
// Recuperation de la liste des versements supprimes a partir de la liste
// des lignes supprimees du ModeleTable
// --------------------------------------------------------------------------
    public Vector<Versement> getListeVersementsSupprimes()
    {
        Vector<Versement> listeVersements = new Vector<Versement>();
        Vector<Vector<Object>> listeLignes = getListeLignesSupprimees();

        for (int i = 0; i < listeLignes.size(); i++)
        {
            Vector<Object> ligne = listeLignes.elementAt(i);
            Versement v = new Versement();

            v.setNumero((Integer) ligne.elementAt(0));
            v.setDate((Date) ligne.elementAt(1));
            v.setMontant((BigDecimal) ligne.elementAt(2));
            v.setNumeroContact((Integer) ligne.elementAt(3));

            listeVersements.addElement(v);
        }

        return listeVersements;
    }

// --------------------------------------------------------------------------
// Recuperation de la liste des versements marques.
// Le marqueur sert a reperer les contacts modifies ('M') ou inseres ('I').
// --------------------------------------------------------------------------
    public Vector<Versement> getListeVersements(Character marqueur)
    {
        Vector<Versement> listeVersements = new Vector<Versement>();
        Vector<Vector<Object>> listeLignes = getListeLignes();

        Vector<Character> marqueursLigne = getMarqueursLignes();

        for (int i = 0; i < listeLignes.size(); i++)
        {
            if (marqueursLigne.elementAt(i).compareTo(marqueur) == 0)
            {
                Vector<Object> ligne = listeLignes.elementAt(i);
                Versement v = new Versement();

                v.setNumero((Integer) ligne.elementAt(0));
                v.setDate((Date) ligne.elementAt(1));
                v.setMontant((BigDecimal) ligne.elementAt(2));
                v.setNumeroContact((Integer) ligne.elementAt(3));

                listeVersements.addElement(v);
            }
        }

        return listeVersements;
    }

// --------------------------------------------------------------------------
// Rendre editables toutes les cellules sauf la premiere colonne
// --------------------------------------------------------------------------
    public boolean isCellEditable(int lig, int col)
    {
        if ((col == 0) && (lig < getRowCount() - 1))
        {
            return false;
        }
        else
        {
            return true;
        }
    }

// --------------------------------------------------------------------------
// Creer une ligne de plus dans la JTable pour permettre les ajouts
// --------------------------------------------------------------------------
    public int getRowCount()
    {
        return super.getRowCount() + 1;
    }
}
