/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UIClient;

import KernelClient.Client;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

/**
 *
 * @author Jeremy
 */
public class Tab_Tchat extends Tab{
    
    public Tab_Tchat(Client c){
        super("Tchat");
        TabPane root = new TabPane();
        this.setContent(root);
        //root.getTabs().add(new Tab("er"));
    }
}
