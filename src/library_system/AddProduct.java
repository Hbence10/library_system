package library_system;

import java.util.*;
import java.awt.*;
import java.io.File;
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
    JPanel formPanel;

    public AddProduct() {
        frame = new JFrame();
        frame.setSize(1280, 720);
        frame.setLayout(null);
        frame.setTitle("Library System - Add Product");
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

        
        try {
            frame.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("src\\bg2.jpg")))));
        } catch (Exception e) {
        }
        
        formPanel = new JPanel();
        formPanel.setBounds(50, 0, 780, 720);
        formPanel.setLayout(null);
        JTextField title = new JTextField();
        
        JButton addButton = new JButton("Add Product!");
        addButton.setBounds(330,575,120,25);
        
        for(int i = 0; i<4; i++){
            JTextArea input = new JTextArea();
            input.setBounds(20, 20 + (i*45), 120, 20);
            formPanel.add(input);
        }
        
        formPanel.add(addButton);
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
