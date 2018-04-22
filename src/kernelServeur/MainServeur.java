/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kernelServeur;

import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import kernelMsg.PseudoNotFoundException;

/**
 *
 * @author lucille
 */
public class MainServeur {
    
    public static final String factory_service = "TchatRoom_serveur_HUB";
    private static HUBImpl objserv;
    
    public static void main(String[] args){
         objserv = null;

        try {
            System.setProperty("java.rmi.server.hostname", "127.0.0.1");
            System.out.println("Creation de l'objet.");
            objserv = new HUBImpl();
            System.out.println("Enregistrement de l'objet.");
            Naming.rebind(factory_service,(Remote) objserv);
            System.out.println("serveur operationnel.");
            

            //Timer
            TimerTask tasknew;
            tasknew = new TimerTask() {
                @Override
                public void run() {
                    try {
                        objserv.testActiviteRoom();
                    } catch (PseudoNotFoundException | RemoteException ex) {
                        Logger.getLogger(MainServeur.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }; 
            
            Timer timer = new Timer();

            // scheduling the task at fixed rate delay
            timer.scheduleAtFixedRate(tasknew,500,1000);     

        } catch (Exception e) {
            System.out.println(e);
        }
    }
     
}
