/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UIClient;

import KernelClient.IC_Tchatroom;
import java.rmi.RemoteException;
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;
import kernelMsg.AbstractMSG;
import kernelServeur.TchatRoom;

/**
 *
 * @author Jeremy
 */
public class UI_Tchtroom extends Tab implements IC_Tchatroom {
    
    private VBox msg;

    public UI_Tchtroom(TchatRoom room) throws RemoteException{
        super(room.getName());
        this.msg = new VBox();
        
        //creation du listener et donner a la room (need client)
    }
    
    @Override
    public void addMsg(AbstractMSG msg) {
       UI_BulleMsg b = new UI_BulleMsg();
       msg.affiche(b);
       this.msg.getChildren().add(b);
       
    }
    
}
