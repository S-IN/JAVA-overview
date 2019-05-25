// ==========================================================================
// Classe Controleur                                     Application ArbreXml
// ==========================================================================

import utilitairesMG.graphique.*;
import java.io.*;
import org.w3c.dom.*;
import org.xml.sax.*;
import javax.xml.parsers.*;

public class Controleur
{

// ==========================================================================
// Proprietes du Controleur
// ==========================================================================
    private static DocumentBuilder parseurXml;
    private static Fenetre maFenetre;

// ==========================================================================
// Demarrage du controleur
// ==========================================================================
    public static void main(String args[]) throws ParserConfigurationException
    {
        DocumentBuilderFactory usineParseurXml;

// --------------------------------------------------------------------------
// Un objet DocumentBuilderFactory permet d'obtenir un objet DocumentBuilder,
// qui est un "parser" (analyseur syntaxique) permettant de transformer un 
// fichier xml en objet de type Document.
// --------------------------------------------------------------------------
        usineParseurXml = DocumentBuilderFactory.newInstance();
        usineParseurXml.setValidating(true);
        usineParseurXml.setIgnoringComments(true);
        usineParseurXml.setIgnoringElementContentWhitespace(true);

        parseurXml = usineParseurXml.newDocumentBuilder();
        parseurXml.setErrorHandler(new ErreurDtd());

// --------------------------------------------------------------------------
// Affichage de la fenetre de l'application
// --------------------------------------------------------------------------
        javax.swing.SwingUtilities.invokeLater
        (
            new Runnable()
            {
                public void run()
                {
                    LF.setLF();
                    maFenetre = new Fenetre("ArbreXml");
                }
            }
        );
    }

// ==========================================================================
// Lecture et conversion en Document du fichier XML.
// ==========================================================================
    public static void litXml(File fichierXml)
    {
        Document document;

        try
        {
            document = parseurXml.parse(fichierXml);
            maFenetre.afficheArbre(document);
        }
        catch (SAXException e)
        {
            maFenetre.afficheMessage(e.getMessage());
        }
        catch (IOException e)
        {
            maFenetre.afficheMessage(e.getMessage());
        }
    }

// ==========================================================================
// Arret de l'application
// ==========================================================================
    public static void arreter()
    {
        System.exit(0);
    }
}
