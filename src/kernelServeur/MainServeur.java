/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kernelServeur;

import java.rmi.Naming;
import java.rmi.Remote;

/**
 *
 * @author lucille
 */
public class MainServeur {
    
    public static final String factory_service = "TchatRoom_serveur_HUB";
    
    public static void main(String[] args){
        HUBImpl objserv = null;

        try {
            System.setProperty("java.rmi.server.hostname", "127.0.0.1");
            System.out.println("Creation de l'objet.");
            objserv = new HUBImpl();
            System.out.println("Enregistrement de l'objet.");
            Naming.rebind(factory_service,(Remote) objserv);
            System.out.println("serveur operationnel.");

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
