// ==========================================================================
// Classe FichierContactsBinaireC : gestion du fichier Contact
// --------------------------------------------------------------------------
// Description de ce fichier :
//
//    int  numero
//    char nom[21]
//    char adresse[51]
//    char codePostal[6]
//    char ville[21]
//    int  codeSecteur
// --------------------------------------------------------------------------
// Attention : entre ville et codeSecteur, il y a un octet de cadrage de
// l'entier codeSecteur sur un octet multiple de 4. 
// La taille de l'enregistrement est donc 108.  
// ==========================================================================

import java.io.*;
import utilitairesMG.divers.*;

public class FichierContactsBinaireC extends Fichier
{

// --------------------------------------------------------------------------
// Constructeurs
// --------------------------------------------------------------------------
    public FichierContactsBinaireC(String nomFichier, String mode) throws IOException
    {
        super(nomFichier, mode, 108);
    }

    public FichierContactsBinaireC(File fichier, String mode) throws IOException
    {
        super(fichier, mode, 108);
    }

    public void lire(Contact contact) throws IOException
    {
        contact.setNumero(readIntC());
        contact.setNom(readString(21));
        contact.setAdresse(readString(51));
        contact.setCodePostal(readString(6));
        contact.setVille(readString(21));
        readByte();
        contact.setCodeSecteur(readIntC());
    }
}
