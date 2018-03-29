/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UIClient;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;


/**
 *
 * @author Jeremy
 */
public class UI_RoomListItem extends Label {
    
    private UI_TchatroomList master;
    
    public UI_RoomListItem(String a, UI_TchatroomList m){
        super(a);
        master = m;
        this.setAlignment(Pos.CENTER);
        this.prefWidthProperty().bind(m.widthProperty());
        
        setOnMouseClicked((MouseEvent t) -> {            
           master.setCurrent(this);
        });
    }
    
    public void setSelectedMode(boolean t){
        if(!t){
           this.backgroundProperty().set(Background.EMPTY); 
        }
        else{
            this.backgroundProperty().set(new Background(new BackgroundFill(Color.CORAL, CornerRadii.EMPTY, Insets.EMPTY)));
        }
        
    }
    
    public String getRoomName(){
        return this.getText();
    }
}
