/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UIClient;

import KernelClient.Client;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import kernelServeur.TchatRoom;

/**
 *
 * @author Jeremy
 */
public class Tab_Tchat extends Tab{
    
    
    private TabPane root;
    private MainFrame m;
    
    
    public Tab_Tchat(Client c, MainFrame m){
        super("Tchat");
        this.m = m;
         root = new TabPane();
        this.setContent(root);
        //
    }
    
    public void addTchat(TchatRoom t) {
        try {
            root.getTabs().add(new UI_Tchtroom(t));
        } catch (RemoteException ex) {
            m.showException(ex);
        }
    }
}
