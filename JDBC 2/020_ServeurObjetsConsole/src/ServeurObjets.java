// ==========================================================================
// Classe ServeurObjets            
// --------------------------------------------------------------------------
// Thread principal du serveur.
// ==========================================================================

import java.io.IOException;
import static java.lang.Thread.interrupted;
import java.net.*;
import utilitairesMG.jdbc.*;

public class ServeurObjets extends Thread
{

// --------------------------------------------------------------------------
// base     : base de donnees utilisee par le serveur
// --------------------------------------------------------------------------
    private BaseDeDonnees base;

// ==========================================================================
// Constructeur : demarrage et parametrage du serveur
// ==========================================================================
    public ServeurObjets(BaseDeDonnees base)
    {
        this.base = base;
    }

// ==========================================================================
// Traitement fait par le serveur
// ==========================================================================
    public void run()
    {

// --------------------------------------------------------------------------
// socketClient : reference d'une socket du serveur vers le client
// threadClient : thread de traitement du client
// --------------------------------------------------------------------------
        ServerSocket serveur;
        boolean actif;
        Socket socketClient;
        Thread threadClient;

        try
        {
            serveur = new ServerSocket(8189);
            serveur.setSoTimeout(10);
            actif = true;
            
            try
            {

// --------------------------------------------------------------------------
// Le serveur sera actif jusqu'a ce que le programme Principal envoie un
// ordre d'interruption (interrupt()). Dans ce cas, la methode interrupted()
// renvoie true. Le programme ne fonctionne que parce qu'on a limite le
// temps d'attente du accept() par la methode setSoTimeout(1000) dans le
// constructeur. Sinon, le programme attend indefiniment qu'un client se
// connecte...
// --------------------------------------------------------------------------
                while (actif)
                {
                    try
                    {
                        socketClient = serveur.accept();

                        threadClient = new ThreadClient(socketClient, base);
                        threadClient.start();
                    }
                    catch (SocketTimeoutException e)
                    {
                        if (interrupted())
                        {
                            actif = false;
                        }
                    }
                }
            }
            finally
            {
                serveur.close();
            }
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
