// ==========================================================================
// Classe ServeurObjets            
// --------------------------------------------------------------------------
// Thread principal du serveur.
// ==========================================================================

import java.io.IOException;
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
// socketServeur : reference d'une socket du serveur vers le client
// threadClient : thread de traitement du client
// --------------------------------------------------------------------------
        ServerSocket serveur;
        boolean actif;
        Socket socketServeur;
        Thread threadClient;

        try
        {
            serveur = new ServerSocket(8189);
            try
            {
                serveur.setSoTimeout(10);
                actif = true;
                Controleur.serveurDemarre();

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
                        socketServeur = serveur.accept();

                        threadClient = new ThreadClient(socketServeur, base);
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
                Controleur.serveurArrete();
            }
        }
        catch(IOException e)
        {
            Controleur.traiterTexte(e.getMessage());
        }
    }
}
