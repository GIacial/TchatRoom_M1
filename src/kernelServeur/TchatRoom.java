package kernelServeur;

import kernelMsg.*;

/**
 * Une salle de discution
 */
public interface TchatRoom {

	/**
	 * Envoie un msg
	 * Peut renvoyer l'exception PseudoNotFoundException
	 * @param msg un msg
	 */
	abstract void sendMsg(AbstractMSG msg);

	/**
	 * Permet de se deconnecter de la room
	 * Peut renvoyer l'exception PseudoNotFoundException
	 * @param id l'identifiant du client
	 */
	abstract void disconnect(string id);

}