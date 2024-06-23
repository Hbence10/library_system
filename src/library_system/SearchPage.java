package library_system;

import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    JPanel buttonRow;

    public SearchPage() {
        frame = new JFrame();
        frame.setSize(1280, 720);
        frame.setLayout(null);
        frame.setTitle("Library System - Search page");
        frame.setResizable(false);
        frame.setIconImage(new ImageIcon("src\\mainIcon.jpg").getImage());

        row = new JPanel();
        row.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        row.setBounds(20, 200, 1280, 500);
        row.setOpaque(false);

        buttonRow = new JPanel();
        buttonRow.setLayout(new FlowLayout(FlowLayout.CENTER, 60, 10));
        buttonRow.setBounds(20, 550, 1280, 100);
        buttonRow.setOpaque(false);

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

        try {
            frame.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("src\\bg.jpg")))));
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
    ArrayList<Integer> matchedProducts = new ArrayList<Integer>();
    ArrayList<JButton> buttons = new ArrayList<JButton>();
    KeyListener searchEvent = new KeyListener() {
        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            counter += 1;

            if (searchBar.getText().length() < 3) {
                matchedProducts = new ArrayList<Integer>();
                buttons = new ArrayList<JButton>();
                row.removeAll();
                row.revalidate();
                row.repaint();
                buttonRow.removeAll();
                buttonRow.revalidate();
                buttonRow.repaint();
            }

            if (searchBar.getText().trim().length() >= 3) {
                row.removeAll();
                row.revalidate();
                row.repaint();
                buttonRow.removeAll();
                buttonRow.revalidate();
                buttonRow.repaint();
                matchedProducts = new ArrayList<Integer>();
                buttons = new ArrayList<JButton>();
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
                        JButton button = new JButton("Check");
                        buttons.add(button);
                        button.addActionListener(checkProduct);
                    }

                }

            }

            ArrayList<JLabel> coverImgList = new ArrayList<JLabel>();

            for (int i : matchedProducts) {
                Image firstImg = new ImageIcon("src\\coverImgs\\" + Product.getAllProduct().get(i).getCoverImg()).getImage();
                Image coverImg = firstImg.getScaledInstance(220, 320, Image.SCALE_DEFAULT);
                JLabel coverLabel = new JLabel();
                coverLabel.setIcon(new ImageIcon(coverImg));
                coverImgList.add(coverLabel);
                coverLabel.setForeground(Color.BLACK);
                coverLabel.setBackground(Color.black);
                row.add(coverLabel);
                row.repaint();
            }
            
            for(JButton i : buttons){
                i.setFocusable(false);
                i.setPreferredSize(new Dimension(175,25));
                i.setBackground(Color.WHITE);
                i.setBorder(BorderFactory.createEtchedBorder());
                buttonRow.add(i);
                buttonRow.repaint();
            }

            frame.add(row);
            frame.add(buttonRow);
            frame.setVisible(true);
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    };

    ActionListener checkProduct = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Product.setSelectedProduct(Product.getAllProduct().get(matchedProducts.get(buttons.indexOf(e.getSource()))));
            frame.dispose();
            new ProductPage(false);
        }
    };
}
