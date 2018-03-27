/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KernelClient;

import java.util.Scanner;

/**
 *
 * @author lucille
 */
public class MainClient {
    
    public static void main(String[] args){
        if(args.length <= 0){
	      System.err.println("manque l'argument du hostname du server");
	      return;
	}

        try {

            Scanner sc = new Scanner(System.in);

            Client objdist= new Client(args[0]);
            System.out.println("Quel est votre pseudo ? "); 
            String pseudo = sc.next();
            objdist.connect(pseudo);

        } catch (Exception e) {
            System.out.println(e);
        } //end try/catch
    }
}
