/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UIClient;

import javafx.scene.control.Tab;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Jeremy
 */
public class Tab_HUB extends Tab {
    
    private UI_TchatroomList list;
    
    public Tab_HUB (){
        super("HUB");
        GridPane grid = new GridPane();
        this.list = new UI_TchatroomList();
        this.setContent(grid);
        grid.add(list, 0, 0, 4, 4);
    }
}
