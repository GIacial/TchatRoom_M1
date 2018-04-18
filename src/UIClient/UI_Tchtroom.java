/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UIClient;

import KernelClient.Client;
import KernelClient.IC_Tchatroom;
import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import kernelMsg.AbstractMSG;
import kernelMsg.MSG_IMG;
import kernelMsg.MSG_Text;
import kernelMsg.MSG_Video;
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
    private ComboBox pseudoChoice;
    private static final int refreshPseudo = 10000;
    private boolean actif;
    private Button imgButton;
    private Button videoButton;

    public UI_Tchtroom(TchatRoom room , Client c,MainFrame m) throws RemoteException{
        super(room.getName());
        this.c = c;
        this.m = m;
        this.actif = true;
        this.room = room;
        this.msg = new VBox();
        BorderPane layout = new BorderPane();
        HBox bottom = new HBox();
        msgEditor = new TextField();
        Button sendButton = new Button("Envoyer");
        imgButton = new Button("img");
        videoButton = new Button("vidéo");
         pseudoChoice = new ComboBox();
         this.createUpdaterPseudoList();
        
        bottom.getChildren().addAll(videoButton,imgButton,msgEditor,sendButton,pseudoChoice);
        
        
        layout.setCenter(msg);
        layout.setBottom(bottom);
        this.setContent(layout);
        
        //fonctionnelle des button
        //envoyer img
        imgButton.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
               sendMsgImg();
            }
        });
        //envoyer video
         videoButton.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
               sendMsgVideo();
            }
        });
        // envoyer text
            //button
        sendButton.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                sendMsgText();
            }
        });
            //enrer key
        msgEditor.setOnKeyPressed(new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent event) {
               if(event.getCode() == KeyCode.ENTER){
                   sendMsgText();
               }
            }
        });
        //a la fermeture
        this.setOnCloseRequest(new EventHandler<Event>(){
            @Override
            public void handle(Event event) {
                try {
                    c.disconnectFromTchatRoom(room);
                    actif = false;
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
            
            MSG_Text msgObject = new MSG_Text(msgEditor.getText(),getDestinataire());
            msgEditor.setText("");
            c.sendMsg(room, msgObject);
        } catch (RemoteException|PseudoNotFoundException ex) {
            m.showException(ex);
        }
    }
    
    private String getDestinataire(){
        String choix = (String) pseudoChoice.getValue();
            if(choix == null){
                choix = "";
                //System.err.println("Pas encore le pseudo selectionné");
            }
        return choix;
    }
    
    private void sendMsgImg(){
        final FileChooser dialog = new FileChooser(); 
        dialog.getExtensionFilters().setAll(new FileChooser.ExtensionFilter("images", "*.jpeg", "*.png","*.gif","*.bmp")); 
        final File file = dialog.showOpenDialog(imgButton.getScene().getWindow()); 
        if (file != null) { 
            // Effectuer le traitement. 
            //System.out.println(file);
            
            try {
                MSG_IMG img = new MSG_IMG(file,getDestinataire());
                c.sendMsg(room, img);
            } catch (RemoteException | PseudoNotFoundException ex) {
               m.showException(ex);
            } catch (IOException ex) {
               m.showException(ex);
            }
        } 
    }
    
     private void sendMsgVideo(){
        final FileChooser dialog = new FileChooser(); 
        dialog.getExtensionFilters().setAll(new FileChooser.ExtensionFilter("images", "*.mp4"/*,"*.mp3"*/)); 
        final File file = dialog.showOpenDialog(imgButton.getScene().getWindow()); 
        if (file != null) { 
            // Effectuer le traitement. 
            //System.out.println(file);
            
            try {
                MSG_Video img = new MSG_Video(file,getDestinataire());
                c.sendMsg(room, img);
            } catch (RemoteException | PseudoNotFoundException ex) {
               m.showException(ex);
            } catch (IOException ex) {
               m.showException(ex);
            }
        } 
    }
    
    private void updatePseudoList(){
        Runnable treatment = new Runnable(){
            @Override
            public void run() {
                String choix = (String) pseudoChoice.getValue();
                pseudoChoice.getItems().clear();
                try {
                    pseudoChoice.getItems().add("");
                    pseudoChoice.getItems().addAll(room.getAllPseudo());
                    pseudoChoice.getSelectionModel().select(choix);
                } catch (RemoteException ex) {
                    m.showException(ex);
                }
                
            }
            
        };
        
        if(Platform.isFxApplicationThread()) treatment.run();
        else Platform.runLater(treatment);
    }
    
    private void createUpdaterPseudoList(){
         Thread t = new Thread(){
            @Override
            public void run() {
                boolean ok = true;
                while(ok && actif){
                    try {
                        updatePseudoList();
                        Thread.sleep(refreshPseudo);
                    } catch (InterruptedException ex) {
                       m.showException(ex);
                       ok = false;
                    }
                }
            }
            
        };
        t.start();
    }
    
}
