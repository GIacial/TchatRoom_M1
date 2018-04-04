package kernelMsg;

import java.io.Serializable;

/**
 * La classe abstraite des messages
 */
public abstract class AbstractMSG implements Serializable {

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
        
        public AbstractMSG(String destinataire){
            this.destinataire = destinataire;
            this.auteur = "";
        }
        

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
	 * Mets le contenu du msg dans la bulle Graphique
	 * @param bulle la bulle graphique du msg
	 */
	public abstract void affiche(IC_BulleMsg bulle);

}