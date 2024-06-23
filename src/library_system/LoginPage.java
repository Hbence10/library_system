package library_system;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.net.*;
import javax.swing.*;
import java.sql.*;
import javax.imageio.ImageIO;

class LoginPage {

    JFrame frame;
    JTextField usernameInput;
    JPasswordField passwordInput;
    JPanel loginBox;
    JLabel errorMsg;

    public LoginPage() {
        frame = new JFrame();
        frame.setSize(1280, 720);
        frame.setLayout(null);
        frame.setTitle("Library System - Login page");
        frame.setResizable(false);
        frame.setIconImage(new ImageIcon("src\\mainIcon.jpg").getImage());

        try {
            frame.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("src\\bg.jpg")))));
        } catch (Exception e) {
        }

        loginBox = new JPanel();
        loginBox.setBounds(330, 197, 620, 326);
        loginBox.setBackground(new Color(0, 0, 0, 99));
        loginBox.setLayout(null);

        JLabel usernameText = new JLabel("Username:");
        usernameText.setBounds(177, 70, 276, 30);
        usernameText.setForeground(Color.WHITE);
        usernameInput = new JTextField();
        usernameInput.setBounds(177, 95, 276, 30);
        usernameInput.addKeyListener(signWithKeyBoard);

        JLabel passwordText = new JLabel("Password:");
        passwordText.setBounds(177, 155, 276, 30);
        passwordText.setForeground(Color.WHITE);
        passwordInput = new JPasswordField();
        passwordInput.setBounds(177, 180, 276, 30);
        passwordInput.addKeyListener(signWithKeyBoard);

        JButton signButton = new JButton("Sign up");
        signButton.setBounds(155, 220, 95, 20);
        signButton.setBackground(Color.WHITE);
        signButton.setBorder(null);
        signButton.addActionListener(sign);
        signButton.setFocusable(false);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(197, 275, 226, 25);
        loginButton.addActionListener(login);
        loginButton.setFocusable(false);
        loginButton.setBackground(Color.WHITE);

        errorMsg = new JLabel();
        errorMsg.setBounds(177, 25, 276, 30);
        errorMsg.setHorizontalAlignment(JLabel.CENTER);
        errorMsg.setOpaque(false);
        errorMsg.setForeground(Color.white);

        loginBox.add(errorMsg);

        loginBox.add(usernameText);
        loginBox.add(passwordText);
        loginBox.add(usernameInput);
        loginBox.add(passwordInput);
        loginBox.add(signButton);
        loginBox.add(loginButton);

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
                    Account.setLogedAcc(new Account(result.getInt("userId"), username, password, result.getString("email"), result.getBoolean("admin")));
                    frame.dispose();
                    new MainPage();
                }
            }

            if (checkLogin == 0) {
                errorMsg.setText("Bad username or password");
                loginBox.setBackground(new Color(0, 0, 0, 99));
                loginBox.repaint();
            }

        } catch (Exception e) {
            System.out.println("hiba");
        }
    }

    ActionListener sign = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Desktop.getDesktop().browse(new URL("http://localhost:4200/").toURI());
            } catch (Exception d) {
            }
        }
    };

    KeyListener signWithKeyBoard = new KeyListener() {
        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == 10) {
                checkAccount("\"" + usernameInput.getText() + "\"", "\"" + passwordInput.getText() + "\"");
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }
    };
}
