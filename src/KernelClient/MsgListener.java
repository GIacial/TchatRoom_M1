package KernelClient;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * //implement l'interface Remote
 */
public interface MsgListener extends Remote{

	/**
	 * Affiche le msg chez le client
	 * @param InterfaceClient le panel qui affiche les msg de la tchatroom
         * @throws java.rmi.RemoteException
	 */
	abstract void recvMsg() throws RemoteException;

}