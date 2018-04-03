/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UIClient;

import KernelClient.Client;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Jeremy
 */
public class UI_TchatroomList extends ScrollPane {
    
    /**
     * La salle selectionné
     */
    private UI_RoomListItem current;
    private final int refreshRoom = 1000;
    
    private VBox list;
    private Tab_HUB master;
    
    public UI_TchatroomList(Collection<String> listNom,Tab_HUB m,Client c){
        
        this.list = new VBox();
        this.setContent(this.list);
        current = null;
        master = m;
        
        this.updateRoomList(listNom);
        
        this.setFitToHeight(true);
        this.setFitToWidth(true);
    }
    
    public void updateRoomList(Collection<String> listNom){
        this.list.getChildren().clear();
        for(String a : listNom){
            this.list.getChildren().add(new UI_RoomListItem(a,this));
        }
    }
    
    /**
     * Permet de select la salle c
     * @param c 
     */
    public void setCurrent(UI_RoomListItem c){
        if(current != null){
            current.setSelectedMode(false);
        }
        current = c;
         if(current != null){
            current.setSelectedMode(true);
            master.showMdp(c.getRoomName());
        }
    }
    
    public String getCurrentRoomName(){
        if(current != null){
            return current.getRoomName();
        }
        return null;
    }
    
    
   
}
