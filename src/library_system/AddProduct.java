package library_system;

import java.util.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public class AddProduct {

    JFrame frame;
    JMenu mainPage;
    JMenu search;
    JMenu history;
    JMenu addProduct;

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
        
         frame.setJMenuBar(menuBar);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
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
