package kernelServeur;

import KernelClient.*;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;


/**
 * la description d'un HUB
 */
public interface HUB extends Remote{

	/**
	 * Permet de se connecter au HUB
	 * On lui donne un pseudo et lui nous donne une cl� qui nous identifie
	 * Peut renvoyer l'exception PseudoNonLibreException
	 * @param pseudo le pseudo
	 */
	abstract String connexion(String pseudo) throws RemoteException;

	/**
	 * Ce connecter � une tchatRoom
	 * Renvoi l'exception TchatRoomNotFoundException ou WrongPasswordException
	 * @param nom le nom de la tchatroom
	 * @param password le mot de passe de la room
	 * @param id identifiant du client
	 * @param listener le moyen de joindre le client
	 */
	abstract TchatRoom connectionChatRoom(String nom, String password, String id, MsgListener listener) throws RemoteException;

	/**
	 * Cr�� une chatRoom dans ce HUB
	 * Peut renvoyer l'exception TchatRoomAlreadyExistException
	 * @param nom le nom de la room
	 * @param mdp le mot de passe de la room (vide ou null => public)
	 * @param id l'identificateur du client
	 * @param listener le moyen de joindre le client
	 */
	abstract TchatRoom createChatRoom(String nom, String mdp, String id, MsgListener listener) throws RemoteException;

	/**
	 * //Renvoi une collection de nom de room donc collection de String
	 * Donne tous les noms des room
	 */
	abstract HashMap<String, String> getAllChatRoom() throws RemoteException;

	/**
	 * Dis si la room est priv�e
	 * Peut renvoyer une exception TchatRoomNotFoundException
	 * @param nom le nom de la chatroom a verifie
	 */
	abstract boolean isPrivate(String nom)throws RemoteException;

	/**
	 * permet au client de liberer son pseudo
	 * Peut lancer une exception PseudoNotFoundException
	 * @param id identificateur du client
	 */
	abstract void disconnect(String id)throws RemoteException;

}