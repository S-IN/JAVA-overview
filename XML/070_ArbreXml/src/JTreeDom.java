// ==========================================================================
// Classe JTreeDom                                       Application ArbreXml
// --------------------------------------------------------------------------
// Classe permettant de creer une hierarchie de "DefaultMutableTreeNode"
// pour alimenter un objet graphique de type JTree.
// ==========================================================================

import javax.swing.tree.*;
import org.w3c.dom.*;

public class JTreeDom
{

// ==========================================================================
// Propriete
// --------------------------------------------------------------------------
// racineJTreeDom : noeud qui sera utilise comme racine d'un objet JTree
// ==========================================================================
    private DefaultMutableTreeNode racineJTreeDom;

// ==========================================================================
// Constructeur
// ==========================================================================
    public JTreeDom(Document documentDom)
    {
        Node racineDom;

        racineDom = documentDom.getDocumentElement();
        racineJTreeDom = creeJTreeDom(racineDom);
    }

// ==========================================================================
// Methodes
// --------------------------------------------------------------------------
// Getter de la racine de l'arbre graphique (JTree). Le reste vient avec...
// ==========================================================================
    public DefaultMutableTreeNode getRacineJTreeDom()
    {
        return racineJTreeDom;
    }

// --------------------------------------------------------------------------
// Creation recursive de l'arbre graphique
// --------------------------------------------------------------------------
    public DefaultMutableTreeNode creeJTreeDom(Node noeudDom)
    {
        DefaultMutableTreeNode noeudJTree;
        DefaultMutableTreeNode noeudAjout;
        String affiche;

        NamedNodeMap attributsDom;
        Node noeudAttributDom;
        NodeList listeEnfantsDom;

// --------------------------------------------------------------------------
// Le noeud est un element, on affiche son nom precede de "<"
// --------------------------------------------------------------------------
        if (noeudDom instanceof Element)
        {
            affiche = "<";
            affiche += noeudDom.getNodeName();

// --------------------------------------------------------------------------
// On regarde si l'element a des attributs
// --------------------------------------------------------------------------
            attributsDom = noeudDom.getAttributes();
            for (int i = 0; i < attributsDom.getLength(); i++)
            {
                noeudAttributDom = attributsDom.item(i);

                affiche += " " + noeudAttributDom.getNodeName() + " = \""
                    + noeudAttributDom.getNodeValue() + "\"";
            }

            affiche += ">";
            noeudJTree = new DefaultMutableTreeNode(affiche);

            listeEnfantsDom = noeudDom.getChildNodes();

// --------------------------------------------------------------------------
// Ajouter les enfants        
// --------------------------------------------------------------------------
            for (int i = 0; i < listeEnfantsDom.getLength(); i++)
            {
                noeudAjout = creeJTreeDom(listeEnfantsDom.item(i));
                noeudJTree.add(noeudAjout);
            }
        }
        else

// --------------------------------------------------------------------------
// Le noeud est un CharacterData (Text, Comment, CDATASection)
// --------------------------------------------------------------------------
        {
            if (noeudDom instanceof CharacterData)
            {
                affiche = ((CharacterData) noeudDom).getData();
                noeudJTree = new DefaultMutableTreeNode(affiche);
            }
            else
            {
                affiche = noeudDom.getClass() + " : " + noeudDom;
                noeudJTree = new DefaultMutableTreeNode(affiche);
            }
        }

        return noeudJTree;
    }
}
