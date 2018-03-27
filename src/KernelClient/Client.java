package KernelClient;

import java.rmi.RemoteException;
import kernelMsg.PseudoNonLibreException;
import kernelServeur.*;

/**
 * Un client
 */
public class Client {

	/**
	 * Identificateur r�cup�r� apr�s la connection
	 */
	private String Identificateur;
	/**
	 * //stockage en collection
	 * Tous les TchatRooms dont je fais parti
	 */
	private int mesTchats;
	/**
	 * le hub des room
	 */
	private HUB hub;
        
        
        public Client(String adrSrv){
            
        }
        
        
        public void connect(String pseudo) throws PseudoNonLibreException, RemoteException{
            
            String id = this.hub.connexion(pseudo); 
            this.Identificateur = id; 
        }

	/**
	 * //retourne une collection de String(nom des serveur)
	 */
	public void getAllTchatRoomName() {
		// TODO - implement Client.getAllTchatRoomName
		throw new UnsupportedOperationException();
	}

	/**
	 * permet de savoir si une room est priv�e
	 * //peut renvoyer l'exception TchatRoomNotFoundException
	 * @param nom nom de la room
	 */
	public boolean isPrivateTchatRoom(String nom) {
		// TODO - implement Client.isPrivateTchatRoom
		throw new UnsupportedOperationException();
	}

	/**
	 * permet de cree une room
	 * peut envoyer l'exception TchatRoomAlreadyExistException
	 * @param nom nom de la salle
	 * @param mdp mot de passe si priv� (sinon null ou vide)
	 */
	public TchatRoom createChatRoom(String nom, String mdp) {
		// TODO - implement Client.createChatRoom
		throw new UnsupportedOperationException();
	}

	/**
	 * se connect� a une chatroom
	 * @param nom nom de la room
	 * @param mdp mdp de la room
	 */
	public TchatRoom connectChatRoom(String nom, String mdp) {
		// TODO - implement Client.connectChatRoom
		throw new UnsupportedOperationException();
	}

}