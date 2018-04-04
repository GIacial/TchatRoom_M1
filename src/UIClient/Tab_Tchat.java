/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UIClient;

import KernelClient.Client;
import KernelClient.IC_Tchatroom;
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
    private Client c;
    
    
    public Tab_Tchat(Client c, MainFrame m){
        super("Tchat");
        this.m = m;
        this.c = c;
         root = new TabPane();
        this.setContent(root);
    }
    
    public IC_Tchatroom addTchat(TchatRoom t) {
        UI_Tchtroom r = null;
        try {
            r  = new UI_Tchtroom(t,c,m);
            root.getTabs().add(r);
            this.root.getSelectionModel().select(r);
        } catch (RemoteException ex) {
            m.showException(ex);
        }
        return r;
    }
}
