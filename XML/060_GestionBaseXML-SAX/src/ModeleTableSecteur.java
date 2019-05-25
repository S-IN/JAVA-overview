// ==========================================================================
// Classe ModeleTableSecteur                              Application JTable4
// --------------------------------------------------------------------------
// Modele de table constitue a partir d'un vecteur de secteurs.
// ==========================================================================

import java.util.*;
import utilitairesMG.divers.*;
import utilitairesMG.graphique.table.*;
import metierMapping.*;

public class ModeleTableSecteur extends ModeleTable
{

// --------------------------------------------------------------------------
// CONSTRUCTEUR
// --------------------------------------------------------------------------
// On transforme la liste d'objets 'Secteur' en  lignes (Vector<Object>) pour
// alimenter le ModeleTable
// --------------------------------------------------------------------------
   public ModeleTableSecteur(Vector<Secteur> listeSecteurs,
                             Vector<Colonne> listeColonnes)
   {
      Vector<Vector<Object>>listeLignes = new Vector<Vector<Object>>();

      for (int i = 0; i < listeSecteurs.size(); i++)
      {
         Vector<Object> ligne = new Vector<Object>();
         Secteur s = listeSecteurs.elementAt(i);

         ligne.addElement(s.getCode());
         ligne.addElement(s.getLibelle());

         listeLignes.addElement(ligne);
      }

      setListeLignes(listeLignes);
      setListeColonnes(listeColonnes);
   }

// --------------------------------------------------------------------------
// Recuperation de la liste des secteurs a partir de la liste des lignes du
// ModeleTable
// --------------------------------------------------------------------------
   public Vector<Secteur> getListeSecteurs()
   {
      Vector<Secteur> listeSecteurs = new Vector<Secteur>();
      Vector<Vector<Object>>listeLignes = getListeLignes();

      for (int i = 0; i < listeLignes.size(); i++)
      {
         Vector<Object> ligne = listeLignes.elementAt(i);
         Secteur s = new Secteur();

         s.setCode((Integer)ligne.elementAt(0));
         s.setLibelle((String)ligne.elementAt(1));

         listeSecteurs.addElement(s);
      }

      return listeSecteurs;
   }

// --------------------------------------------------------------------------
// Recuperation de la liste des secteurs supprimes a partir de la liste des
// lignes supprimees du ModeleTable
// --------------------------------------------------------------------------
   public Vector<Secteur> getListeSecteursSupprimes()
   {
      Vector<Secteur> listeSecteurs = new Vector<Secteur>();
      Vector<Vector<Object>>listeLignes = getListeLignesSupprimees();

      for (int i = 0; i < listeLignes.size(); i++)
      {
         Vector<Object> ligne = listeLignes.elementAt(i);
         Secteur s = new Secteur();

         s.setCode((Integer)ligne.elementAt(0));
         s.setLibelle((String)ligne.elementAt(1));

         listeSecteurs.addElement(s);
      }

      return listeSecteurs;
   }

// --------------------------------------------------------------------------
// Recuperation de la liste des secteurs marques.
// Le marqueur sert a reperer les contacts modifies ('M') ou inseres ('I').
// --------------------------------------------------------------------------
   public Vector<Secteur> getListeSecteurs(Character marqueur)
   {
      Vector<Secteur> listeSecteurs = new Vector<Secteur>();
      Vector<Vector<Object>>listeLignes = getListeLignes();

      Vector<Character> marqueursLigne = getMarqueursLignes();

      for (int i = 0; i < listeLignes.size(); i++)
      {
         if (marqueursLigne.elementAt(i).compareTo(marqueur) == 0)
         {
            Vector<Object> ligne = listeLignes.elementAt(i);
            Secteur s = new Secteur();

            s.setCode((Integer)ligne.elementAt(0));
            s.setLibelle((String)ligne.elementAt(1));

            listeSecteurs.addElement(s);
         }
      }

      return listeSecteurs;
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

 