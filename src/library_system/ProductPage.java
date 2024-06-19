package library_system;

import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;

public class ProductPage {

    JFrame frame;
    JPanel coverPanel;
    JLabel coverImg;
    JPanel detailsPanel;
    JMenu mainPage;

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
        JMenu search = new JMenu("Search");
        JMenu history = new JMenu("My History");

        menuBar.add(mainPage);
        menuBar.add(search);
        menuBar.add(history);

        if (Account.logedAcc.getAdmin()) {
            JMenu addProduct = new JMenu("Add Product");
            menuBar.add(addProduct);
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
        available.setBounds(20, 375, 100, 100);

        JButton lendButton = new JButton("Lend");
        lendButton.setBounds(200, 375, 100, 25);

        JLabel availableDate = new JLabel("It will available at: " + Product.getSelectedProduct().getAvailabDate());
        availableDate.setBounds(20, 395, 610, 100);

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

    ActionListener navigate = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println(e.getSource());
        }
    };

}
