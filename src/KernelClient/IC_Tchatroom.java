package KernelClient;

import kernelMsg.*;

/**
 * Represente l'interface graphique pour une tchatrom (interface client)
 */
public interface IC_Tchatroom {

	/**
	 * ajoute un msg a l'�l�ment graphics
	 * @param msg le msg
	 */
	abstract void addMsg(AbstractMSG msg);

}