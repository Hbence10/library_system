package library_system;

import java.util.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public class SearchPage {

    JFrame frame;
    JMenu mainPage;
    JMenu search;
    JMenu history;
    JMenu addProduct;
    JMenu quit;
    JFormattedTextField searchBar;
    JPanel containerPanel;
    JPanel row;

    public SearchPage() {
        frame = new JFrame();
        frame.setSize(1280, 720);
        frame.setLayout(null);
        frame.setTitle("Library System - Search page");
        frame.setResizable(false);
        frame.setIconImage(new ImageIcon("src\\icon.jpg").getImage());

        row = new JPanel();
        row.setLayout(new FlowLayout());
        row.setBounds(20, 200, 1280, 500);
        row.setOpaque(false);

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
            frame.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("src\\bg8.jpg")))));
        } catch (Exception e) {
        }

        containerPanel = new JPanel();
        containerPanel.setBounds(0, 0, 1280, 120);
        containerPanel.setBackground(new Color(0, 0, 0, 95));
        containerPanel.setLayout(null);

        searchBar = new JFormattedTextField();
        searchBar.setBounds(500, 40, 280, 25);
        searchBar.addKeyListener(searchEvent);

        containerPanel.add(searchBar);

        frame.add(containerPanel);
        frame.setJMenuBar(menuBar);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    int counter = 0;
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
            } else if (e.getSource() == quit) {
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
    HashSet<Integer> matchedProducts = new HashSet<Integer>();
    KeyListener searchEvent = new KeyListener() {
        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            counter += 1;

            if (searchBar.getText().length() < 3) {
                matchedProducts = new HashSet<Integer>();
                System.out.println("kevesebb matched: " + matchedProducts);
                row.removeAll();
                row.revalidate();
                row.repaint();
            }

            if (searchBar.getText().trim().length() >= 3) {
                row.removeAll();
                row.revalidate();
                row.repaint();
                matchedProducts = new HashSet<Integer>();
                for (int i = 0; i < Product.getAllProduct().size(); i++) {
                    String originTitle = "";
                    String originAuthor = "";
                    String originISBN = "";

                    try {
                        originTitle = Product.getAllProduct().get(i).getTitle().substring(0, searchBar.getText().length());
                    } catch (Exception d) {
                    }
                    try {
                        originAuthor = Product.getAllProduct().get(i).getAuthor().substring(0, searchBar.getText().length());
                    } catch (Exception d) {
                    }
                    try {
                        originISBN = String.valueOf(Product.getAllProduct().get(i).getISBN()).substring(0, searchBar.getText().length());
                    } catch (Exception d) {
                    }

                    if (originTitle.toLowerCase().equals(searchBar.getText().toLowerCase().trim()) || originAuthor.toLowerCase().equals(searchBar.getText().toLowerCase().trim()) || originISBN.toLowerCase().equals(searchBar.getText().toLowerCase().trim())) {
                        matchedProducts.add(i);
                    }

                }

            }

            ArrayList<JLabel> testList = new ArrayList<JLabel>();

            for (int i : matchedProducts) {
                Image img1 = new ImageIcon("src\\coverImgs\\" + Product.allProduct.get(i).getCoverImg()).getImage();
                Image test = img1.getScaledInstance(220, 320, Image.SCALE_DEFAULT);

                JLabel testLabel = new JLabel();
                testLabel.setIcon(new ImageIcon(test));
                testList.add(testLabel);
                testLabel.setForeground(Color.BLACK);
                testLabel.setBackground(Color.black);
                row.add(testLabel);
                row.repaint();
            }

            frame.add(row);
            frame.setVisible(true);
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    };
}
