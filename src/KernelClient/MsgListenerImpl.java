package KernelClient;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import kernelMsg.AbstractMSG;

public class MsgListenerImpl extends UnicastRemoteObject implements MsgListener {

	/**
	 * l'interface de la chatrrom
	 */
	private IC_Tchatroom interfaceTchatroom;

        /**
         *
         */
        public MsgListenerImpl() throws RemoteException{
            
        }
	/**
	 * Affiche le msg chez le client
	 * @param InterfaceClient le panel qui affiche les msg de la tchatroom
	 */
        @Override
	public void recvMsg(AbstractMSG msg){
		this.interfaceTchatroom.addMsg(msg);
	}
        
        /**
         * associe UI au listener
         * @param InterfaceClient 
         */
        public void setUI(IC_Tchatroom interfaceClient){
            this.interfaceTchatroom = interfaceClient;
        }

}