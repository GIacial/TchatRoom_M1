/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UIClient;

import KernelClient.Client;
import java.awt.Panel;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import kernelServeur.MainServeur;

/**
 *
 * @author Jeremy
 */
public class MainFrame extends Application {

    private TabPane fenetre;
    private static Client c;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
       this.fenetre = new TabPane();
       fenetre.getTabs().add(new Tab_HUB(c));
       fenetre.getTabs().add(new Tab_Tchat(c));
       fenetre.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
       Scene scene = new Scene(fenetre, 900,600);
        primaryStage.setScene(scene);
        primaryStage.show(); 
    }
    
     /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {  
        c = new Client(/*args[0]*/"");
        launch(args);
    }
    
}
