package library_system;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;
import java.sql.*;
import javax.imageio.ImageIO;
import javax.swing.border.Border;

class LoginPage {

    JFrame frame;
    JTextField usernameInput;
    JTextField passwordInput;
    JPanel loginBox;
    JLabel errorMsg;

    public LoginPage() {
        frame = new JFrame();
        frame.setSize(1280, 720);
        frame.setLayout(null);
        frame.setTitle("Library System - Login page");
        frame.setResizable(false);
        frame.setIconImage(new ImageIcon("src\\icon.jpg").getImage());

        try {
            frame.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("src\\bg2.jpg")))));
        } catch (Exception e) {
        }

        loginBox = new JPanel();
        loginBox.setBounds(330, 197, 620, 326);
        loginBox.setBackground(new Color(70, 170, 150));
        loginBox.setLayout(null);

        JLabel text1 = new JLabel("Username:");
        text1.setBounds(177, 70, 276, 30);
        text1.setForeground(Color.WHITE);
        usernameInput = new JTextField();
        usernameInput.setBounds(177, 95, 276, 30);

        JLabel text2 = new JLabel("Password:");
        text2.setBounds(177, 155, 276, 30);
        text2.setForeground(Color.WHITE);
        passwordInput = new JTextField();
        passwordInput.setBounds(177, 180, 276, 30);

        JButton button = new JButton("Login");
        button.setBounds(197, 275, 226, 25);
        button.addActionListener(login);

        errorMsg = new JLabel();
        errorMsg.setBounds(177, 25, 276, 30);
        errorMsg.setHorizontalAlignment(JLabel.CENTER);

        loginBox.add(errorMsg);

        loginBox.add(text1);
        loginBox.add(text2);
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
                    Account.logedAcc = new Account(result.getInt("userId"), username, password, result.getString("email"), result.getBoolean("admin"));
                    frame.dispose();
                    new MainPage();
                }
            }

            if (checkLogin == 0) {    
                errorMsg.setText("Bad username or password");
            }
            
        } catch (Exception e) {
            System.out.println("hiba");
        }
    }
}
