/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UIClient;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Jeremy
 */
public class UI_ExceptionDisplay extends Stage {
    
    
    private Label exceptionText;
    
    public UI_ExceptionDisplay (Stage primaryStage){
        this.initModality(Modality.APPLICATION_MODAL);
        this.initOwner(primaryStage);
        BorderPane dialogVbox = new BorderPane();
        exceptionText = new Label("This is a Dialog");
        exceptionText.setAlignment(Pos.CENTER);
        exceptionText.prefWidthProperty().bind(this.widthProperty());
        Button dialogCloseButton = new Button("Fermer");
        dialogCloseButton.setAlignment(Pos.CENTER);
        dialogCloseButton.prefWidthProperty().bind(dialogVbox.widthProperty());
        dialogCloseButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
           @Override
           public void handle(MouseEvent t) {
               UI_ExceptionDisplay.this.close();
           }
       });
        dialogVbox.setCenter(exceptionText);
        dialogVbox.setBottom(dialogCloseButton);
        Scene dialogScene = new Scene(dialogVbox, 300, 200);
        this.setScene(dialogScene);
    }
    
    public void showException(Exception e){
        exceptionText.setText(e+"");
        this.show();
    }
}
