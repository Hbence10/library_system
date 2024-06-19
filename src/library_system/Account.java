package library_system;
import java.util.* ;
import java.awt.*;
import javax.swing.*;

public class Account {
    private int userId;
    private String username; 
    private String password;
    private String email;
    private boolean admin;
    
    static Account logedAcc;

    public Account(int userId, String username, String password, String email,boolean admin) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.admin = admin;
        
        logedAcc = this;
    }

    @Override
    public String toString() {
        return "username: " + username + " password: " + password + " a";
    }

    public boolean getAdmin(){
        return this.admin;
    }

    public String getUsername() {
        return username;
    }

    public int getUserId() {
        return userId;
    }
    
    
}