package KernelClient;

import java.rmi.Remote;
import java.rmi.RemoteException;
import kernelMsg.AbstractMSG;

/**
 * //implement l'interface Remote
 */
public interface MsgListener extends Remote{

	/**
	 * Affiche le msg chez le client
         * @throws java.rmi.RemoteException
	 */
	abstract void recvMsg(AbstractMSG msg) throws RemoteException;

}