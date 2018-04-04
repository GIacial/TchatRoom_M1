package kernelMsg;

/**
 * MSG video
 */
public class MSG_Video extends AbstractMSG {

	/**
	 * la video du msg
	 */
	private int video;

	/**
	 * Mets le contenu du msg dans la bulle Graphique
	 * @param bulle la bulle graphique du msg
	 */
	public void affiche(IC_BulleMsg bulle) {
		// TODO - implement MSG_Video.affche
		throw new UnsupportedOperationException();
	}

         public MSG_Video(String destinataire){
            super(destinataire);
        }
}