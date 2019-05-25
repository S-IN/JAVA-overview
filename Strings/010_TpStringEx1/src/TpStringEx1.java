public class TpStringEx1
{
    public static void main(String arg[])
    {
        String chaine;
        String chaineTravail;
        char c;           
        int i;            

        chaine = "J'utilise JAVA avec brio !";
        System.out.println("AU DEPART : " + chaine);

        chaineTravail = "";

        for (i = 0; i < chaine.length(); i++)
        {
            c = chaine.charAt(i);

            if (Character.isLetter(c))
            {
                if (Character.isLowerCase(c))
                {
                    chaineTravail += Character.toUpperCase(c);
                }
                else
                {
                    chaineTravail += Character.toLowerCase(c);
                }
            }
            else
            {
                chaineTravail += c;
            }
        }

        chaine = chaineTravail;
        System.out.println("A LA FIN  : " + chaine);
    }
}