/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package respondr;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.text.DefaultCaret;
import static respondr.RespondrChangePasswordScreen.name;
import static respondr.Server.users;

/**
 *
 * @author HMF5HNZ
 */
public class RespondrMessageScreen extends javax.swing.JFrame {

    /**
     * Creates new form RespondrMessageScreen
     */
    public static int port;
    private static String title = "";
    public static Client client;
    public static String recipient;

    //sets up port and conversation title
    public RespondrMessageScreen(String name, Client client) throws IOException {
        this.port = port;
        this.client = client;
        this.recipient = name;
        
        RespondrMessageScreen.title = "Conversation with " + name;
        initComponents();
        this.conversationTitle.setText(RespondrMessageScreen.title);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.enterTextField.requestFocusInWindow();
        this.getRootPane().setDefaultButton(sendButton);
        DefaultCaret caret = (DefaultCaret) conversationTextArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        //adds key listener for executing send button logic upon hitting the enter key
        enterTextField.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    e.consume();
                    sendButtonClicked();
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        //Thread for constantly accepting and appending incoming messages from the Server
        conversationTextArea.append("You are now connected and can begin chatting!");

        Runnable getMessage = () -> {
            while (true) {
                //if message is the default name string or isn't a new message, dont append
                if (client.newMessage == true && !client.message.startsWith("$$NAME: ") && !client.message.startsWith("$$ONLINE: ")) {
                    int stringPosition = 1;
                    while (!client.message.substring(0, stringPosition).contains("$")) {
                        stringPosition++;
                    }
                    if (client.message.substring(stringPosition + 7, client.message.length()).contains(recipient)) {
                        client.message = client.message.substring(0, stringPosition - 1);
                        this.conversationTextArea.append("\n" + client.message);
                        //logic for conversation database
                        try {

                            File file = new File(client.name + ".txt");

                            //if file doesnt exists, then create it
                            if (!file.exists()) {
                                file.createNewFile();
                            }

                            //adding to conversation database for person receiving message
                            FileWriter fileWriter = new FileWriter(file.getName(), true);
                            BufferedWriter bufferWriter = new BufferedWriter(fileWriter);
                            bufferWriter.write("FROM: " + recipient);
                            bufferWriter.newLine();
                            bufferWriter.write(client.message);
                            bufferWriter.newLine();
                            bufferWriter.close();
                            
                            //adding to conversation for person who sent it
                            file = new File(recipient + ".txt");
                            if (!file.exists()) {
                                file.createNewFile();
                            }
                            fileWriter = new FileWriter(file.getName(), true);
                            bufferWriter = new BufferedWriter(fileWriter);
                            bufferWriter.write("TO: " + client.name);
                            bufferWriter.newLine();
                            bufferWriter.write(client.message);
                            bufferWriter.newLine();
                            bufferWriter.close();
                            
                            System.out.println("Added conversation to database.");

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        System.out.println(client.name + " " + recipient);
                    }
                    client.newMessage = false;
                }
            }
        };

        Thread messageThread = new Thread(getMessage);
        messageThread.start();
    }

    public static void reprocessMessage(String message) throws IOException {
        
        int stringPosition = 1;
        while (!message.substring(0, stringPosition).contains("$")) {
            stringPosition++;
        }
        
        if(client.message.substring(stringPosition+7, client.message.length()).contains(recipient)){
        client.message = client.message.substring(0, stringPosition-1);
        }
    }

    //send button logic for sending message to recipient
    public void sendButtonClicked() {
        if (!this.conversationTextArea.getText().isEmpty()) {
            this.conversationTextArea.append("\n");
        }
        //for spacing layout of conversation correctly
        StringBuffer newMessage = new StringBuffer("");
        for (int i = 0; i < 165 - this.enterTextField.getText().length(); i++) {
            newMessage.append(" ");
        }
        newMessage.append(this.enterTextField.getText());
        this.conversationTextArea.append(newMessage.toString());
        client.sendMessage("$$RECIPIENT: " + recipient + "%" + this.enterTextField.getText() + "$$FROM: " +  client.name);
        this.enterTextField.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        enterTextField = new javax.swing.JTextArea();
        sendButton = new javax.swing.JButton();
        conversationTitle = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        conversationTextArea = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        enterTextField.setColumns(20);
        enterTextField.setRows(5);
        enterTextField.setAutoscrolls(false);
        jScrollPane1.setViewportView(enterTextField);

        sendButton.setText("Send");
        sendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendButtonActionPerformed(evt);
            }
        });

        conversationTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        conversationTitle.setText("Conversation with: Person 1");

        conversationTextArea.setColumns(20);
        conversationTextArea.setRows(5);
        jScrollPane3.setViewportView(conversationTextArea);
        conversationTextArea.setEditable(false);
        conversationTextArea.setWrapStyleWord(true);
        conversationTextArea.setLineWrap(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sendButton, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE))
            .addComponent(conversationTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(conversationTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(sendButton, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sendButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendButtonActionPerformed
        sendButtonClicked();
    }//GEN-LAST:event_sendButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RespondrMessageScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RespondrMessageScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RespondrMessageScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RespondrMessageScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new RespondrMessageScreen(title, client).setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(RespondrMessageScreen.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea conversationTextArea;
    public javax.swing.JLabel conversationTitle;
    private javax.swing.JTextArea enterTextField;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton sendButton;
    // End of variables declaration//GEN-END:variables

}
