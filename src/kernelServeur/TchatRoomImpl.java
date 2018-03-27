package kernelServeur;

import kernelMsg.*;
import KernelClient.*;

/**
 * implementation de la tchatroom
 */
public class TchatRoomImpl implements TchatRoom {

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
	 * Envoie un msg
	 * Peut renvoyer l'exception PseudoNotFoundException
	 * @param msg un msg
	 */
	public void sendMsg(AbstractMSG msg) {
		// TODO - implement TchatRoomImpl.sendMsg
		throw new UnsupportedOperationException();
	}

	/**
	 * Permet de se deconnecter de la room
	 * //Peut renvoyer l'exception PseudoNotFoundException
	 * @param id l'identifiant du client
	 */
	public void disconnect(string id) {
		// TODO - implement TchatRoomImpl.disconnect
		throw new UnsupportedOperationException();
	}

	/**
	 * Ajoute un client � la chatroom
	 * @param pseudo le pseudo du client
	 * @param listener la moyen de joindre le client
	 */
	public void addClient(string pseudo, MsgListener listener) {
		// TODO - implement TchatRoomImpl.addClient
		throw new UnsupportedOperationException();
	}

}