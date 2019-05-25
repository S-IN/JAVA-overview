// ========================================================================
// APPLICATION TabString4 
// ========================================================================

import java.io.*;

public class TabString4
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
            catch (Exception e)
            {
                System.out.println("Traitement d'une exception "
                    + e.getClass().getName());
            }
        }
    }
}
