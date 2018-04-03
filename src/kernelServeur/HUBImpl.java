package kernelServeur;

import KernelClient.*;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import kernelMsg.PseudoNonLibreException;
import kernelMsg.PseudoNotFoundException;
import kernelMsg.TchatRoomAlreadyExistException;
import kernelMsg.TchatRoomNotFoundException;
import kernelMsg.WrongPasswordException;

public class HUBImpl extends UnicastRemoteObject implements HUB, Serializable{

    
   
	/**
	 * Collection (Nom,Tchatroom) qui contient toutes les rooms existante
	 */
	private HashMap<String, TchatRoomImpl> TchatRooms;
	/**
	 * l'identificateur des clients
	 */
        
	private IdentificateurImpl identificater;

        
        
        //Rajouté par défaut
        public HUBImpl() throws RemoteException{
            this.TchatRooms = new HashMap<>(); 
            this.identificater = new IdentificateurImpl(); 
        }
        
        
	/**
	 * Permet de se connecter au HUB
	 * On lui donne un pseudo et lui nous donne une cl� qui nous identifie
	 * Peut renvoyer l'exception PseudoNonLibre
	 * @param pseudo le pseudo
         * @return 
         * @throws kernelMsg.PseudoNonLibreException
	 */
        @Override
	public int connexion(String pseudo) throws PseudoNonLibreException{
                return this.identificater.connexion(pseudo); 
                
	}

	/**
	 * Ce connecter � une tchatRoom
	 * Renvoi l'exception TchatRoomNotFound ou WrongPassword
	 * @param nom le nom de la tchatroom
	 * @param password le mot de passe de la room
	 * @param id
	 * @param listener
	 */
        @Override
	public TchatRoom connectionChatRoom(String nom, String password, int id, MsgListener listener) throws TchatRoomNotFoundException, WrongPasswordException, PseudoNotFoundException{
            if(!this.TchatRooms.containsKey(nom)){ 
                    throw new TchatRoomNotFoundException();
            }
            
            this.TchatRooms.get(nom).connect(id, listener, password); 
            return TchatRooms.get(nom); 
	}

	/**
	 * Cr�� une chatRoom dans ce HUB
	 * Peut renvoyer l'exception TchatRoomAlreadyExist
	 * @param nom le nom de la room
	 * @param mdp le mot de passe de la room (vide ou null => public)
	 * @param id
	 * @param listener
         * @return 
         * @throws kernelMsg.TchatRoomAlreadyExistException
	 */
        @Override
	public TchatRoom createChatRoom(String nom, String mdp, int id, MsgListener listener) throws TchatRoomAlreadyExistException{
            if(this.TchatRooms.containsKey(nom)){
                throw new TchatRoomAlreadyExistException();
            }
            
            TchatRoomImpl tchatRoom = null; 
            try { 
                tchatRoom = new TchatRoomImpl(nom, mdp, this, this.identificater);
            } catch (RemoteException ex) {
                Logger.getLogger(HUBImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            this.TchatRooms.put(nom, tchatRoom); 
            return tchatRoom; 
            
	}

	/**
	 * //Renvoi une collection de nom de room donc collection de String
	 * Donne tous les noms des room
	 */
        @Override
	public Collection<String> getAllChatRoom() throws RemoteException{
                ArrayList<String> r = new ArrayList<>();    //cause keySet not Serializable
                for(String t:this.TchatRooms.keySet()){
                    r.add(t);
                }
		return r; 
	}

	/**
	 * Dis si la room est priv�e
	 * Peut renvoyer une exception TchatroomNotFound
	 * @param nom le nom de la chatroom a verifie
	 */
        @Override
	public boolean isPrivate(String nom) throws TchatRoomNotFoundException, RemoteException{
		if(!this.TchatRooms.containsKey(nom)){ 
                    throw new TchatRoomNotFoundException();
                }
                return this.TchatRooms.get(nom).isPrivate(); 
	}

	/**
	 * enleve la chatrrom
	 * Peut renvoyer (mais normalement pas) l'exception TchatRoomNotFoundException
	 * @param room la room
	 */
	public void removeRoom(TchatRoom room){
		// TODO - implement HUBImpl.removeRoom
		throw new UnsupportedOperationException();
	}

	/**
	 * permet au client de liberer son pseudo
	 * @param id identificateur du client
	 */
        @Override
	public void disconnect(int id) throws PseudoNotFoundException{
            
            this.identificater.disconnect(id);
          
	}

	/**
	 * Teste l'activit� des chatroom , si une room n'est pas assez active on envoie un object msg null pour verifier si le listener est joingnable
	 */
	private void testActiviteRoom(){
		// TODO - implement HUBImpl.testActiviteRoom
		throw new UnsupportedOperationException();
	}

}