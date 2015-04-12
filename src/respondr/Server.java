/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package respondr;

import java.lang.*;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Server {

    private ExecutorService executor = Executors.newFixedThreadPool(10);
    public static HashMap<User, String> users = new HashMap<>();
    public static ArrayList<String> list = new ArrayList<String>();
    public static User lastUser;
    private boolean running;
    private int port;

    public Server(int port) {
        this.port = port;
    }

    public void start() {
        running = true;

        Runnable acceptor = () -> {
            try (ServerSocket ss = new ServerSocket(port)) {
                while (running) {
                    User client = new User(ss.accept());
                    users.put(client, "name");
                    lastUser = client;
                    executor.execute(client);
                }
            } catch (IOException e) {
                //if the port is not open or if a server is already running on this port
                System.out.println("Server has already been opened.");
                //e.printStackTrace();
            }
        };

        Runnable userInputReader = () -> {
            try (Scanner scanner = new Scanner(System.in)) {
                while (running) {
                    String input = scanner.nextLine();
                    System.out.println("Server sending: " + input);
                    processMessage(input);
                }
            } catch (IOException e) {
                //problem sending data;
                e.printStackTrace();
            }

        };

        Thread acceptorThread = new Thread(acceptor);
        Thread userThread = new Thread(userInputReader);
        acceptorThread.start();
        userThread.start();
    }

    public void stop() {
        running = false;
    }

    public static void processMessage(String message) throws IOException {
        boolean found = false;
        int stringPosition = 1;
        while (!message.substring(0, stringPosition).contains("%")) {
            stringPosition++;
        }
        for (User key : users.keySet()) {
            if (message.substring(0, stringPosition).contains(users.get(key))) {
                System.out.println("Server sending: \"" + message.substring(stringPosition, message.length()) + "\" to: " + users.get(key));
                key.send(message.substring(stringPosition, message.length()));
                stringPosition = 1;
                found = true;
            }
        }
        if(!found){
            JOptionPane.showMessageDialog(null, "Sorry, that User is not currently online. Try again later.", "ERROR!!", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
     public static void sendGlobalMessage(String message) throws IOException {
        for (User key : users.keySet()) {
                System.out.println("Server sending: \"" + message + "\" to: " + users.get(key));
                key.send(message);
        }
    }

    public static void main(String[] args) {
        new Server(15180).start();
        System.out.println("Server started!");
    }

    class User implements Runnable {

        private Socket socket;
        private boolean connected;
        private DataOutputStream out; //so we can access from the #send(String) method

        public User(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            connected = true;

            try (DataInputStream in = new DataInputStream(socket.getInputStream())) {
                out = new DataOutputStream(socket.getOutputStream());

                while (connected) {
                    String data = in.readUTF();
                    //checks if message is name being set by client upon connection
                    if (data.startsWith("$$NAME: ")) {
                        users.replace(lastUser, data.substring(8, data.length()));
                        list.add(data.substring(8, data.length()));
                        System.out.println(users);
                    }

                    if (data.startsWith("$$RECIPIENT: ")) {
                        Server.processMessage(data);
                    }
                    if(data.startsWith("$$ONLINE: ")){
                        Server.sendGlobalMessage(data);
                    }

                    System.out.println("From client: " + data);
                    //send to all clients
                }
            } catch (IOException e) {
                //if there's a problem initializing streams;
                //if socket closes while attempting to read from it;
                System.out.println("Socket has been closed.");
                //e.printStackTrace();
            }
        }

        public void send(String message) throws IOException {
            if (connected) {
                out.writeUTF(message);
                out.flush();
            }
        }
    }
}
