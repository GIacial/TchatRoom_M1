package kernelServeur;

import kernelMsg.*;
import KernelClient.*;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
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
	private Date tempsDerniereActivite;
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
            GregorianCalendar calendar = new GregorianCalendar();
            Date time  = calendar.getTime();
            this.tempsDerniereActivite = time; 
        }
        
	/**
	 * Envoie un msg
	 * Peut renvoyer l'exception PseudoNotFoundException
	 * @param msg un msg
         * @param id destinataire
         * @throws java.rmi.RemoteException
	 */
        @Override
	public void sendMsg(AbstractMSG msg, int id) throws PseudoNotFoundException,RemoteException{
            String pseudo = this.identificateur.getPseudo(id);
            msg.setAuteur(pseudo);
            if(msg.getDestinataire().equals("")){
              for(MsgListener l : this.clients.values()){
                l.recvMsg(msg);
                
                }  
            }
            else{
                MsgListener l = this.clients.get(msg.getDestinataire());
                if(l != null){
                    l.recvMsg(msg);
                }
                l = this.clients.get(msg.getAuteur());
                if(l != null){
                    l.recvMsg(msg);//copie pour l'auteur
                }
                
            }
            GregorianCalendar calendar = new GregorianCalendar();
            Date time  = calendar.getTime();
            this.tempsDerniereActivite = time; 
           
	}

        
        public void connect(int id, MsgListener list, String mdp) throws PseudoNotFoundException, WrongPasswordException,AlreadyConnectException{
            String pseudo = this.identificateur.getPseudo(id); 
            if(this.clients.containsKey(pseudo)){
                throw new AlreadyConnectException();
            }
            if(!this.mdp.equals(mdp)){
                throw new WrongPasswordException(); 
            }
            
            addClient(pseudo, list);
            welcomeMsg(pseudo," a rejoint la salle");
            
        }
        
        private void welcomeMsg(String pseudo, String message){
            MSG_Text msg = new MSG_Text(pseudo+ message);
            msg.setAuteur(pseudo);
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
            synchronized(clients){
                this.clients.remove(pseudo);
            }
            welcomeMsg(pseudo," a quitté la salle");
            if(this.clients.isEmpty()){
                this.hub.removeRoom(this);
            }
	}

	/**
	 * Ajoute un client � la chatroom
	 * @param pseudo le pseudo du client
	 * @param listener la moyen de joindre le client
         * @throws java.rmi.RemoteException
	 */
	public void addClient(String pseudo, MsgListener listener){
            synchronized(clients){
            this.clients.put(pseudo, listener); 
            }
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

        @Override
        public Collection<String> getAllPseudo(){
            ArrayList<String> r = new ArrayList<>();
            for(String n : this.clients.keySet()){
                r.add(n);
            }
            return r;
        }
        
        public void sendCheck() throws PseudoNotFoundException,RemoteException{
            AbstractMSG msg = null;
            
            for(String pseudo : this.clients.keySet()){
                try{
                    
                    System.err.println("Deconnecté test :"+pseudo);
                    this.clients.get(pseudo).recvMsg(msg);
                }catch(Exception e){
                    System.err.println("Deconnecté trouver");
                    int id = this.identificateur.getId(pseudo); 
                    this.identificateur.disconnect(id);
                    this.clients.remove(pseudo); 
                    System.out.println("Déconnexion");
                    
                }
              
            } 
            
           
           
            GregorianCalendar calendar = new GregorianCalendar();
            Date time  = calendar.getTime();
            this.tempsDerniereActivite = time; 
            
             if(this.clients.isEmpty()){
                    System.err.println("Destruction de la room");
                     this.hub.removeRoom(this);
            }
             else{
                 System.err.println("il reste des gens de la room");
             }
           
	}
        
        public Date getDate(){
            return this.tempsDerniereActivite; 
        }
}