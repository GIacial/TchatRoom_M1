package KernelClient;

import kernelServeur.*;

/**
 * Un client
 */
public class Client {

	/**
	 * Identificateur récupéré aprés la connection
	 */
	private string Identificateur;
	/**
	 * //stockage en collection
	 * Tous les TchatRooms dont je fais parti
	 */
	private int mesTchats;
	/**
	 * le hub des room
	 */
	private HUB hub;

	/**
	 * //retourne une collection de string(nom des serveur)
	 */
	public void getAllTchatRoomName() {
		// TODO - implement Client.getAllTchatRoomName
		throw new UnsupportedOperationException();
	}

	/**
	 * permet de savoir si une room est privée
	 * //peut renvoyer l'exception TchatRoomNotFoundException
	 * @param nom nom de la room
	 */
	public boolean isPrivateTchatRoom(string nom) {
		// TODO - implement Client.isPrivateTchatRoom
		throw new UnsupportedOperationException();
	}

	/**
	 * permet de cree une room
	 * peut envoyer l'exception TchatRoomAlreadyExistException
	 * @param nom nom de la salle
	 * @param mdp mot de passe si privé (sinon null ou vide)
	 */
	public TchatRoom createChatRoom(string nom, string mdp) {
		// TODO - implement Client.createChatRoom
		throw new UnsupportedOperationException();
	}

	/**
	 * se connecté a une chatroom
	 * @param nom nom de la room
	 * @param mdp mdp de la room
	 */
	public TchatRoom connectChatRoom(string nom, string mdp) {
		// TODO - implement Client.connectChatRoom
		throw new UnsupportedOperationException();
	}

}