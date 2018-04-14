package kernelMsg;
// J'ai regardé : on peut envoyé sous forme de byte apparemment c'est ce qu'il se fait le plus

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * MSG d'image
 */
public class MSG_IMG extends AbstractMSG {

	/**
	 * L'image du msg
	 */
	private byte[] image;
        
        public MSG_IMG (File c,String destinataire) throws IOException{
            super(destinataire);
            image = Files.readAllBytes(c.toPath());
        }

	/**
	 * Mets le contenu du msg dans la bulle Graphique
	 * @param bulle la bulle graphique du msg
	 */
	public void affiche(IC_BulleMsg bulle) {
            HBox v = new HBox();
            Label auteur = new Label(this.getAuteur()+" : ");
            ImageView i = new ImageView(new Image(new ByteArrayInputStream(image)));
            if(!this.getDestinataire().equals("")){
                v.setBackground(new Background(new BackgroundFill(Color.VIOLET, CornerRadii.EMPTY, Insets.EMPTY)));
            }
            v.getChildren().addAll(auteur,i);
            bulle.addItem(v);
	}
        


}