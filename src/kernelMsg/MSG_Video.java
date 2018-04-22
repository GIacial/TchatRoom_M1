package kernelMsg;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
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
        private String format;
        private String nom;

	public MSG_Video (File c,String destinataire) throws IOException{
            super(destinataire);
            video = Files.readAllBytes(c.toPath());
            System.err.println(c.toPath().getFileName().toString());
            String[] d = c.toPath().getFileName().toString().split("\\.");
            format = d[1];
            nom = d[0];
        }

	/**
	 * Mets le contenu du msg dans la bulle Graphique
	 * @param bulle la bulle graphique du msg
	 */
	public void affiche(IC_BulleMsg bulle) {
            HBox v = new HBox();
            Node i;
            Label auteur = new Label("Le media envoyé par"+this.getAuteur()+" : ");
            try {
                String nom = this.nom+"."+format;
                FileOutputStream f = new FileOutputStream(nom);
                f.write(video);
                f.close();
                File fvideo = new File(nom);
                MediaPlayer player = new MediaPlayer(new Media(fvideo.toURI().toString()));
                 i = new MediaView(player);
                 player.play();
            } catch (Exception ex) {
                i = new Label(""+ex);
            }
            
            if(!this.getDestinataire().equals("")){
                v.setBackground(new Background(new BackgroundFill(Color.VIOLET, CornerRadii.EMPTY, Insets.EMPTY)));
            }
            v.getChildren().addAll(auteur,i);
            bulle.addItem(v);
	}
        
 
}