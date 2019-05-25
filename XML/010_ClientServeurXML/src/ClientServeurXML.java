// ==========================================================================
// Classe ClientServeurXML     
// ==========================================================================

import java.net.*;
import java.io.*;
import utilitairesMG.divers.*;

public class ClientServeurXML
{
    public static void main(String argv[])
    {

// --------------------------------------------------------------------------
// socketClient  : reference d'une socket vers le serveur
// sortie        : flux vers le serveur
// entree        : flux en provenance du serveur
// --------------------------------------------------------------------------
        Socket socketClient;
        BufferedReader entree;
        PrintWriter sortie;

        String requete;
        String reponse;

        try
        {
            System.out.print("\nRequete SQL : ");
            requete = Clavier.readString();

            socketClient = new Socket("localhost", 8189);
            try
            {
                sortie = new PrintWriter(
                    new OutputStreamWriter(
                        socketClient.getOutputStream(), "utf-8"), true);

                entree = new BufferedReader(
                    new InputStreamReader(
                        socketClient.getInputStream(), "utf-8"));

                sortie.println(requete);

                reponse = entree.readLine();

                while (reponse != null)
                {
                    System.out.println(reponse);
                    reponse = entree.readLine();
                }
            }
            finally
            {
                socketClient.close();
            }
        }
        catch (ConnectException e)
        {
            System.out.println("Connexion impossible : serveur indisponible");
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
