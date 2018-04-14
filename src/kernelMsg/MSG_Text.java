package kernelMsg;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * Un msg de texte
 */
public class MSG_Text extends AbstractMSG {

	/**
	 * Le contenu du msg
	 */
	private String texte;
        
        public MSG_Text(String contenu,String destinataire){
            super(destinataire);
            this.texte = contenu+"";
        }
        
        public MSG_Text(String contenu){
            this(contenu,"");
        }

	/**
	 * Mets le contenu du msg dans la bulle Graphique
	 * @param bulle la bulle graphique du msg
	 */
	public void affiche(IC_BulleMsg bulle) {
            Label msg = new Label(this.getAuteur()+" : "+texte);
            if(this.getDestinataire().equals("")){
                msg.setTextFill(Paint.valueOf("#000000"));
            }else{
                msg.setTextFill(Paint.valueOf("#008891"));
                msg.setBackground(new Background(new BackgroundFill(Color.VIOLET, CornerRadii.EMPTY, Insets.EMPTY)));
            }
            bulle.addItem(msg);
	}

}