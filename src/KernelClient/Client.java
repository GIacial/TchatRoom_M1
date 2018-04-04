package KernelClient;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.HashMap;
import kernelMsg.AbstractMSG;
import kernelMsg.PseudoNonLibreException;
import kernelMsg.PseudoNotFoundException;
import kernelMsg.TchatRoomAlreadyExistException;
import kernelMsg.TchatRoomNotFoundException;
import kernelMsg.WrongPasswordException;
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
        
        
        public Client(String adrSrv) throws NotBoundException, MalformedURLException, RemoteException{
            String url = "rmi://"+adrSrv+"/"+MainServeur.factory_service;
            this.hub = (HUB)Naming.lookup(url);
            this.mesTchats = new HashMap<>();
            this.identificateur = 0;
        }
        
        
        public void connect(String pseudo) throws PseudoNonLibreException, RemoteException{
          
            int id = this.hub.connexion(pseudo); 
            this.identificateur = id; 
        }

	/**
	 * //retourne une collection de String(nom des serveur)
	 */
	public Collection<String> getAllTchatRoomName() throws RemoteException {		
                return this.hub.getAllChatRoom();
	}

	/**
	 * permet de savoir si une room est priv�e
	 * //peut renvoyer l'exception TchatRoomNotFoundException
	 * @param nom nom de la room
	 */
	public boolean isPrivateTchatRoom(String nom) throws TchatRoomNotFoundException, RemoteException {              
		return this.hub.isPrivate(nom);
	}

	/**
	 * permet de cree une room
	 * peut envoyer l'exception TchatRoomAlreadyExistException
	 * @param nom nom de la salle
	 * @param mdp mot de passe si priv� (sinon null ou vide)
	 */
	public TchatRoom createChatRoom(String nom, String mdp) throws TchatRoomAlreadyExistException, RemoteException, WrongPasswordException, PseudoNotFoundException  {
                MsgListenerImpl listener = new MsgListenerImpl();
		TchatRoom t = this.hub.createChatRoom(nom, mdp, identificateur, listener);
                this.mesTchats.put(t, listener);
                return t;
	}

	/**
	 * se connect� a une chatroom
	 * @param nom nom de la room
	 * @param mdp mdp de la room
	 */
	public TchatRoom connectChatRoom(String nom, String mdp) throws TchatRoomNotFoundException, RemoteException, WrongPasswordException, PseudoNotFoundException {
             MsgListenerImpl listener = new MsgListenerImpl();
             TchatRoom t = this.hub.connectionChatRoom(nom, mdp, identificateur, listener);
             this.mesTchats.put(t, listener);
             return t;
	}
        
        public void setUI(TchatRoom t , IC_Tchatroom i){
            if(this.mesTchats.containsKey(t)){
                this.mesTchats.get(t).setUI(i);
            }
        }
        
        public void sendMsg(TchatRoom t, AbstractMSG msg) throws RemoteException, PseudoNotFoundException{
            if(this.mesTchats.containsKey(t)){
                t.sendMsg(msg, identificateur);
            }
        }
        
        public void disconnectFromTchatRoom(TchatRoom t) throws RemoteException, PseudoNotFoundException{
            if(this.mesTchats.containsKey(t)){
                t.disconnect(identificateur);
                this.mesTchats.remove(t);
            }
        }
        
        public void disconnect() throws RemoteException, PseudoNotFoundException{
            this.hub.disconnect(identificateur);
            this.identificateur = 0;                //0 la valeur de non connection
        }

}