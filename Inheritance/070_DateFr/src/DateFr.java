// ==========================================================================
// DateFr : classe de dates en notation francaise
// ==========================================================================

import java.util.*;
import java.text.*;

/**
 * Cette classe permet d'ajouter des méthodes à la classe GregorianCalendar.
 * <br>Initialiation (constructeur) et mise à jour (set)de la date de l'objet
 * à l'aide d'une variable de type String contenant une date.
 * <br>Visualisation (toString) de la date de l'objet en utilisant un format
 * initialisé par défaut à dd/MM/yyyy.
 * <br>Modification (setFormat) et visualisation (toFormat) du format de la
 * date.<br>Calcul du nombre de JOURS écoulés entre deux dates (difDates).
 * <br>Les formats utilisés sont décrits dans la classe Calendar.
 *
 * @author Michel GINESTE
 * @version 2016.09
 */

public class DateFr extends GregorianCalendar
{
    private SimpleDateFormat formatDateFr;

// --------------------------------------------------------------------------
// Constructeurs :
// --------------------------------------------------------------------------
// Ces constructeurs initialisent le format de dates par defaut a dd/MM/yyyy
// La date initiale transmise au second devra donc respecter ce format. A
// defaut, il y aura une exception ParseException.
// --------------------------------------------------------------------------

    /**
     * Construit un objet DateFr, initialisé avec la date et l'heure système.
     */
    public DateFr() {
        formatDateFr = new SimpleDateFormat("dd/MM/yyyy");
    }

    /**
     * Construit un objet DateFr, initialisé avec la date indiquée dans le
     * paramètre dateInitiale.<br>Le format de la chaîne doit être dd/MM/yyyy
     * (exemple : 01/03/2012).
     * @param dateInitiale date initiale.
     * @throws java.text.ParseException si la date est incorrecte.
     */
    public DateFr(String dateInitiale) throws ParseException
    {
        formatDateFr = new SimpleDateFormat("dd/MM/yyyy");
        setTime(formatDateFr.parse(dateInitiale));
    }

// --------------------------------------------------------------------------
// Modification de la date contenue dans l'objet courant.
// --------------------------------------------------------------------------
// Cette methode s'ajoute a toutes les methodes set() contenues dans la
// classe Calendar.
// --------------------------------------------------------------------------

    /**
     * Modifie la date de l'objet courant.
     * @param nouvelleDate nouvelle date.
     * @throws java.text.ParseException si la date est incorrecte.
     */
    public void set(String nouvelleDate) throws ParseException
    {
        setTime(formatDateFr.parse(nouvelleDate));
    }

// --------------------------------------------------------------------------
// Methodes liees au format :
// --------------------------------------------------------------------------
// setFormat : changement de format
// toFormat  : recuperation du format courant (pour affichage par exemple)
// --------------------------------------------------------------------------

    /**
     * Modifie le format d'affichage et de saisie de l'objet courant.
     * @param nouveauFormat Nouveau format.
     */
    public void setFormat(String nouveauFormat)
    {
        formatDateFr.applyPattern(nouveauFormat);
    }

    /**
     * Retourne le format d'affichage de l'objet courant.
     *
     * @return Chaîne de format de l'affichage courant.
     */
    public String toFormat()
    {
        return formatDateFr.toPattern();
    }

// --------------------------------------------------------------------------
// Methode de calcul.
// --------------------------------------------------------------------------
// Cette methode permet de calculer le nombre de jours entre deux dates.
// Elle s'ajoute aux methodes de GregorianCalendar (Par exemple : add())
// --------------------------------------------------------------------------

    /**
     * Retourne le nombre de jours entre les deux dates.
     *
     * @return Nombre de jours entre date1 et date2.
     * @param date1 date de debut de la periode.
     * @param date2 date de fin de la periode.
     */
    public static long difDates(DateFr date1, DateFr date2)
    {
        long difference;

        difference = date2.getTimeInMillis() - date1.getTimeInMillis();
        difference = difference / (1000 * 3600 * 24);

        return difference;
    }

// --------------------------------------------------------------------------
// Methode toString().
// --------------------------------------------------------------------------
// Cette methode permet d'afficher la date avec le format courant.
// --------------------------------------------------------------------------

    /**
     * Retourne une représentation de la date sous forme de chaine.
     *
     * @return Date au format courant.
     */
    @Override
    public String toString()
    {
        return formatDateFr.format(getTime());
    }
}
