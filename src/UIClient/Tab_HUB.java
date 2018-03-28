/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UIClient;

import KernelClient.Client;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Jeremy
 */
public class Tab_HUB extends Tab {
    
    private UI_TchatroomList list;
    private Client c;
    private TextArea mdp;
    
    public Tab_HUB (Client c){
        super("HUB");
        this.c = c;
        BorderPane grid = new BorderPane();
        this.list = new UI_TchatroomList(c.getAllTchatRoomName());
        this.mdp = new TextArea();
        this.mdp.setPrefRowCount(1);
        VBox bottom = new VBox();
        
        
        this.setContent(grid);
        grid.setCenter(list);
        grid.setBottom(bottom);
        bottom.getChildren().add(mdp);
        
    }
}
