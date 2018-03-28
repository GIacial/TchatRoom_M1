/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UIClient;

import javafx.geometry.Pos;
import javafx.scene.control.Label;


/**
 *
 * @author Jeremy
 */
public class UI_RoomListItem extends Label {
    
    public UI_RoomListItem(String a){
        super(a);
        this.setAlignment(Pos.CENTER);
    }
}
