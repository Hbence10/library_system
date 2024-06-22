package library_system;

import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public class AddProduct {

    JFrame frame;
    JMenu mainPage;
    JMenu search;
    JMenu history;
    JMenu addProduct;
    JMenu quit;
    JPanel formPanel;
    JTextField title;
    JTextField author;
    JTextField ISBN;
    JTextField rDate;
    JButton coverImg;
    JTextArea description;
    File selectedFile;

    public AddProduct() {
        frame = new JFrame();
        frame.setSize(1280, 720);
        frame.setLayout(null);
        frame.setTitle("Library System - Add Product");
        frame.setResizable(false);
        frame.setIconImage(new ImageIcon("src\\mainIcon.jpg").getImage());
        frame.setForeground(Color.red);

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

        if (Account.logedAcc.getAdmin()) {
            addProduct = new JMenu("Add Product");
            menuBar.add(addProduct);
            addProduct.addMenuListener(navigate);
        }
        
        menuBar.add(quit);

        try {
            frame.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("src\\bg.jpg")))));
        } catch (Exception e) {
        }

        formPanel = new JPanel();
        formPanel.setBounds(210, 0, 860, 720);
        formPanel.setLayout(null);
        formPanel.setBackground(new Color(0, 0, 0, 95));

        JLabel mainTitle = new JLabel("Add Product");
        mainTitle.setFont(new Font("Serif", Font.PLAIN, 48));
        mainTitle.setBounds(0, 0, 860, 62);
        mainTitle.setHorizontalAlignment(JLabel.CENTER);
        mainTitle.setForeground(Color.white);

        JLabel titleText = new JLabel("Title:");
        titleText.setBounds(20, 90, 172, 40);
        titleText.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 34));
        titleText.setForeground(Color.white);

        JLabel authorText = new JLabel("Author:");
        authorText.setBounds(20, 155, 172, 40);
        authorText.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 34));
        authorText.setForeground(Color.white);

        JLabel ISBNText = new JLabel("ISBN:");
        ISBNText.setBounds(440, 90, 172, 40);
        ISBNText.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 34));
        ISBNText.setForeground(Color.white);

        JLabel rDateText = new JLabel("Relase Date:");
        rDateText.setBounds(440, 155, 200, 40);
        rDateText.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 34));
        rDateText.setForeground(Color.white);

        JLabel coverImgText = new JLabel("Cover Img:");
        coverImgText.setBounds(440, 220, 200, 40);
        coverImgText.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 34));
        coverImgText.setForeground(Color.white);

        JLabel descriptionText = new JLabel("Description:");
        descriptionText.setBounds(20, 290, 200, 40);
        descriptionText.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 34));
        descriptionText.setForeground(Color.white);

        title = new JTextField();
        title.setBounds(180, 95, 195, 25);

        author = new JTextField();
        author.setBounds(180, 160, 195, 25);

        JButton addButton = new JButton("Add to database");
        addButton.setBounds(410, 575, 175, 30);
        addButton.setFocusable(false);
        addButton.setBackground(new Color(255, 213, 5));
        addButton.setBorder(BorderFactory.createEtchedBorder());
        addButton.addActionListener(addProductToDatabase);

        JButton previewButton = new JButton("Preview the Page");
        previewButton.setBounds(600, 575, 175, 30);
        previewButton.setFocusable(false);
        previewButton.setBackground(new Color(255, 213, 5));
        previewButton.setBorder(BorderFactory.createEtchedBorder());

        rDate = new JTextField();
        rDate.setBounds(650, 160, 195, 25);

        ISBN = new JTextField();
        ISBN.setBounds(650, 95, 195, 25);

        coverImg = new JButton("Select");
        coverImg.setBounds(650, 225, 195, 30);
        coverImg.setFocusable(false);
        coverImg.setBackground(Color.white);
        coverImg.addActionListener(fileSelect);

        description = new JTextArea();
        description.setBounds(150, 350, 575, 125);

        formPanel.add(mainTitle);
        formPanel.add(titleText);
        formPanel.add(authorText);
        formPanel.add(ISBNText);
        formPanel.add(rDateText);
        formPanel.add(coverImgText);
        formPanel.add(descriptionText);
        formPanel.add(title);
        formPanel.add(author);
        formPanel.add(ISBN);
        formPanel.add(rDate);
        formPanel.add(coverImg);
        formPanel.add(description);
        formPanel.add(addButton);
        formPanel.add(previewButton);
        frame.add(formPanel);
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
            }else if(e.getSource() == quit){
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

    ActionListener fileSelect = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser chooser = new JFileChooser();

            int response = chooser.showOpenDialog(null); //select file to open

            if (response == JFileChooser.APPROVE_OPTION) {
                try {
                    selectedFile = chooser.getSelectedFile();
                    Path form = Paths.get(selectedFile.toURI());
                    Path to = Paths.get("src\\coverImgs\\" + selectedFile.getName());

                    Files.copy(form, to);
                } catch (IOException ex) {
                    Logger.getLogger(AddProduct.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
    };

    ActionListener addProductToDatabase = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            ArrayList<String> details = new ArrayList<>(Arrays.asList(title.getText(), ISBN.getText(), description.getText(), rDate.getText(), author.getText()));

            uploadToDatabase(details);
            frame.dispose();
            new MainPage();
        }
    };

    public void uploadToDatabase(ArrayList<String> details) {
        String url = "jdbc:mysql://localhost:3306/library";
        String query = "INSERT INTO `library`.`book`(`bookId`,`title`,`ISBN_Number`,`description`,`available`,`relaese_Date`,`coverImg`,`author`)VALUES ("
                + (Product.getAllProduct().size() + 1) + ",\"" + details.get(0) + "\"," + details.get(1) + ", \" " + details.get(2) + "\", 1, \"" + details.get(3) + "\",\"" + selectedFile.getName() + "\",\"" + details.get(4) + "\" );";

        System.out.println(query);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            System.out.println("Hiba");
        }

        try {

            Connection con = DriverManager.getConnection(url, "root", "ASDasd123");
            Statement statement = con.createStatement();
            statement.executeUpdate(query);

        } catch (Exception e) {
            System.out.println("hiba");
        }
    }
}
