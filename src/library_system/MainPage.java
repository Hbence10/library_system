package library_system;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.*;
import javax.swing.event.*;

public class MainPage {

    JFrame frame;
    JPanel row;
    JLabel coverLabel;
    ArrayList<JButton> buttons = new ArrayList<JButton>();
    JMenu mainPage;
    JMenu search;
    JMenu history;
    JMenu addProduct;
    JMenu quit;

    public MainPage() {
        Product.setAllProduct(new ArrayList<Product>());
        getAllProduct();
        frame = new JFrame();
        frame.setSize(1280, 720);
        frame.setTitle("Library System - Main page");
        frame.setResizable(false);
        frame.setIconImage(new ImageIcon("src\\mainIcon.jpg").getImage());

        JMenuBar menuBar = new JMenuBar();
        mainPage = new JMenu("Main Page");
        search = new JMenu("Search");
        history = new JMenu("My History");
        quit = new JMenu("Log out");
        
        mainPage.addMenuListener(navigate);
        search.addMenuListener(navigate);
        history.addMenuListener(navigate);
        quit.addMenuListener(navigate);
        
        menuBar.add(mainPage);
        menuBar.add(search);
        menuBar.add(history);
      

        if (Account.getLogedAcc().getAdmin()) {
            addProduct = new JMenu("Add Product");
            menuBar.add(addProduct);
            addProduct.addMenuListener(navigate);
        }
          menuBar.add(quit);

        int counter = 0;

        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setBackground(new Color(0, 0, 0, 96));

        for (int j = 0; j < Product.getAllProduct().size() / 4; j++) {
            row = new JPanel();
            row.setLayout(new FlowLayout());
            JPanel buttonRow = new JPanel();
            buttonRow.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 10));

            for (int i = 0; i < 4; i++) {
                counter += 1;
                Image firstImg = new ImageIcon("src\\coverImgs\\" + Product.getAllProduct().get((j * 4) + i).getCoverImg()).getImage();
                Image coverImg = firstImg.getScaledInstance(220, 320, Image.SCALE_DEFAULT);
                JButton checkButton = new JButton("Check");
                checkButton.setFocusable(false);
                checkButton.setPreferredSize(new Dimension(175, 25));
                checkButton.setBackground(Color.WHITE);
                checkButton.setBorder(BorderFactory.createEtchedBorder());
                buttons.add(checkButton);
                checkButton.addActionListener(selectProduct);

                coverLabel = new JLabel();
                coverLabel.setIcon(new ImageIcon(coverImg));

                coverLabel.setForeground(Color.BLACK);
                coverLabel.setBackground(Color.black);

                row.add(coverLabel);
                buttonRow.add(checkButton);
            }

            container.add(row);
            container.add(buttonRow);
        }
        System.out.println(counter);
        loop:
        while (counter != Product.getAllProduct().size()) {
            row = new JPanel();
            row.setLayout(new FlowLayout());

            JPanel buttonRow = new JPanel();
            buttonRow.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 10));

            for (int i = 0; i < 4; i++) {
                counter += 1;
                Image firstImg = new ImageIcon("src\\coverImgs\\" + Product.getAllProduct().get(counter-1).getCoverImg()).getImage();
                Image coverImg = firstImg.getScaledInstance(220, 320, Image.SCALE_DEFAULT);
                JButton checkButton = new JButton("Check");
                checkButton.setFocusable(false);
                checkButton.setPreferredSize(new Dimension(175, 25));
                checkButton.setBackground(Color.WHITE);
                checkButton.setBorder(BorderFactory.createEtchedBorder());
                buttons.add(checkButton);
                checkButton.addActionListener(selectProduct);

                coverLabel = new JLabel();
                coverLabel.setIcon(new ImageIcon(coverImg));

                coverLabel.setForeground(Color.BLACK);
                coverLabel.setBackground(Color.black);

                row.add(coverLabel);
                buttonRow.add(checkButton);

                if (counter == Product.getAllProduct().size()) {
                    container.add(row);
                    container.add(buttonRow);
                    break loop;
                }
            }

        }

        JScrollPane pane = new JScrollPane(container);
        pane.getVerticalScrollBar().setUnitIncrement(25);

        frame.add(pane);
        frame.setJMenuBar(menuBar);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void getAllProduct() {
        System.out.println("Select all book");

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
                Product newProduct = new Product(result.getInt(1), result.getString(2), result.getInt(3), result.getString(4), result.getBoolean(5), result.getDate(6), result.getString(7), result.getString(10), result.getInt(11));
                try {
                    newProduct.setAvailabDate(result.getDate(8));
                } catch (Exception e) {
                }
            }

        } catch (Exception e) {
            System.out.println("hiba");
        }
    }

    ActionListener selectProduct = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Product.selectedProduct = Product.getAllProduct().get(buttons.indexOf(e.getSource()));
            frame.dispose();
            new ProductPage();
        }
    };

    MenuListener navigate = new MenuListener() {
        @Override
        public void menuSelected(MenuEvent e) {
            if (e.getSource() == history) {
                new HistoryPage();
            } else if (e.getSource() == search) {
                new SearchPage();
            } else if (e.getSource() == mainPage) {
                new MainPage();
            } else if (e.getSource() == addProduct) {
                new AddProduct();
            } else if(e.getSource() == quit){
              new LoginPage();  
            }

            frame.dispose();
        }

        @Override
        public void menuDeselected(MenuEvent e) {
        }

        @Override
        public void menuCanceled(MenuEvent e) {
        }
    };
}
