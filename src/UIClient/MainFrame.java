/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UIClient;

import KernelClient.Client;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import kernelMsg.PseudoNotFoundException;

/**
 *
 * @author Jeremy
 */
public class MainFrame extends Application {

    private TabPane fenetre;
    private static Client c;
    public static boolean onRun = true;             //permet de fermer le thread d'actualissation des rooms
    private UI_ExceptionDisplay exceptiondisplay;
    private Tab_Tchat tchats;
    

    
    @Override
    public void start(Stage primaryStage) throws Exception {
       
        //creation pop up exception
        this.exceptiondisplay = new UI_ExceptionDisplay(primaryStage);
        //demande de pseudo
        UI_PseudoDialog pseudo = new UI_PseudoDialog(primaryStage,c,this);
        pseudo.showAndWait();
        
         //a la fermeture
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>(){
            @Override
            public void handle(WindowEvent event) {
                try {
                    c.disconnect();
                    onRun = false;
                    stop();
                } catch (Exception  ex) {
                   
                }
            }
        });
        
        //creation de la fentre
       this.fenetre = new TabPane();
       tchats = new Tab_Tchat(c,this);
       Tab_HUB hub = new Tab_HUB(c,this,primaryStage,tchats);
       fenetre.getTabs().add(hub);
       fenetre.getTabs().add(tchats);
       fenetre.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
       Scene scene = new Scene(fenetre, 900,600);
        primaryStage.setScene(scene);
        primaryStage.show(); 
        

        
        
    }
    
    public void showException(Exception e){
        exceptiondisplay.showException(e);
    }
    
     /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws NotBoundException, MalformedURLException, RemoteException {  
     c = new Client(/*args[0]*/"");
        launch(args);
    }

    public void goTchatOnglet(){
        fenetre.getSelectionModel().select(tchats);
    }

    
    
    
}
