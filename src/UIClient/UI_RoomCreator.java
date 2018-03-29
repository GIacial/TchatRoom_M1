/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UIClient;

import KernelClient.Client;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import kernelMsg.PseudoNonLibreException;
import kernelMsg.TchatRoomAlreadyExistException;
import kernelServeur.TchatRoom;

/**
 *
 * @author Jeremy
 */
public class UI_RoomCreator extends Stage {
    
    private TextField nom;
    private TextField mdp;
    private Label exception;
    private Client c;
    private MainFrame m;
    private RadioButton privateVisibility;
    private Tab_Tchat tchats;
    
    public UI_RoomCreator (Stage primaryStage,Client c,MainFrame m,Tab_Tchat tchats){
        this.initModality(Modality.APPLICATION_MODAL);
        this.initOwner(primaryStage);
        this.c = c;
        this.tchats = tchats;
        BorderPane dialogVbox = new BorderPane();
        nom = new TextField();
        mdp = new TextField();
        exception = new Label("Créer votre salle");
        exception.setAlignment(Pos.CENTER);
        exception.prefWidthProperty().bind(this.widthProperty());
      //  exception.
        Button dialogCloseButton = new Button("Créer");
        dialogCloseButton.setAlignment(Pos.CENTER);
        dialogCloseButton.prefWidthProperty().bind(dialogVbox.widthProperty());
        dialogCloseButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
           @Override
           public void handle(MouseEvent t) {
               connection();
           }
       });
        VBox center = new VBox();
        //creation du choix
        HBox visibilityModeBox = new HBox();
        ToggleGroup visibilityChoice = new ToggleGroup();
        
        RadioButton publicVisibility = new RadioButton("publique");
        publicVisibility.setSelected(true);
        publicVisibility.setToggleGroup(visibilityChoice);
        
         privateVisibility = new RadioButton("privée");
        privateVisibility.setToggleGroup(visibilityChoice);
        
 
        visibilityModeBox.getChildren().addAll(publicVisibility,privateVisibility);
        
        //creation nom layout
        HBox nomBox = new HBox();
        nomBox.getChildren().addAll(new Label("Nom :"),nom);
        
        //creation de mdp layout
        HBox mdpBox = new HBox();
        mdpBox.getChildren().addAll(new Label("Mot de passe :"),mdp);
        mdpBox.visibleProperty().bind(privateVisibility.selectedProperty());
        
        center.getChildren().addAll(visibilityModeBox,nomBox,mdpBox);
        
        dialogVbox.setCenter(center);
        dialogVbox.setTop(exception);
        dialogVbox.setBottom(dialogCloseButton);
        Scene dialogScene = new Scene(dialogVbox, 300, 200);
        this.setScene(dialogScene);
    }
    
    private void connection(){
        
        try {
            if(!this.privateVisibility.isSelected()){
                mdp.clear();                            //nettoyage du mdp si inutile
            }
            TchatRoom t = c.createChatRoom(nom.getText(), mdp.getText());
            if(t != null){
                tchats.addTchat(t);
            }
             this.close();
        } catch (TchatRoomAlreadyExistException ex) {
            exception.setText("Le nom de cette salle est déjà pris par une autre");
        }
        catch(Exception ex){
            m.showException(ex);
        }
           

    }
}
