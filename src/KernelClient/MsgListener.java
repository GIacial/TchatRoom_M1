package KernelClient;

/**
 * //implement l'interface Remote
 */
public interface MsgListener {

	/**
	 * Affiche le msg chez le client
	 * @param InterfaceClient le panel qui affiche les msg de la tchatroom
	 */
	abstract void recvMsg(IC_Tchatroom InterfaceClient);

}