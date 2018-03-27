package kernelServeur;

import KernelClient.*;

/**
 * la description d'un HUB
 */
public interface HUB {

	/**
	 * Permet de se connecter au HUB
	 * On lui donne un pseudo et lui nous donne une cl� qui nous identifie
	 * Peut renvoyer l'exception PseudoNonLibreException
	 * @param pseudo le pseudo
	 */
	abstract string connexion(string pseudo);

	/**
	 * Ce connecter � une tchatRoom
	 * Renvoi l'exception TchatRoomNotFoundException ou WrongPasswordException
	 * @param nom le nom de la tchatroom
	 * @param password le mot de passe de la room
	 * @param id identifiant du client
	 * @param listener le moyen de joindre le client
	 */
	abstract TchatRoom connectionChatRoom(string nom, string password, string id, MsgListener listener);

	/**
	 * Cr�� une chatRoom dans ce HUB
	 * Peut renvoyer l'exception TchatRoomAlreadyExistException
	 * @param nom le nom de la room
	 * @param mdp le mot de passe de la room (vide ou null => public)
	 * @param id l'identificateur du client
	 * @param listener le moyen de joindre le client
	 */
	abstract TchatRoom createChatRoom(string nom, string mdp, string id, MsgListener listener);

	/**
	 * //Renvoi une collection de nom de room donc collection de string
	 * Donne tous les noms des room
	 */
	abstract void getAllChatRoom();

	/**
	 * Dis si la room est priv�e
	 * Peut renvoyer une exception TchatRoomNotFoundException
	 * @param nom le nom de la chatroom a verifie
	 */
	abstract boolean isPrivate(string nom);

	/**
	 * permet au client de liberer son pseudo
	 * Peut lancer une exception PseudoNotFoundException
	 * @param id identificateur du client
	 */
	abstract void disconnect(string id);

}