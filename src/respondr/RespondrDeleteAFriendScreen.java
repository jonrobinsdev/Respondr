/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package respondr;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import static respondr.RespondrLogInScreen.friendsList;

/**
 *
 * @author HMF5HNZ
 */
public class RespondrDeleteAFriendScreen extends javax.swing.JFrame {

    /**
     * Creates new form RespondrAddAFriendScreen
     */
    private static String name;

    public RespondrDeleteAFriendScreen(String name) {
        initComponents();

        this.name = name;

        nameButton.setSelected(true);
        emailTextField.setEditable(false);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("Delete a Friend");
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        jLabel1 = new javax.swing.JLabel();
        emailButton = new javax.swing.JRadioButton();
        nameButton = new javax.swing.JRadioButton();
        nameTextField = new javax.swing.JTextField();
        emailTextField = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Enter the name or email of the User you wish to delete:");

        emailButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        emailButton.setText("Email");
        emailButton.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                emailButtonFocusGained(evt);
            }
        });

        nameButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        nameButton.setText("Name");
        nameButton.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                nameButtonFocusGained(evt);
            }
        });

        searchButton.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        searchButton.setText("Search");
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nameButton)
                            .addComponent(emailButton))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(emailTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                            .addComponent(nameTextField)))
                    .addComponent(jLabel1))
                .addContainerGap(53, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(searchButton)
                .addGap(180, 180, 180))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nameButton))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(emailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(emailButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addComponent(searchButton)
                .addGap(26, 26, 26))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed

        String[] foundResults = new String[10];
        int i = 0;
        boolean found = false;
        boolean initialLineStored = false;
        int resultsArrayPosition = 0;
        if (nameTextField.getText().equals("")) {
            try (BufferedReader br = new BufferedReader(new FileReader("UserDatabase.txt"))) {
                String line = "";
                String initialLine = "";
                while ((line = br.readLine()) != null) {
                    while (!initialLineStored && line.substring(0, 49).contains(name.trim())) {
                        initialLine = line;
                        initialLineStored = true;
                    }
                    if (line.substring(50, 100).trim().toLowerCase().contains(emailTextField.getText().trim().toLowerCase())) {
                        if (initialLine.length() >= 151) {
                            int pastPosition = 150;
                            int currentPosition = 151;
                            while (!initialLine.substring(pastPosition, currentPosition).contains("|")) {
                                currentPosition++;
                                if (initialLine.substring(pastPosition, currentPosition).contains("|")) {
                                    if (line.substring(0, 99).toLowerCase().contains(initialLine.substring(pastPosition, currentPosition - 1).toLowerCase())) {
                                        System.out.println(line);
                                        foundResults[resultsArrayPosition] = (line.substring(0, 99));
                                        resultsArrayPosition++;
                                        found = true;
                                    }
                                    pastPosition = currentPosition;
                                    currentPosition = pastPosition + 1;
                                    if (pastPosition == initialLine.length()) {
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
                if (br.readLine() == null && !found) {
                    JOptionPane.showMessageDialog(null, "Found no results matching your criteria.", "ERROR!!", JOptionPane.INFORMATION_MESSAGE);
                }
                if (found) {
                    RespondrDeleteAFriendResultsScreen resultScreen = new RespondrDeleteAFriendResultsScreen(foundResults, name);
                    resultScreen.setVisible(true);

                    Runnable whileResultsFriendsListExists = () -> {
                        while (true) {
                            if (!resultScreen.isVisible()) {
                                this.dispose();
                                break;
                            }
                        }
                    };
                    Thread friendsListThread3 = new Thread(whileResultsFriendsListExists);
                    friendsListThread3.start();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "ERROR: EITHER FILE'S NOT FOUND OR SERVER ISN'T OPEN YET!", "ERROR!!", JOptionPane.INFORMATION_MESSAGE);
                System.out.println(e);
            }
        } else {
            try (BufferedReader br = new BufferedReader(new FileReader("UserDatabase.txt"))) {
                String line = "";
                String initialLine = "";
                while ((line = br.readLine()) != null) {
                    while (!initialLineStored && line.substring(0, 49).contains(name.trim())) {
                        initialLine = line;
                        initialLineStored = true;
                    }
                    if (line.substring(0, 49).trim().toLowerCase().contains(nameTextField.getText().trim().toLowerCase())) {
                        if (initialLine.length() >= 151) {
                            int pastPosition = 150;
                            int currentPosition = 151;
                            while (!initialLine.substring(pastPosition, currentPosition).contains("|")) {
                                currentPosition++;
                                if (initialLine.substring(pastPosition, currentPosition).contains("|")) {
                                    if (line.substring(0, 99).toLowerCase().contains(initialLine.substring(pastPosition, currentPosition - 1).toLowerCase())) {
                                        System.out.println(line);
                                        foundResults[resultsArrayPosition] = (line.substring(0, 99));
                                        resultsArrayPosition++;
                                        found = true;
                                    }
                                    pastPosition = currentPosition;
                                    currentPosition = pastPosition + 1;
                                    if (pastPosition == initialLine.length()) {
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
                if (br.readLine() == null && !found) {
                    JOptionPane.showMessageDialog(null, "Found no results matching your criteria.", "ERROR!!", JOptionPane.INFORMATION_MESSAGE);
                }
                if (found) {
                    RespondrDeleteAFriendResultsScreen resultScreen = new RespondrDeleteAFriendResultsScreen(foundResults, name);
                    resultScreen.setVisible(true);

                    Runnable whileResultsFriendsListExists = () -> {
                        while (true) {
                            if (!resultScreen.isVisible()) {
                                this.dispose();
                                break;
                            }
                        }
                    };
                    Thread friendsListThread3 = new Thread(whileResultsFriendsListExists);
                    friendsListThread3.start();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "ERROR: EITHER FILE'S NOT FOUND OR SERVER ISN'T OPEN YET!", "ERROR!!", JOptionPane.INFORMATION_MESSAGE);
                System.out.println(e);
            }
        }

    }//GEN-LAST:event_searchButtonActionPerformed

    private void emailButtonFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_emailButtonFocusGained
        emailTextField.setEditable(true);
        emailTextField.setVisible(true);

        nameTextField.setEditable(false);
        nameButton.setSelected(false);
        nameTextField.setText("");
        nameTextField.setVisible(false);
    }//GEN-LAST:event_emailButtonFocusGained

    private void nameButtonFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nameButtonFocusGained
        nameTextField.setEditable(true);
        nameTextField.setVisible(true);

        emailTextField.setEditable(false);
        emailButton.setSelected(false);
        emailTextField.setText("");
        emailTextField.setVisible(false);
    }//GEN-LAST:event_nameButtonFocusGained

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
            java.util.logging.Logger.getLogger(RespondrDeleteAFriendScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RespondrDeleteAFriendScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RespondrDeleteAFriendScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RespondrDeleteAFriendScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RespondrDeleteAFriendScreen(name).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton emailButton;
    private javax.swing.JTextField emailTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JRadioButton nameButton;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JButton searchButton;
    // End of variables declaration//GEN-END:variables
}