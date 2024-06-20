package library_system;

import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import javax.imageio.ImageIO;
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
    ArrayList<JButton> sendButtons = new ArrayList<JButton>();
    ArrayList<JButton> checkButtons = new ArrayList<JButton>();

    public HistoryPage() {
        History.setFullHistory(new ArrayList<History>());
        getFullHistory();
        frame = new JFrame();
        frame.setSize(1280, 720);
        frame.setLayout(null);
        frame.setTitle("Library System - " + Account.logedAcc.getUsername().replace("\"", "") + "'s Histroy");
        frame.setResizable(false);
        frame.setIconImage(new ImageIcon("src\\icon.jpg").getImage());

        try {
            frame.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("src\\bg8.jpg")))));
        } catch (Exception e) {
        }

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
        detailsPanel.setBounds(150, 75, 980, 570);
        detailsPanel.setLayout(null);
//        detailsPanel.setOpaque(false);

        JLabel username = new JLabel(Account.logedAcc.getUsername().replace("\"", "") + "'s history");
        username.setFont(new Font("Mv Boli", Font.PLAIN, 45));
        username.setBounds(0, 0, 980, 60);
        username.setHorizontalAlignment(JLabel.CENTER);

        detailsPanel.add(username);

        for (int i = 0; i < History.fullHistory.size(); i++) {
            JButton endButton = new JButton("Send");
            endButton.setBounds(495, 110 + (i * 30), 75, 20);
            endButton.addActionListener(sendProduct);
            sendButtons.add(endButton);

            JLabel title = new JLabel(Product.getAllProduct().get(History.fullHistory.get(i).getBookId() - 1).getTitle());
            title.setBounds(20, 95 + (i * 30), 120, 50);

            JLabel startDate = new JLabel(History.fullHistory.get(i).getStartDate().toString());
            startDate.setBounds(265, 95 + (i * 30), 100, 50);

            JLabel endDate = new JLabel();
            try {
                endDate.setText(History.fullHistory.get(i).getEndDate().toString());
                endButton.setEnabled(false);
                History.fullHistory.get(i).setOngoing(false);
            } catch (Exception e) {
                endDate.setText("-");
                History.fullHistory.get(i).setOngoing(true);
            }

            JLabel status = new JLabel(History.fullHistory.get(i).getOngoing() ? "Active" : "Ended");
            status.setBounds(155, 95 + (i * 30), 100, 50);

            endDate.setBounds(375, 95 + (i * 30), 100, 50);

            JButton checkButton = new JButton("Check");
            checkButton.setBounds(580, 110 + (i * 30), 75, 20);
            checkButtons.add(checkButton);
            checkButton.addActionListener(checkProduct);

            detailsPanel.add(title);
            detailsPanel.add(status);
            detailsPanel.add(startDate);
            detailsPanel.add(endDate);
            detailsPanel.add(endButton);
            detailsPanel.add(checkButton);
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
                    newHistory.setOngoing(false);
                } catch (Exception e) {
                    newHistory.setOngoing(true);
                }
            }

        } catch (Exception e) {
            System.out.println("hiba");
        }
    }

    ActionListener checkProduct = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int index = checkButtons.indexOf(e.getSource());
            System.out.println(Product.getAllProduct().get(History.fullHistory.get(index).getBookId() - 1));
            Product.setSelectedProduct(Product.getAllProduct().get(History.fullHistory.get(index).getBookId() - 1));

            frame.dispose();
            new ProductPage();
            System.out.println();
        }
    };

    ActionListener sendProduct = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int index = sendButtons.indexOf(e.getSource());

            Calendar c = Calendar.getInstance();
            c.add(Calendar.DATE, 30);
            Date endDate = c.getTime();
            LocalDate locald = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            Product selected = Product.getAllProduct().get(History.fullHistory.get(index).getBookId() - 1);
            ArrayList<String> querys = new ArrayList<String>(Arrays.asList(
                    "UPDATE library.book SET available = true, availableDate = NULL, lendDate = NULL WHERE bookId = " + selected.getBookId() + " ;",
                    "UPDATE library.lendhistory SET endDate = " + "\"" + LocalDate.now() + "\" WHERE bookTitle = " + selected.getBookId() + ";",
                    "UPDATE library.lendtracker SET finish = true, endDate = \"" + locald + "\" WHERE bookId= " + selected.getBookId() + ";"
            ));
            for (String i : querys) {
                System.out.println(i);
            }

            sendUpdate(querys);
            frame.dispose();
            new HistoryPage();
        }
    };

    public void sendUpdate(ArrayList<String> querys) {
        String url = "jdbc:mysql://localhost:3306/library";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            System.out.println("Hiba");
        }

        try {
            for (int i = 0; i < querys.size(); i++) {
                System.out.println(querys.get(i));
                Connection con = DriverManager.getConnection(url, "root", "ASDasd123");
                Statement statement = con.createStatement();
                statement.executeUpdate(querys.get(i));
            }

        } catch (Exception e) {
            System.out.println("hiba");
        }
    }
}
