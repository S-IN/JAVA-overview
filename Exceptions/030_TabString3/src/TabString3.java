// ========================================================================
// APPLICATION TabString3
// ========================================================================

import java.io.*;

public class TabString3
{
    public static void main(String argv[])
    {
        int i;
        String tab[] = new String[3];

        tab[0] = "Autruche";
        tab[1] = "Bruant";
        tab[2] = "Corbeau";

        for (i = 0; i <= 4; i++)
        {
            try
            {
                if (i == 4)
                {
                    throw new IOException();
                }
                System.out.println(tab[i]);
            }
            catch (ArrayIndexOutOfBoundsException e)
            {
                System.out.print("L'indice i = " + i + " deborde du tableau.\n");
            }

// ------------------------------------------------------------------------
// Le catch suivant traite une Exception. 
// IOException étant une classe dérivée de Exception, c'est ce bloc qui 
// sera exécuté en cas de IOException (creee artificiellement dans la 
// boucle for ci-dessus...
// ------------------------------------------------------------------------
            catch (Exception e)
            {
                System.out.println("Traitement d'une exception "
                    + e.getClass().getName());
            }
        }
    }
}