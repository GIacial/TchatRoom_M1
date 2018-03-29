/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UIClient;

import KernelClient.Client;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.stage.Stage;

/**
 *
 * @author Jeremy
 */
public class MainFrame extends Application {

    private TabPane fenetre;
    private static Client c;
    private UI_ExceptionDisplay exceptiondisplay;
    private Tab_Tchat tchats;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        //creation pop up exception
        this.exceptiondisplay = new UI_ExceptionDisplay(primaryStage);
        //demande de pseudo
        UI_PseudoDialog pseudo = new UI_PseudoDialog(primaryStage,c,this);
        pseudo.showAndWait();
        
        //creation de la fentre
       this.fenetre = new TabPane();
       tchats = new Tab_Tchat(c,this);
       fenetre.getTabs().add(new Tab_HUB(c,this,primaryStage,tchats));
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
    public static void main(String[] args) {  
        c = new Client(/*args[0]*/"");
        launch(args);
    }
    
    
    
}
