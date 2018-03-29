/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UIClient;

import javafx.scene.Node;
import javafx.scene.layout.HBox;
import kernelMsg.IC_BulleMsg;

/**
 *
 * @author Jeremy
 */
public class UI_BulleMsg extends HBox implements IC_BulleMsg {
    
    public UI_BulleMsg(){
        
    }

    @Override
    public void addItem(Node element) {       
        this.getChildren().add(element);
    }
    
}
