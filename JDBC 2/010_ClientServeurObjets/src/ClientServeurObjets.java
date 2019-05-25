// ==========================================================================
// Classe ClientServeurObjets   
// --------------------------------------------------------------------------
// Client du serveur ServeurObjets (mode console ou pas)
// --------------------------------------------------------------------------
// Ce programme ouvre la connexion, envoie une requete SQL, lit la reponse
// du serveur, et affiche les deux objets de la reponse.
// ==========================================================================

import java.net.*;
import java.io.*;
import utilitairesMG.divers.*;

public class ClientServeurObjets
{
    public static void main(String argv[])
    {

// --------------------------------------------------------------------------
// socketClient  : reference d'une socket vers le serveur
// sortie        : flux vers le serveur
// entree        : flux en provenance du serveur
// --------------------------------------------------------------------------
        Socket socketClient;
        ObjectInputStream entree;
        ObjectOutputStream sortie;

        String requete;
        Object reponse;

        try
        {
            System.out.print("\nRequete SQL : ");
            requete = Clavier.readString();

// --------------------------------------------------------------------------
// L'instanciation de l'objet Socket ouvre la connexion avec le serveur. Si
// le serveur est indisponible, il y a lancement d'une ConnectException.
// --------------------------------------------------------------------------
            socketClient = new Socket("94010-6101877", 8189);
            try
            {
                sortie = new ObjectOutputStream(socketClient.getOutputStream());
                entree = new ObjectInputStream(socketClient.getInputStream());
                sortie.writeObject(requete);

                reponse = entree.readObject();
                System.out.println(reponse);
                reponse = entree.readObject();
                System.out.println(reponse);
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
        catch (ClassNotFoundException | IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
