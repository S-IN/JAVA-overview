// ==========================================================================
// TestDateErreurs : tests sur les erreurs de saisie de dates
// ==========================================================================

import java.util.*;
import java.text.*;

public class TestDateErreurs
{
    public static void main(String argv[]) throws ParseException
    {
        Date maDate = new Date();
        SimpleDateFormat monFormatDate = new SimpleDateFormat();

        monFormatDate
            = (SimpleDateFormat) DateFormat.getDateInstance(DateFormat.SHORT);

        System.out.println("Pattern (SHORT)           : "
            + monFormatDate.toPattern());
        System.out.println("maDate : "
            + monFormatDate.format(maDate));

// --------------------------------------------------------------------------
// Ci dessous : changement de format.
// --------------------------------------------------------------------------
// A la saisie comme a l'affichage, la date devra comporter des separateurs
// point (.). Les blancs ne derangent pas (ils peuvent aussi servir de
// separateur (dd MM yy)). Le 2004 est accepte malgre le yy. Par contre,
// si l'annee est codee en yyyy, une annee sur deux chiffres (04) est
// acceptee mais la valeur est 0004...
// --------------------------------------------------------------------------
        monFormatDate.applyPattern("dd.MM.yy");
        maDate = monFormatDate.parse("1. 01. 2018");

        System.out.println("\nNouvelle date : "
            + monFormatDate.format(maDate));
    }
}
