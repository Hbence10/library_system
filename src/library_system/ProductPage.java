package library_system;

import java.util.*;
import java.awt.*;
import javax.swing.*;

public class ProductPage {

    JFrame frame;

    public ProductPage() {
        frame = new JFrame();
        frame.setSize(1280, 720);
        frame.setLayout(null);
        frame.setTitle("Library System -" + Product.selectedProduct.getTitle());
        frame.setResizable(false);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}
