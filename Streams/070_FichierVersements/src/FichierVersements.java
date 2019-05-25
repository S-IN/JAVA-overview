// ==========================================================================
// Classe FichierVersements : gestion du fichier Contact
// --------------------------------------------------------------------------
// Description de ce fichier :
//
//    int  numero
//    char date[11]        jj/mm/aaaa
//    double montant       1000.12
//    int  numeroContact
//
// 28 octets dans un typedef de C (un octet de cadrage entre date et montant)
// ==========================================================================

import java.io.*;
import java.text.*;
import java.util.*;
import utilitairesMG.divers.*;

public class FichierVersements extends Fichier
{

// --------------------------------------------------------------------------
// Constructeurs
// --------------------------------------------------------------------------
    public FichierVersements(String nomFichier, String mode) throws IOException
    {
        super(nomFichier, mode, 27);
    }

    public FichierVersements(File fichier, String mode) throws IOException
    {
        super(fichier, mode, 27);
    }

// --------------------------------------------------------------------------
// Methodes d'ecriture (sequentielle et directe)
// --------------------------------------------------------------------------
    public void ecrire(Versement versement) throws IOException
    {
        writeInt(versement.getNumero());

        DateFr dateFr = new DateFr();
        dateFr.setTime(versement.getDate());
        writeString(dateFr.toString(), 11);

        writeDouble(versement.getMontant());

        writeInt(versement.getNumeroContact());
    }

    public void ecrire(Versement versement, int numEnreg) throws IOException
    {
        seekFichier(numEnreg);
        ecrire(versement);
    }

// --------------------------------------------------------------------------
// Methodes de lecture (sequentielle et directe)
// --------------------------------------------------------------------------
    public void lire(Versement versement) throws IOException
    {
        versement.setNumero(readInt());

        try
        {
            DateFr dateFr = new DateFr(readString(11));
            versement.setDate(dateFr.getTime());
        }
        catch (ParseException e)
        {
        }

        versement.setMontant(readDouble());

        versement.setNumeroContact(readInt());
    }

    public void lire(Versement versement, int numEnreg) throws IOException
    {
        seekFichier(numEnreg);
        lire(versement);
    }

// --------------------------------------------------------------------------
// Liste des produits
// --------------------------------------------------------------------------
    public Vector<Versement> lireListe()
    {
        Vector<Versement> listeVersements = new Vector<Versement>();
        Versement versement;

        try
        {
            seekFichier(0);

            while (true)
            {
                versement = new Versement();
                lire(versement);
                listeVersements.addElement(versement);
            }
        }
        catch (EOFException e)
        {
        }
        catch (IOException e)
        {
            System.out.println("Indice incorrect !");
        }

        return listeVersements;
    }
}
