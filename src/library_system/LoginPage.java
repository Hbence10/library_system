package library_system;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

class LoginPage {

    JFrame frame;
    JTextField usernameInput;
    JTextField passwordInput;
    JPanel loginBox;
    
    public LoginPage() {
        frame = new JFrame();
        frame.setSize(1280, 720);
        frame.setBackground(Color.yellow);
        frame.setLayout(null);
        frame.setTitle("Library System - Login page");

        loginBox = new JPanel();
        loginBox.setBounds(0, 0, 620, 325);
        loginBox.setBackground(Color.red);

        usernameInput = new JTextField();
        usernameInput.setPreferredSize(new Dimension(275, 25));

        passwordInput = new JTextField();
        passwordInput.setPreferredSize(new Dimension(275, 25));

        JButton button = new JButton("Login");
        button.addActionListener(login);

        loginBox.add(usernameInput);
        loginBox.add(passwordInput);
        loginBox.add(button);

        frame.add(loginBox);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    ActionListener login = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            checkAccount("\"" + usernameInput.getText() + "\"", "\"" + passwordInput.getText() + "\"");
        }
    };

    public void checkAccount(String username, String password) {
        String url = "jdbc:mysql://localhost:3306/library";
        String query = "SELECT * FROM user where Username = " + username + " and password = " + password + ";";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            System.out.println("Hiba");
        }

        try {
            Connection con = DriverManager.getConnection(url, "root", "ASDasd123");
            Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery(query);

            int checkLogin = 0;
            while (result.next()) {
                checkLogin += 1;
                if (!result.getString("userId").equals("")) {
                      Account.logedAcc = new Account(result.getInt("userId"), username, password, result.getString("email"),  result.getBoolean("admin"));
                }
            }

            if(checkLogin == 0){
                JLabel errorMsg = new JLabel("Bad username/password");
                loginBox.add(errorMsg);
                frame.setVisible(true);
            }
        } catch (Exception e) {
            System.out.println("hiba");
        }
    }
}
