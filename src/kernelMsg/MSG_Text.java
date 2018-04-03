package kernelMsg;

import javafx.scene.control.Label;

/**
 * Un msg de texte
 */
public class MSG_Text extends AbstractMSG {

	/**
	 * Le contenu du msg
	 */
	private String texte;
        
        public MSG_Text(String contenu){
            super();
            this.texte = contenu+"";
        }

	/**
	 * Mets le contenu du msg dans la bulle Graphique
	 * @param bulle la bulle graphique du msg
	 */
	public void affiche(IC_BulleMsg bulle) {
		bulle.addItem(new Label(this.getAuteur()+" : "+texte));
	}

}