package kernelServeur;

import kernelMsg.*;
import KernelClient.*;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * implementation de la tchatroom
 */
public class TchatRoomImpl extends UnicastRemoteObject implements TchatRoom, Serializable {

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
            this.clients = new HashMap<>();
        }
        
	/**
	 * Envoie un msg
	 * Peut renvoyer l'exception PseudoNotFoundException
	 * @param msg un msg
         * @throws java.rmi.RemoteException
	 */
        @Override
	public void sendMsg(AbstractMSG msg, int id) throws PseudoNotFoundException,RemoteException{
            String pseudo = this.identificateur.getPseudo(id);
            msg.setAuteur(pseudo);
            for(MsgListener l : this.clients.values()){
                l.recvMsg(msg);
                
            }
          
	}

        
        public void connect(int id, MsgListener list, String mdp) throws PseudoNotFoundException, WrongPasswordException{
            
            if(!this.mdp.equals(mdp)){
                throw new WrongPasswordException(); 
            }
            String pseudo = this.identificateur.getPseudo(id); 
            addClient(pseudo, list);
            welcomeMsg(pseudo," a rejoint la salle");
            
        }
        
        private void welcomeMsg(String pseudo, String message){
            MSG_Text msg = new MSG_Text(pseudo+ message);
            for(MsgListener l : this.clients.values()){
                try {
                    l.recvMsg(msg);
                } catch (RemoteException ex) {
                    Logger.getLogger(TchatRoomImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        }
        
	/**
	 * Permet de se deconnecter de la room
	 * //Peut renvoyer l'exception PseudoNotFoundException
	 * @param id l'identifiant du client
         * @throws java.rmi.RemoteException
	 */
        @Override
	public void disconnect(int id)throws PseudoNotFoundException,RemoteException{
            String pseudo = this.identificateur.getPseudo(id);
            this.clients.remove(pseudo);
            welcomeMsg(pseudo," a quitté la salle");
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