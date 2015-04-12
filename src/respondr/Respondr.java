/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package respondr;

import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author hmf5hnz
 */
public class Respondr {

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) {
        //just launches the log-in screen first
        RespondrLogInScreen logInScreen = new RespondrLogInScreen();
        logInScreen.setVisible(true);

        //if Server isn't running yet, declare and open server
        try {
            new Server(15180).start();
            System.out.println("Server started!");
        } catch (Exception e) {
            System.out.println("");
        }
    }
}
