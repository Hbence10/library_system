package library_system;

import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.*;

public class MainPage {

    JFrame frame;
    JPanel row;
    JLabel testLabel;
    ArrayList<JButton> buttons = new ArrayList<JButton>();

    public MainPage() {
        getAllProduct();
        frame = new JFrame();
        frame.setSize(1280, 720);
        frame.setTitle("Library System - Main page");
        frame.setResizable(false);
        
        JMenuBar menuBar = new JMenuBar();
        JMenu search = new JMenu("Search");
        JMenu history = new JMenu("My History");

        menuBar.add(search);
        menuBar.add(history);

        if (Account.logedAcc.getAdmin()) {
            JMenu addProduct = new JMenu("Add Product");
            menuBar.add(addProduct);
        }

        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        for (int j = 0; j < 5; j++) {
            row = new JPanel();
            row.setLayout(new FlowLayout());
            JPanel buttonRow = new JPanel();
            buttonRow.setLayout(new FlowLayout());
            
            for (int i = 0; i < 4; i++) {
                Image img1 = new ImageIcon("src\\coverImgs\\" + Product.allProduct.get((j * 4) + i).getCoverImg()).getImage();
                Image test = img1.getScaledInstance(220, 320, Image.SCALE_DEFAULT);
                JButton testButton = new JButton("Check");
                buttons.add(testButton);
                testButton.addActionListener(selectProduct);

               // System.out.println((j * 4) + i);
                testLabel = new JLabel();
                testLabel.setIcon(new ImageIcon(test));

                testLabel.setForeground(Color.BLACK);
                testLabel.setBackground(Color.black);

                row.add(testLabel);
                buttonRow.add(testButton);
            }

            container.add(row);
            container.add(buttonRow);
        }
        JScrollPane pane = new JScrollPane(container);
        frame.add(pane);
        frame.setJMenuBar(menuBar);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void getAllProduct() {
        String url = "jdbc:mysql://localhost:3306/library";
        String query = "SELECT * FROM book ;";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            System.out.println("Hiba");
        }

        try {
            Connection con = DriverManager.getConnection(url, "root", "ASDasd123");
            Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery(query);

            while (result.next()) {
                new Product(result.getInt(1), result.getString(2), result.getInt(3), result.getString(4), true, result.getDate(6), result.getString(7), result.getString(10));
            }

         

        } catch (Exception e) {
            System.out.println("hiba");
        }
    }

    ActionListener selectProduct = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Product.selectedProduct = Product.allProduct.get(buttons.indexOf(e.getSource()));
            frame.dispose();
            new ProductPage();
        }
    };
}
