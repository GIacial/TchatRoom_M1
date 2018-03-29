/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UIClient;

import KernelClient.Client;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import kernelMsg.PseudoNonLibreException;

/**
 *
 * @author Jeremy
 */
public class UI_PseudoDialog extends Stage {
    
    
    private Label exceptionText;
    private TextField pseudo;
    private Client c;
    private MainFrame m;
    
    public UI_PseudoDialog (Stage primaryStage,Client c,MainFrame m){
        this.initModality(Modality.APPLICATION_MODAL);
        this.initOwner(primaryStage);
        this.c = c;
        this.setOnCloseRequest(new EventHandler<WindowEvent>(){
            @Override
            public void handle(WindowEvent event) {
                event.consume();
            }
            
        });
        BorderPane dialogVbox = new BorderPane();
        exceptionText = new Label("Quel est ton pseudo ?");
        exceptionText.setAlignment(Pos.CENTER);
        exceptionText.prefWidthProperty().bind(this.widthProperty());
        pseudo = new TextField();
        Button dialogCloseButton = new Button("Reserver son Pseudo");
        dialogCloseButton.setAlignment(Pos.CENTER);
        dialogCloseButton.prefWidthProperty().bind(dialogVbox.widthProperty());
        dialogCloseButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
           @Override
           public void handle(MouseEvent t) {
               connection();
           }
       });
        dialogVbox.setTop(exceptionText);
        dialogVbox.setCenter(pseudo);
        dialogVbox.setBottom(dialogCloseButton);
        Scene dialogScene = new Scene(dialogVbox, 300, 200);
        this.setScene(dialogScene);
    }
    
    private void connection(){
        try {
            c.connect(pseudo.getText());
            this.close();
        } catch (PseudoNonLibreException ex) {
            this.exceptionText.setText("Ce pseudo est déjà pris");
        } catch (Exception ex) {
           m.showException(ex);
        }
    }
}
