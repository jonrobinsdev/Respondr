/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package respondr;

import java.net.Socket;

/**
 *
 * @author hmf5hnz
 */
public class User {
    
    private String name;
    private String email;
    private String password;
 
    public User(String name, String email, String password){
        this.name = name;
        this.email = email;
        this.password = password;
    }

    User(Socket accept) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public String getName(User x){
        return x.name;
    }
    
    public String getEmail(User x){
        return x.email;
    }
    
    public String getPassword(User x){
        return x.password;
    }

    void send(String input) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
} 
