package kernelMsg;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;

/**
 * MSG video
 */
public class MSG_Video extends AbstractMSG {

	/**
	 * la video du msg
	 */
	private byte[] video;

	public MSG_Video (File c,String destinataire) throws IOException{
            super(destinataire);
            video = Files.readAllBytes(c.toPath());
        }

	/**
	 * Mets le contenu du msg dans la bulle Graphique
	 * @param bulle la bulle graphique du msg
	 */
	public void affiche(IC_BulleMsg bulle) {
            HBox v = new HBox();
            Label auteur = new Label(this.getAuteur()+" : ");
           // MediaView i = new MediaView(new ByteArrayInputStream(video));
            if(!this.getDestinataire().equals("")){
                v.setBackground(new Background(new BackgroundFill(Color.VIOLET, CornerRadii.EMPTY, Insets.EMPTY)));
            }
            v.getChildren().addAll(auteur/*,i*/);
            bulle.addItem(v);
	}
        
 
}