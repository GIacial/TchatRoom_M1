/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UIClient;

import java.util.Collection;
import javafx.scene.layout.HBox;

/**
 *
 * @author Jeremy
 */
public class UI_TchatroomList extends HBox {
    
    public UI_TchatroomList(Collection<String> list){
        this.updateRoomList(list);
    }
    
    public void updateRoomList(Collection<String> list){
        this.getChildren().clear();
        for(String a : list){
            this.getChildren().add(new UI_RoomListItem(a));
        }
    }
}
