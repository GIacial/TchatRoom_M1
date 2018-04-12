package kernelMsg;
// J'ai regardé : on peut envoyé sous forme de byte apparemment c'est ce qu'il se fait le plus
/**
 * MSG d'image
 */
public class MSG_IMG extends AbstractMSG {

	/**
	 * L'image du msg
	 */
	private int image;

	/**
	 * Mets le contenu du msg dans la bulle Graphique
	 * @param bulle la bulle graphique du msg
	 */
	public void affiche(IC_BulleMsg bulle) {
		// TODO - implement MSG_IMG.affche
		throw new UnsupportedOperationException();
	}
        
        public MSG_IMG(String destinataire){
            super(destinataire);
        }

}