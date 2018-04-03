package KernelClient;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import kernelMsg.AbstractMSG;
import kernelMsg.PseudoNonLibreException;
import kernelMsg.TchatRoomAlreadyExistException;
import kernelMsg.TchatRoomNotFoundException;
import kernelServeur.*;

/**
 * Un client
 */
public class Client {

	/**
	 * Identificateur r�cup�r� apr�s la connection
	 */
	private int identificateur;
	/**
	 * //stockage en collection
	 * Tous les TchatRooms dont je fais parti
	 */
	private HashMap<TchatRoom,MsgListenerImpl> mesTchats;
	/**
	 * le hub des room
	 */
	private HUB hub;
        
        
        public Client(String adrSrv){
            
        }
        
        
        public void connect(String pseudo) throws PseudoNonLibreException, RemoteException{
           /* 
            String id = this.hub.connexion(pseudo); 
            this.Identificateur = id; */
            if(pseudo.equalsIgnoreCase("")){
                    throw new PseudoNonLibreException();
                }
        }

	/**
	 * //retourne une collection de String(nom des serveur)
	 */
	public Collection<String> getAllTchatRoomName() {
		// TODO - implement Client.getAllTchatRoomName
		//throw new UnsupportedOperationException();
                Collection<String> r = new ArrayList<String>();
                r.add("public TestRoom 1");
                r.add("private TestRoom 2");
                return r;
	}

	/**
	 * permet de savoir si une room est priv�e
	 * //peut renvoyer l'exception TchatRoomNotFoundException
	 * @param nom nom de la room
	 */
	public boolean isPrivateTchatRoom(String nom) throws TchatRoomNotFoundException {
                if(Math.random()<0.25){
                    throw new TchatRoomNotFoundException();
                }
		return !nom.contains("public");
	}

	/**
	 * permet de cree une room
	 * peut envoyer l'exception TchatRoomAlreadyExistException
	 * @param nom nom de la salle
	 * @param mdp mot de passe si priv� (sinon null ou vide)
	 */
	public TchatRoom createChatRoom(String nom, String mdp) throws TchatRoomAlreadyExistException  {
		// TODO - implement Client.createChatRoom
		throw new TchatRoomAlreadyExistException();
	}

	/**
	 * se connect� a une chatroom
	 * @param nom nom de la room
	 * @param mdp mdp de la room
	 */
	public TchatRoom connectChatRoom(String nom, String mdp) throws TchatRoomNotFoundException {
		// TODO - implement Client.connectChatRoom
		throw new TchatRoomNotFoundException();
	}
        
        public void setUI(TchatRoom t , IC_Tchatroom i){
            if(this.mesTchats.containsKey(t)){
                this.mesTchats.get(t).setUI(i);
            }
        }
        
        public void sendMsg(TchatRoom t, AbstractMSG msg) throws RemoteException{
            if(this.mesTchats.containsKey(t)){
                t.sendMsg(msg, identificateur);
            }
        }

}