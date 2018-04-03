package kernelServeur;

import kernelMsg.*;
import KernelClient.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

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
	private HashMap<String, MsgListener> clients;
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
        
        private String mdp; 

        
        public TchatRoomImpl(String nom, String mdp, HUBImpl hub, IdentificateurImpl id) throws RemoteException{
            this.name = nom; 
            this.mdp = mdp; 
            this.hub = hub;
            this.identificateur = id; 
        }
        
	/**
	 * Envoie un msg
	 * Peut renvoyer l'exception PseudoNotFoundException
	 * @param msg un msg
         * @throws java.rmi.RemoteException
	 */
        @Override
	public void sendMsg(AbstractMSG msg, int id){
		// TODO - implement TchatRoomImpl.sendMsg
		throw new UnsupportedOperationException();
	}

        
        public void connect(int id, MsgListener list, String mdp) throws PseudoNotFoundException, WrongPasswordException{
            
            if(!this.mdp.equals(mdp)){
                throw new WrongPasswordException(); 
            }
            String pseudo = this.identificateur.getPseudo(id); 
            addClient(pseudo, list);
            
        }
        
	/**
	 * Permet de se deconnecter de la room
	 * //Peut renvoyer l'exception PseudoNotFoundException
	 * @param id l'identifiant du client
         * @throws java.rmi.RemoteException
	 */
        @Override
	public void disconnect(int id){
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
            this.clients.put(pseudo, listener); 
	}

        /**
         * Le nom de la salle
         * @return 
         */
        @Override
        public String getName() {
            return this.name;
        }
        
        public boolean isPrivate() {
            return !this.mdp.equals(""); 
        }

        
}