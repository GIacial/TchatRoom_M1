/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UIClient;

import KernelClient.IC_Tchatroom;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Tab;
import kernelMsg.AbstractMSG;
import kernelMsg.IC_BulleMsg;
import kernelServeur.TchatRoom;

/**
 *
 * @author Jeremy
 */
public class UI_Tchtroom extends Tab implements IC_Tchatroom {
    
    private List<IC_BulleMsg> msg;

    public UI_Tchtroom(TchatRoom room) throws RemoteException{
        super(room.getName());
        this.msg = new ArrayList<>();
    }
    
    @Override
    public void addMsg(AbstractMSG msg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
