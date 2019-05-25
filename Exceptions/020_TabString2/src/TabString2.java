// ========================================================================
// APPLICATION TabString2
// ========================================================================

public class TabString2
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
                System.out.println(tab[i]);
            }
            catch (ArrayIndexOutOfBoundsException e)
            {
                System.out.print(
                    "L'indice i = " + i + " deborde du tableau.\n");
            }
        }
    }
}