package kernelServeur;

import KernelClient.*;

public class HUBImpl implements HUB {

	/**
	 * Collection (Nom,Tchatroom) qui contient toutes les rooms existante
	 */
	private int TchatRooms;
	/**
	 * l'identificateur des clients
	 */
	private IdentificateurImpl identificater;

	/**
	 * Permet de se connecter au HUB
	 * On lui donne un pseudo et lui nous donne une clé qui nous identifie
	 * Peut renvoyer l'exception PseudoNonLibre
	 * @param pseudo le pseudo
	 */
	public string connexion(string pseudo) {
		// TODO - implement HUBImpl.connexion
		throw new UnsupportedOperationException();
	}

	/**
	 * Ce connecter à une tchatRoom
	 * Renvoi l'exception TchatRoomNotFound ou WrongPassword
	 * @param nom le nom de la tchatroom
	 * @param password le mot de passe de la room
	 * @param id
	 * @param listener
	 */
	public TchatRoom connectionChatRoom(string nom, string password, string id, MsgListener listener) {
		// TODO - implement HUBImpl.connectionChatRoom
		throw new UnsupportedOperationException();
	}

	/**
	 * Créé une chatRoom dans ce HUB
	 * Peut renvoyer l'exception TchatRoomAlreadyExist
	 * @param nom le nom de la room
	 * @param mdp le mot de passe de la room (vide ou null => public)
	 * @param id
	 * @param listener
	 */
	public TchatRoom createChatRoom(string nom, string mdp, string id, MsgListener listener) {
		// TODO - implement HUBImpl.createChatRoom
		throw new UnsupportedOperationException();
	}

	/**
	 * //Renvoi une collection de nom de room donc collection de string
	 * Donne tous les noms des room
	 */
	public void getAllChatRoom() {
		// TODO - implement HUBImpl.getAllChatRoom
		throw new UnsupportedOperationException();
	}

	/**
	 * Dis si la room est privée
	 * Peut renvoyer une exception TchatroomNotFound
	 * @param nom le nom de la chatroom a verifie
	 */
	public boolean isPrivate(string nom) {
		// TODO - implement HUBImpl.isPrivate
		throw new UnsupportedOperationException();
	}

	/**
	 * enleve la chatrrom
	 * Peut renvoyer (mais normalement pas) l'exception TchatRoomNotFoundException
	 * @param room la room
	 */
	public void removeRoom(TchatRoom room) {
		// TODO - implement HUBImpl.removeRoom
		throw new UnsupportedOperationException();
	}

	/**
	 * permet au client de liberer son pseudo
	 * @param id identificateur du client
	 */
	public void disconnect(string id) {
		// TODO - implement HUBImpl.disconnect
		throw new UnsupportedOperationException();
	}

	/**
	 * Teste l'activité des chatroom , si une room n'est pas assez active on envoie un object msg null pour verifier si le listener est joingnable
	 */
	private void testActiviteRoom() {
		// TODO - implement HUBImpl.testActiviteRoom
		throw new UnsupportedOperationException();
	}

}