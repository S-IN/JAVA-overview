// ==========================================================================
// Classe FiltreFichiers                                 Application ArbreXml
// --------------------------------------------------------------------------
// Classe permettant de filtrer les fichiers a choisir.
// ==========================================================================

import java.io.*;

// ==========================================================================
// Classe FiltreFichiers
// ==========================================================================
public class FiltreFichiers extends javax.swing.filechooser.FileFilter
{
    public boolean accept(File f)
    {
        boolean correct = false;

        if (f.isDirectory())
        {
            correct = true;
        }
        if (f.getName().toLowerCase().endsWith(".xml"))
        {
            correct = true;
        }

        return correct;
    }

    public String getDescription()
    {
        return "Fichiers xml";
    }
}
