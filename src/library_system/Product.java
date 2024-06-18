package library_system;
import java.util.* ;
import java.awt.*;
import javax.swing.*;

public class Product {
    private int bookId;
    private String title;
    private int ISBN;
    private String description;
    private boolean available;
    private Date relaeseDate;
    private String coverImg;
    private Date availabDate = new Date();
    private Date lendDate = new Date();
    private String author;
    
    public static ArrayList<Product> allProduct = new ArrayList<Product>();

    public static Product selectedProduct;
    
    public Product(int bookId, String title, int ISBN, String description, boolean available, Date relaeseDate, String coverImg, String author) {
        this.bookId = bookId;
        this.title = title;
        this.ISBN = ISBN;
        this.description = description;
        this.available = available;
        this.relaeseDate = relaeseDate;
        this.coverImg = coverImg;
        this.author = author;
        
        allProduct.add(this);
    }

    @Override
    public String toString() {
        return "Information about: " + title + "\n author: " + author + " relaeseDate: " + relaeseDate;
    }

    public String getCoverImg() {
        return coverImg;
    }

    public String getTitle() {
        return title;
    }
    
    
    
}