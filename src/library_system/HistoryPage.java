package library_system;

import java.util.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public class HistoryPage {

    JFrame frame;
    JMenu mainPage;
    JMenu search;
    JMenu history;
    JMenu addProduct;
    JPanel detailsPanel;

    public HistoryPage() {
        getFullHistory();
        frame = new JFrame();
        frame.setSize(1280, 720);
        frame.setLayout(null);
        frame.setTitle("Library System - " + Account.logedAcc.getUsername().replace("\"", "") + "'s Histroy");
        frame.setResizable(false);
        frame.setIconImage(new ImageIcon("src\\icon.jpg").getImage());

        JMenuBar menuBar = new JMenuBar();
        mainPage = new JMenu("Main Page");
        search = new JMenu("Search");
        history = new JMenu("My History");

        mainPage.addMenuListener(navigate);
        search.addMenuListener(navigate);
        history.addMenuListener(navigate);

        menuBar.add(mainPage);
        menuBar.add(search);
        menuBar.add(history);

        if (Account.logedAcc.getAdmin()) {
            addProduct = new JMenu("Add Product");
            menuBar.add(addProduct);
            addProduct.addMenuListener(navigate);
        }

        detailsPanel = new JPanel();
        detailsPanel.setBounds(40, 10, 1200, 700);

        JLabel username = new JLabel(Account.logedAcc.getUsername().replace("\"", ""));
        username.setFont(new Font("Mv Boli", Font.PLAIN, 45));
        detailsPanel.add(username);
        
//        for(int i = 0; i<History.fullHistory.size(); i++){
//            JLabel title = new JLabel(Product.getAllProduct().get(History.fullHistory.get(i).getBookId() -1).getTitle());
//            detailsPanel.add(title);
//        }

        for (History i : History.fullHistory) {
            System.out.println(i);
        }
        
        frame.add(detailsPanel);
        frame.setJMenuBar(menuBar);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

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

    public void getFullHistory() {
        String url = "jdbc:mysql://localhost:3306/library";
        String query = "SELECT * FROM library.lendhistory WHERE userId = " + Account.logedAcc.getUserId() + ";";

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
                History newHistory = new History(result.getInt(5), result.getDate(3));
                try {
                    newHistory.setEndDate(result.getDate(4));
                    newHistory.setOngoing(true);
                } catch (Exception e) {
                }
            }

        } catch (Exception e) {
            System.out.println("hiba");
        }
    }
}
