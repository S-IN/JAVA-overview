// ==========================================================================
// CLASSE Versement
// ==========================================================================

import java.util.*;
import utilitairesMG.divers.*;

public class Versement implements Comparable<Versement>
{
    private Integer numero;
    private Date date;
    private Double montant;
    private Integer numeroContact;

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

    public void setMontant(Double montant)
    {
        this.montant = montant;
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

    public Double getMontant()
    {
        return montant;
    }

    public Integer getNumeroContact()
    {
        return numeroContact;
    }

// --------------------------------------------------------------------------
// CompareTo
// --------------------------------------------------------------------------
    public int compareTo(Versement v)
    {
        return date.compareTo(v.date);
    }

// --------------------------------------------------------------------------
// AFFICHAGE DU VERSEMENT
// --------------------------------------------------------------------------
    public String toString()
    {
        String retour;
        DateFr dateFr;
        String chaineMontant;
        
        if (date != null)
        {
            dateFr = new DateFr();
            dateFr.setTime(date);
        }
        else
        {
            dateFr = null;
        }

        if (montant != null)
        {
            chaineMontant = Conversion.doubleChaineStandard(montant, 2, 1);
        }
        else
        {
            chaineMontant = null;
        }

        retour = "Numero              : " + numero + "\n";
        retour += "Date                : " + dateFr + "\n";
        retour += "Montant             : " + chaineMontant + "\n";
        retour += "Numero Contact      : " + numeroContact + "\n";

        return retour;
    }
}
