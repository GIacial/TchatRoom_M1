package kernelServeur;

import kernelMsg.*;
import KernelClient.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * implementation de la tchatroom
 */
public class TchatRoomImpl extends UnicastRemoteObject implements TchatRoom {

	/**
	 * le hub qui gere cette room
	 */
	private HUBImpl hub;
	/**
	 * collection (pseudo,ListenerMsg)
	 */
	private int clients;
	/**
	 * la date de la derniere activit�
	 * //Permet au hub de v�rifie si des channels on des personne qui on crash
	 */
	private int tempsDerniereActivite;
	/**
	 * lien sur identificateur du hub
	 */
	private IdentificateurImpl identificateur;
        
        /**
         * Le nom de la salle
         */
        private String name;

        
        public TchatRoomImpl() throws RemoteException{
            
        }
        
	/**
	 * Envoie un msg
	 * Peut renvoyer l'exception PseudoNotFoundException
	 * @param msg un msg
         * @throws java.rmi.RemoteException
	 */
        @Override
	public void sendMsg(AbstractMSG msg){
		// TODO - implement TchatRoomImpl.sendMsg
		throw new UnsupportedOperationException();
	}

	/**
	 * Permet de se deconnecter de la room
	 * //Peut renvoyer l'exception PseudoNotFoundException
	 * @param id l'identifiant du client
         * @throws java.rmi.RemoteException
	 */
        @Override
	public void disconnect(String id){
		// TODO - implement TchatRoomImpl.disconnect
		throw new UnsupportedOperationException();
	}

	/**
	 * Ajoute un client � la chatroom
	 * @param pseudo le pseudo du client
	 * @param listener la moyen de joindre le client
         * @throws java.rmi.RemoteException
	 */
	public void addClient(String pseudo, MsgListener listener){
		// TODO - implement TchatRoomImpl.addClient
		throw new UnsupportedOperationException();
	}

        /**
         * Le nom de la salle
         * @return 
         */
        public String getName() {
            return name;
        }

        
}