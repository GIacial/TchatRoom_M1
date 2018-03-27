package kernelMsg;

/**
 * La classe abstraite des messages
 */
public abstract class AbstractMSG {

	/**
	 * le pseudo du destinataire
	 */
	private string destinataire;
	/**
	 * la taille max des msg en octect
	 */
	private long LIMITE_TAILLE;
	/**
	 * L'identificateur de l'auteur du msg (soit id donné par le client , soit le pseudo mit par la tchatroom)
	 */
	private string auteur;

	public string getDestinataire() {
		return this.destinataire;
	}

	public string getAuteur() {
		return this.auteur;
	}

	public void setAuteur(string auteur) {
		this.auteur = auteur;
	}

	/**
	 * Mets le contenu du msg dans la bulle Graphique
	 * @param bulle la bulle graphique du msg
	 */
	public abstract void affche(IC_BulleMsg bulle);

}