// ========================================================================
// APPLICATION TabString5 
// ========================================================================

import java.io.*;

public class TabString5
{
    public static void main(String argv[]) throws IOException
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

// ------------------------------------------------------------------------
// Debordement du tableau (i > 2)
// ------------------------------------------------------------------------
            catch (ArrayIndexOutOfBoundsException e)
            {
                System.out.print("L'indice i = " + i + " deborde du tableau.\n");
            }
            catch (IOException e)
            {
                System.out.print("IOException provoquée par le programmeur.\n");
            }

// ------------------------------------------------------------------------
// S'il existe, le bloc finally est toujours exécuté
// ------------------------------------------------------------------------
            finally
            {
                System.out.println("Pour le moment i = " + i);
                System.out.println("-----------------------------");
            }
        }
    }
}
