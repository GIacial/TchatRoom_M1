package kernelServeur;

import java.rmi.RemoteException;
import java.rmi.Remote;
import kernelMsg.*;

/**
 * Une salle de discution
 */
public interface TchatRoom extends Remote{

	/**
	 * Envoie un msg
	 * Peut renvoyer l'exception PseudoNotFoundException
	 * @param msg un msg
	 */
	abstract void sendMsg(AbstractMSG msg) throws RemoteException;

	/**
	 * Permet de se deconnecter de la room
	 * Peut renvoyer l'exception PseudoNotFoundException
	 * @param id l'identifiant du client
	 */
	abstract void disconnect(String id) throws RemoteException;
        
        /**
         * Donne le nom de la salle
         * @return
         * @throws RemoteException 
         */
        abstract public String getName()throws RemoteException;

}