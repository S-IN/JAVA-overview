// ==========================================================================
// CLASSE Contact      
// ==========================================================================

public class Contact implements Comparable<Contact>
{

// ==========================================================================
// PROPRIETES
// ==========================================================================
    private Integer numero;            // 4 octets
    private String nom;                // 20 caracteres + \0 final (21)
    private String adresse;            // 50 caracteres + \0 final (51)
    private String codePostal;         // 5 caracteres + \0 final (6)
    private String ville;              // 20 caracteres + \0 final (21)
    private Integer codeSecteur;       // 4 octets

// ==========================================================================
// METHODES
// ==========================================================================
// --------------------------------------------------------------------------
// CONSTRUCTEUR
// --------------------------------------------------------------------------
    public Contact()
    {
    }

// --------------------------------------------------------------------------
// SETTERS
// --------------------------------------------------------------------------
    public void setNumero(Integer numero)
    {
        this.numero = numero;
    }

    public void setNom(String nom)
    {
        this.nom = nom;
    }

    public void setAdresse(String adresse)
    {
        this.adresse = adresse;
    }

    public void setCodePostal(String codePostal)
    {
        this.codePostal = codePostal;
    }

    public void setVille(String ville)
    {
        this.ville = ville;
    }

    public void setCodeSecteur(Integer codeSecteur)
    {
        this.codeSecteur = codeSecteur;
    }

// --------------------------------------------------------------------------
// GETTERS
// --------------------------------------------------------------------------
    public Integer getNumero()
    {
        return numero;
    }

    public String getNom()
    {
        return nom;
    }

    public String getAdresse()
    {
        return adresse;
    }

    public String getCodePostal()
    {
        return codePostal;
    }

    public String getVille()
    {
        return ville;
    }

    public Integer getCodeSecteur()
    {
        return codeSecteur;
    }

// --------------------------------------------------------------------------
// COMPARAISON DE DEUX OBJETS Contact
// --------------------------------------------------------------------------
    public int compareTo(Contact c)
    {
        return getNumero().compareTo(c.getNumero());
    }

// --------------------------------------------------------------------------
// AFFICHAGE DU Contact
// --------------------------------------------------------------------------
    public String toString()
    {
        String retour;

        retour = numero + ";"
            + nom + ";"
            + adresse + ";"
            + codePostal + ";"
            + ville + ";"
            + codeSecteur;

        return retour;
    }
}
