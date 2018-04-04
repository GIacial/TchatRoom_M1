/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UIClient;

import KernelClient.Client;
import KernelClient.IC_Tchatroom;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import kernelMsg.AbstractMSG;
import kernelMsg.MSG_Text;
import kernelMsg.PseudoNotFoundException;
import kernelServeur.TchatRoom;

/**
 *
 * @author Jeremy
 */
public class UI_Tchtroom extends Tab implements IC_Tchatroom {
    
    private VBox msg;
    private TextField msgEditor;
    private Client c;
    private TchatRoom room;
    private MainFrame m;

    public UI_Tchtroom(TchatRoom room , Client c,MainFrame m) throws RemoteException{
        super(room.getName());
        this.c = c;
        this.m = m;
        this.room = room;
        this.msg = new VBox();
        BorderPane layout = new BorderPane();
        HBox bottom = new HBox();
        msgEditor = new TextField();
        Button sendButton = new Button("Envoyer");
        bottom.getChildren().addAll(msgEditor,sendButton);
        layout.setCenter(msg);
        layout.setBottom(bottom);
        this.setContent(layout);
        
        //fonctionnelle des button
        sendButton.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                sendMsgText();
            }
        });
        //a la fermeture
        this.setOnCloseRequest(new EventHandler<Event>(){
            @Override
            public void handle(Event event) {
                try {
                    c.disconnectFromTchatRoom(room);
                } catch (RemoteException |PseudoNotFoundException ex) {
                    m.showException(ex);
                }
            }
            
        });
    }
    
    @Override
    public void addMsg(AbstractMSG msgs) {
       
       
       Runnable treatment = new Runnable(){
            @Override
            public void run() {
                UI_BulleMsg b = new UI_BulleMsg();
                msgs.affiche(b);
                msg.getChildren().add(b);
            }
            
        };
        
        if(Platform.isFxApplicationThread()) treatment.run();
        else Platform.runLater(treatment);
       
    }
    
    private void sendMsgText() {    
        try {
            MSG_Text msgObject = new MSG_Text(msgEditor.getText());
            msgEditor.setText("");
            c.sendMsg(room, msgObject);
        } catch (RemoteException|PseudoNotFoundException ex) {
            m.showException(ex);
        }
    }
    
}
