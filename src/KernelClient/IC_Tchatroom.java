package KernelClient;

import kernelMsg.*;

/**
 * Represente l'interface graphique pour une tchatrom
 */
public interface IC_Tchatroom {

	/**
	 * ajoute un msg a l'élément graphics
	 * @param msg le msg
	 */
	abstract void addMsg(AbstractMSG msg);

}