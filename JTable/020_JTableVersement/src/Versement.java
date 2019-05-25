import java.util.*;
import utilitairesMG.divers.*;
import java.math.*;

public class Versement
{
    private Integer numero;                  
    private Date date;
    private BigDecimal montant;
    private Integer numeroContact;          

// --------------------------------------------------------------------------
// CONSTRUCTEUR
// --------------------------------------------------------------------------
    public Versement()
    {
    }

// --------------------------------------------------------------------------
// SETTERS
// --------------------------------------------------------------------------
    public void setNumero(Integer numero)
    {
        this.numero = numero;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    public void setMontant(BigDecimal montant)
    {
        this.montant = montant;
    }

// --------------------------------------------------------------------------
// Il n'y a pas conversion automatique de "double" en "BigDecimal"...
// --------------------------------------------------------------------------
    public void setMontant(double montant)
    {
        this.montant = new BigDecimal(montant);
    }

    public void setNumeroContact(Integer numeroContact)
    {
        this.numeroContact = numeroContact;
    }

// --------------------------------------------------------------------------
// GETTERS
// --------------------------------------------------------------------------
    public Integer getNumero()
    {
        return numero;
    }

    public Date getDate()
    {
        return date;
    }

    public BigDecimal getMontant()
    {
        return montant;
    }

    public Integer getNumeroContact()
    {
        return numeroContact;
    }

// --------------------------------------------------------------------------
// AFFICHAGE DU VERSEMENT (POUR MISE AU POINT)
// --------------------------------------------------------------------------
    public String toString()
    {
        String retour;

        DateFr dateFr = null;
        if (date != null)
        {
            dateFr = new DateFr();
            dateFr.setTime(date);
        }

        String chaineMontant = null;
        if (montant != null)
        {
            chaineMontant
                = Conversion.doubleChaine(montant.doubleValue(), 2, 1, '.');
        }

        retour = "Numero              : " + numero + "\n";
        retour += "Date                : " + dateFr + "\n";
        retour += "Montant             : " + chaineMontant + "\n";
        retour += "Numero Contact      : " + numeroContact + "\n";

        return retour;
    }
}
