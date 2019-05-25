// ==========================================================================
// TestDate : tests sur les dates
// --------------------------------------------------------------------------
// On utilisera trois classes :
//
// Date :              creation d'un objet rudimentaire, contenant une valeur
//                     en millisecondes, qu'on peut affecter ou extraire.
//
// SimpleDateFormat :  classe derivee de la classe abstraite Dateformat, qui
//                     permet d'utiliser des formats de dates par defaut,
//                     dependant du contexte local (Objet Locale).
//
// GregorianCalendar : classe derivee de la classe abstraite Calendar, qui
//                     permet d'extraire des proprietes entieres d'une date
//                     stockee dans un objet de cette classe. Ces entiers
//                     representent l'annee (YEAR), le mois (MONTH)...
//                     Cette classe possede aussi des methodes utiles :
//                     getTime(), setTime(), add(), set(), get()...
// ==========================================================================

import java.util.*;
import java.text.*;

public class TestDate
{
    public static void main(String argv[]) throws ParseException
    {

// ==========================================================================
// Date :      permet de creer un objet qui represente un instant (exprime en
//             millisecondes). Par defaut, il est initialise a l'heure
//             courante (au moment du new). Lors du new, on peut lui passer
//             un entier long, qui represente le nombre de millisecondes
//             ecoule depuis le 1 janvier 1970 a 1 h du matin).
//             La methode getTime() retourne ce nombre.
// --------------------------------------------------------------------------
        Date maDate = new Date();
        System.out.println("maDate :           " + maDate);
        System.out.println("maDate.getTime() : " + maDate.getTime());

// ==========================================================================
// SimpleDateFormat : visualisation d'un format de date par defaut (MEDIUM)
// --------------------------------------------------------------------------
        SimpleDateFormat monFormatDate = new SimpleDateFormat();

        System.out.println("\nReference format          : " + monFormatDate);
        System.out.println("Pattern (DEFAULT, MEDIUM) : "
            + monFormatDate.toPattern());

// --------------------------------------------------------------------------
// Visualisation de la date maDate en utilisant ce format
// --------------------------------------------------------------------------
        System.out.println("maDate : "
            + monFormatDate.format(maDate));

// --------------------------------------------------------------------------
// Creation et utilisation de notre propre format
// --------------------------------------------------------------------------
        monFormatDate = new SimpleDateFormat("EEEE");

        System.out.println("\nReference format          : " + monFormatDate);
        System.out.println("Pattern (PERSO)           : "
            + monFormatDate.toPattern());
        System.out.println("maDate : "
            + monFormatDate.format(maDate));

        monFormatDate = new SimpleDateFormat("dd MMMM yyyy");

        System.out.println("\nReference format          : " + monFormatDate);
        System.out.println("Pattern (PERSO)           : "
            + monFormatDate.toPattern());
        System.out.println("maDate : "
            + monFormatDate.format(maDate));

// ==========================================================================
// GregorianCalendar
// --------------------------------------------------------------------------
        GregorianCalendar calendrier = new GregorianCalendar();

// --------------------------------------------------------------------------
// getTime() permet d'extraire un objet Date du GregorianCalendar
// --------------------------------------------------------------------------
        System.out.println("\ncalendrier.getTime(): " + calendrier.getTime());

// --------------------------------------------------------------------------
// Initialisation d'une date en utilisant le format par defaut
// Methode set (attention, les mois sont numerotes a partir de 0)
// setTime() initialise le calendrier a partir d'un objet Date
// --------------------------------------------------------------------------
        calendrier.set(1953, 2, 31);
        System.out.println("\ncalendrier.getTime(): " + calendrier.getTime());
        calendrier.setTime(new Date(-3600007));
        System.out.println("\ncalendrier.getTime(): " + calendrier.getTime());

// --------------------------------------------------------------------------
// Initialisation d'une date en utilisant un format personnel.
// La methode parse() de la classe DateFormat permet de convertir la chaine
// en date en utilisant le format contenu dans l'objet. Cette date sert
// ensuite a initialiser la variable de type Calendar
// --------------------------------------------------------------------------
        monFormatDate = new SimpleDateFormat("dd MMMM yyyy");
        try
        {
            maDate = monFormatDate.parse("1 février 2017");
        }
        catch (ParseException e)
        {
            System.out.println("Erreur de creation de la date");
        }
        calendrier.setTime(maDate);
        System.out.println("\nNouvelle date : "
            + monFormatDate.format(calendrier.getTime()));
        System.out.println("\ncalendrier.getTime(): " + calendrier.getTime());

// --------------------------------------------------------------------------
// Quelques calculs sur des objets Calendar...
// Ajouts
// --------------------------------------------------------------------------
        calendrier.add(Calendar.MONTH, -4);               // 4 mois avant
        calendrier.add(Calendar.DAY_OF_MONTH, 4);         // 4 jours apres
        System.out.println("Nouvelle date : "
            + monFormatDate.format(calendrier.getTime()));

// --------------------------------------------------------------------------
// Nombre de jours entre deux dates
// --------------------------------------------------------------------------
        GregorianCalendar calendrier2 = new GregorianCalendar();
        try
        {
            maDate = monFormatDate.parse("1 mars 2017");
        }
        catch (ParseException e)
        {
            System.out.println("Erreur de creation de la date");
        }
        calendrier2.setTime(maDate);

        long difference = (calendrier2.getTime()).getTime()
            - (calendrier.getTime()).getTime();
        difference = (difference) / (1000 * 3600 * 24);

        System.out.print("\nNombre de jours entre le "
            + monFormatDate.format(calendrier.getTime())
            + " et le "
            + monFormatDate.format(calendrier2.getTime())
            + " = " + difference + "\n");
    }
}
