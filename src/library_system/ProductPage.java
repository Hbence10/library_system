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

public class ProductPage {

    JFrame frame;
    JPanel coverPanel;
    JLabel coverImg;
    JPanel detailsPanel;
    JMenu mainPage;
    JMenu search;
    JMenu history;
    JMenu addProduct;

    public ProductPage() {
        frame = new JFrame();
        frame.setSize(1280, 720);
        frame.setLayout(null);
        frame.setTitle("Library System - " + Product.selectedProduct.getTitle());
        frame.setResizable(false);
        frame.setIconImage(new ImageIcon("src\\icon.jpg").getImage());

        try {
            frame.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("src\\bg2.jpg")))));
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

        coverImg = new JLabel();
        coverImg.setIcon(new ImageIcon("src\\coverImgs\\" + Product.selectedProduct.getCoverImg()));
        coverImg.setBackground(Color.red);

        coverPanel = new JPanel();
        coverPanel.setBounds(45, 20, 450, 600);
        coverPanel.setOpaque(false);
        coverPanel.add(coverImg);

        detailsPanel = new JPanel();
        detailsPanel.setBounds(590, 20, 610, 600);
//        detailsPanel.setBackground(Color.red);
//    detailsPanel.setOpaque(false);
        detailsPanel.setLayout(null);

        JLabel title = new JLabel(Product.getSelectedProduct().getTitle());
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setBounds(0, 0, 610, 75);
        title.setFont(new Font("Mv Boli", Font.PLAIN, 45));

        JLabel author = new JLabel("By: " + Product.getSelectedProduct().getAuthor());
        author.setHorizontalAlignment(JLabel.CENTER);
        author.setBounds(0, 40, 610, 75);

        JLabel release = new JLabel("Released : " + Product.getSelectedProduct().getRelaeseDate());
        release.setHorizontalAlignment(JLabel.CENTER);
        release.setBounds(0, 58, 610, 75);

        JLabel ISBN = new JLabel("ISBN: " + Product.getSelectedProduct().getISBN());
        ISBN.setHorizontalAlignment(JLabel.CENTER);
        ISBN.setBounds(0, 75, 610, 75);

        JTextArea description = new JTextArea(Product.getSelectedProduct().getDescription());
        description.setBounds(20, 155, 570, 350);
        description.setLineWrap(true);
        description.setOpaque(false);

        if (Account.logedAcc.getAdmin()) {
            description.setEditable(true);
        } else {
            description.setEditable(false);
        }

        JLabel available = new JLabel("Status: " + (Product.getSelectedProduct().getAvailable() ? "Available" : "Taken"));
        available.setBounds(20, 375, 100, 25);

        JButton lendButton = new JButton("Lend");
        lendButton.setBounds(490, 375, 100, 25);
        lendButton.addActionListener(lend);

        JLabel availableDate = new JLabel("It will available at: " + Product.getSelectedProduct().getAvailabDate());
        availableDate.setBounds(20, 395, 610, 20);

        if (Product.getSelectedProduct().getAvailable()) {
            available.setForeground(Color.green);

        } else {
            available.setForeground(Color.red);
            lendButton.setEnabled(false);
            detailsPanel.add(availableDate);
        }
        detailsPanel.add(lendButton);
        detailsPanel.add(title);
        detailsPanel.add(author);
        detailsPanel.add(release);
        detailsPanel.add(ISBN);
        detailsPanel.add(description);
        detailsPanel.add(available);

        frame.setJMenuBar(menuBar);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(coverPanel);
        frame.add(detailsPanel);
        frame.setVisible(true);
    }

    ActionListener lend = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println(LocalDate.now());

            Calendar c = Calendar.getInstance();
            c.add(Calendar.DATE, 30);
            Date endDate = c.getTime();

            System.out.println(endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());

            System.out.println(Account.logedAcc.getUsername() + "want to lend " + Product.getSelectedProduct().getTitle());
            ArrayList<String> querys = new ArrayList<String>(Arrays.asList(
                    "INSERT INTO library.lendhistory (`userId`, `startDate`, `bookTitle`) VALUES (" + Account.logedAcc.getUserId() + ", \"" + LocalDate.now() + "\"," + Product.getSelectedProduct().getBookId()+ ");",
                    "UPDATE library.book SET available = false , lendDate = \"" + LocalDate.now() + "\",availableDate =  \"" + endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate() + "\" WHERE bookId = " + Product.selectedProduct.getBookId() + ";",
                    "INSERT INTO library.lendtracker (`bookId`, `userId`, `startDate`) VALUES (" + Product.getSelectedProduct().getBookId() + ", " + Account.logedAcc.getUserId() + ", \"" + LocalDate.now() + "\");"
            ));

            System.out.println("UPDATE library.book SET available = false , lendDate = \"" + LocalDate.now() + "\",availableDate =  \"" + endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate() + "\" WHERE bookId = " + Product.selectedProduct.getBookId() + ";");
            System.out.println("INSERT INTO library.lendhistory (`userId`, `startDate`, `bookTitle`) VALUES (" + Account.logedAcc.getUserId() + ", \"" + LocalDate.now() + "\", \"" + Product.getSelectedProduct().getTitle() + "\"" + ");");
            sendLend(querys);

            frame.dispose();
            new MainPage();
        }
    };

    public void sendLend(ArrayList<String> querys) {
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
    
    MenuListener navigate = new MenuListener() {
        @Override
        public void menuSelected(MenuEvent e) {
            if(e.getSource() == history){
                new HistoryPage();
            } else if (e.getSource() == search){
                new SearchPage();
            } else if(e.getSource() == mainPage){
                new MainPage();
            } else if(e.getSource() == addProduct){
                new AddProduct();
            }
            
            frame.dispose();
        }

        @Override
        public void menuDeselected(MenuEvent e) {}

        @Override
        public void menuCanceled(MenuEvent e) { }
    };
}
