/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package respondr;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JOptionPane;
import static respondr.RespondrAddAFriendResultsScreen.name;

/**
 *
 * @author hmf5hnz
 */
public class RespondrChangePasswordScreen extends javax.swing.JFrame {

    /**
     * Creates new form RespondrChangePasswordScreen
     */
    public static String name;

    public RespondrChangePasswordScreen(String name) {
        this.name = name;

        initComponents();
        
        this.oldPasswordTextField.requestFocusInWindow();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        this.setTitle("Change Password");

        oldPasswordTextField.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    e.consume();
                    newPasswordTextField.requestFocusInWindow();
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        newPasswordTextField.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    e.consume();
                    confirmPasswordTextField.requestFocusInWindow();
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        oldPasswordTitle = new javax.swing.JLabel();
        newPasswordTitle = new javax.swing.JLabel();
        confirmPasswordTitle = new javax.swing.JLabel();
        oldPasswordTextField = new javax.swing.JPasswordField();
        newPasswordTextField = new javax.swing.JPasswordField();
        confirmPasswordTextField = new javax.swing.JPasswordField();
        submitButton = new javax.swing.JButton();
        titleText = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        oldPasswordTitle.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        oldPasswordTitle.setText("Enter your old password:");

        newPasswordTitle.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        newPasswordTitle.setText("Enter your new password:");

        confirmPasswordTitle.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        confirmPasswordTitle.setText("Confirm your new password:");

        submitButton.setText("Submit");
        submitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitButtonActionPerformed(evt);
            }
        });

        titleText.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        titleText.setText("Change Password");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(confirmPasswordTitle)
                        .addGap(18, 18, 18)
                        .addComponent(confirmPasswordTextField))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(newPasswordTitle)
                            .addComponent(oldPasswordTitle))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(newPasswordTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(oldPasswordTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 32, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(submitButton)
                        .addGap(177, 177, 177))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(titleText)
                        .addGap(163, 163, 163))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(titleText)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(oldPasswordTitle)
                    .addComponent(oldPasswordTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newPasswordTitle)
                    .addComponent(newPasswordTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(confirmPasswordTitle)
                    .addComponent(confirmPasswordTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(submitButton)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void submitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitButtonActionPerformed
        
        boolean changePassword = false;
        try (BufferedReader br = new BufferedReader(new FileReader("UserDatabase.txt"))) {
            String line = "";
            boolean found = false;
            while ((line = br.readLine()) != null) {
                if (line.substring(0, 49).trim().equals(name)) {
                    if (line.substring(100, 149).trim().equals(oldPasswordTextField.getText())) {
                        if (newPasswordTextField.getText().equals(confirmPasswordTextField.getText())) {
                            changePassword = true;
                            found = true;
                        } else {
                            JOptionPane.showMessageDialog(null, "Your new password fields do not match.", "ERROR!!", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Your old password was not entered correctly.", "ERROR!!", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
            if (br.readLine() == null && !found) {
                JOptionPane.showMessageDialog(null, "Sorry, you entered either your email or password incorrectly.", "ERROR!!", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR: EITHER FILE'S NOT FOUND OR SERVER ISN'T OPEN YET!", "ERROR!!", JOptionPane.INFORMATION_MESSAGE);
            System.out.println(e);
        }

        if (changePassword) {
            StringBuilder newPassword = new StringBuilder(newPasswordTextField.getText());
            while (newPassword.length() < 50) {
                newPassword.append(" ");
            }
            try (BufferedReader br = new BufferedReader(new FileReader("UserDatabase.txt"))) {
                String line = "";
                File file = new File("test.txt");
                File file2 = new File("UserDatabase.txt");
                while ((line = br.readLine()) != null) {
                    if (line.substring(0, 49).trim().equals(name)) {
                        line = line.substring(0, 100) + newPassword.toString() + line.substring(150, line.length());
                        System.out.println(line);
                        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("test.txt", true)))) {
                            out.println(line);
                            JOptionPane.showMessageDialog(null, "Your password has been successfully changed!", "Person Added", JOptionPane.INFORMATION_MESSAGE);
                            //break;
                        } catch (IOException e) {
                            System.out.println("Couldn't find file.");
                        }
                        //this.dispose();
                    } else {
                        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("test.txt", true)))) {
                            System.out.println(line);
                            out.println(line);
                        } catch (IOException e) {
                            System.out.println("Couldn't find file.");
                        }
                    }
                }
                br.close();
                file2.delete();
                file.renameTo(file2);
                this.dispose();

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Couldn't find file..", "ERROR!!", JOptionPane.INFORMATION_MESSAGE);
                System.out.println(e);
            }

        }
    }//GEN-LAST:event_submitButtonActionPerformed

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
            java.util.logging.Logger.getLogger(RespondrChangePasswordScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RespondrChangePasswordScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RespondrChangePasswordScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RespondrChangePasswordScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RespondrChangePasswordScreen(name).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPasswordField confirmPasswordTextField;
    private javax.swing.JLabel confirmPasswordTitle;
    private javax.swing.JPasswordField newPasswordTextField;
    private javax.swing.JLabel newPasswordTitle;
    private javax.swing.JPasswordField oldPasswordTextField;
    private javax.swing.JLabel oldPasswordTitle;
    private javax.swing.JButton submitButton;
    private javax.swing.JLabel titleText;
    // End of variables declaration//GEN-END:variables
}
