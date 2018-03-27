package KernelClient;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class MsgListenerImpl extends UnicastRemoteObject implements MsgListener {

	/**
	 * l'interface de la chatrrom
	 */
	private IC_Tchatroom interfaceTchatroom;

	/**
	 * Affiche le msg chez le client
	 * @param InterfaceClient le panel qui affiche les msg de la tchatroom
	 */
        @Override
	public void recvMsg(IC_Tchatroom InterfaceClient) throws RemoteException {
		// TODO - implement MsgListenerImpl.recvMsg
		throw new UnsupportedOperationException();
	}

}