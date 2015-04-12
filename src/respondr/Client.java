/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package respondr;

/**
 *
 * @author hmf5hnz
 */
import java.lang.*;
import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Client {

    public String message;
    public boolean newMessage;
    public static String name;
    private final int port;
    private boolean connected;
    private Socket socket;

    public Client(int port) {
        this.port = port;
    }

    public void start(String name) throws IOException {
        message = "";
        connected = true;
        socket = new Socket("localhost", port);
        //client sends their name upon connection
        sendMessage("$$NAME: " + name);
        sendMessage("$$ONLINE: " + name);

        //constantly checks for new messages then sets it as its current message and sets newMessage flag = true
        Runnable serverInputReader = () -> {
            try (DataInputStream in = new DataInputStream(socket.getInputStream())) {
                while (connected) {
                    String data = in.readUTF();
                    System.out.println("From server: " + data);
                    this.message = data;
                    this.newMessage = true;
                }
            } catch (IOException e) {
                //problem connecting to server; problem wrapping stream; problem receiving data from server;
                //e.printStackTrace();
            }
        };
        Thread communicateThread = new Thread(serverInputReader);
        communicateThread.start();
    }

    public void sendMessage(String outgoingMessage) {
        DataOutputStream out = null;
        try {
            out = new DataOutputStream(socket.getOutputStream());
            System.out.println("Client sending: " + outgoingMessage);
            out.writeUTF(outgoingMessage);
            out.flush();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } /*finally {
         try {
         out.close();
         } catch (IOException ex) {
         Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
         }*/

    }

    public static void main(String[] args) throws IOException {
        new Client(15180).start(name);
    }
}
