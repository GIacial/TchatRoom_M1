/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UIClient;

import KernelClient.Client;
import KernelClient.IC_Tchatroom;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import kernelMsg.TchatRoomNotFoundException;
import kernelServeur.TchatRoom;

/**
 *
 * @author Jeremy
 */
public class Tab_HUB extends Tab {
    
    private UI_TchatroomList list;
    private Client c;
    private TextField mdp;
    private Label mdpLabel;
    private HBox mdpBox;
    private MainFrame m;
    private UI_RoomCreator roomCreator;
    
    public Tab_HUB (Client c,MainFrame m,Stage primaryStage,Tab_Tchat tchats){
        super("HUB");
        this.c = c;
        this.m = m;
        this.roomCreator = new UI_RoomCreator(primaryStage,c,m,tchats);
        BorderPane grid = new BorderPane();
        this.list = new UI_TchatroomList(c.getAllTchatRoomName(),this);
        this.mdp = new TextField();
        this.mdpLabel = new Label("Mot de passe");
        this.mdpLabel.setAlignment(Pos.CENTER);
        HBox bottom = new HBox();
        mdpBox = new HBox();
        this.mdpBox.setVisible(false);
        this.mdpLabel.prefHeightProperty().bind(mdpBox.heightProperty());
        this.mdp.prefWidthProperty().bind(grid.widthProperty().divide(3).subtract(this.mdpLabel.widthProperty()));
        Button createRoomButton = new Button("Créer une salle");
        createRoomButton.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                roomCreator.show();
            }
        });
        Button connectButton = new Button("Connection");
        connectButton.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                String nameRoom = list.getCurrentRoomName();
                if(nameRoom != null){
                    try {
                        TchatRoom t = c.connectChatRoom(nameRoom, mdp.getText());
                        if(t != null){
                            IC_Tchatroom i = tchats.addTchat(t);
                            c.setUI(t, i);
                        }
                    } catch (TchatRoomNotFoundException ex) {
                        m.showException(ex);
                    }
                }
            }
        });
        createRoomButton.prefWidthProperty().bind(grid.widthProperty().divide(3));
        connectButton.prefWidthProperty().bind(grid.widthProperty().divide(3));
        connectButton.prefHeightProperty().bind(mdpBox.heightProperty());
        createRoomButton.prefHeightProperty().bind(mdpBox.heightProperty());
        
        this.setContent(grid);
        grid.setCenter(list);
        grid.setBottom(bottom);
        mdpBox.getChildren().add(mdpLabel);
        mdpBox.getChildren().add(mdp);
        bottom.getChildren().addAll(mdpBox,createRoomButton,connectButton);
        
    }
    
    public void  showMdp(String nameRoom){
        try {
            if(c.isPrivateTchatRoom(nameRoom)){
                this.mdpBox.setVisible(true);
            }
            else{
                this.mdpBox.setVisible(false);
                this.mdp.clear();
                
            }
        } catch (TchatRoomNotFoundException ex) {
           this.showException(ex);
        }
    }
    
    public void showException(Exception e){
        m.showException(e);
    }
}
