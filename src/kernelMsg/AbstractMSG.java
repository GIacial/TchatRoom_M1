package kernelMsg;

/**
 * La classe abstraite des messages
 */
public abstract class AbstractMSG {

	/**
	 * le pseudo du destinataire
	 */
	private String destinataire;
	/**
	 * la taille max des msg en octect
	 */
	private long LIMITE_TAILLE;
	/**
	 * L'identificateur de l'auteur du msg (soit id donn� par le client , soit le pseudo mit par la tchatroom)
	 */
	private String auteur;
        
        /**
         * id de l'auteur
         */
        private int idAuteur;

	public String getDestinataire() {
		return this.destinataire;
	}

	public String getAuteur() {
		return this.auteur;
	}

	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}
        
        /**
         * lis et supprime id de l'auteur
         * @return 
         */
        public int getIdAuteur(){
            int t = this.idAuteur;
            this.idAuteur = 0 ;
            return t;
        }

	/**
	 * Mets le contenu du msg dans la bulle Graphique
	 * @param bulle la bulle graphique du msg
	 */
	public abstract void affiche(IC_BulleMsg bulle);

}