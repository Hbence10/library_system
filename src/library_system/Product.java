package library_system;
import java.util.* ;

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
    private int lendBy;
    
    private static ArrayList<Product> allProduct = new ArrayList<Product>();
    private static ArrayList<Product> searchedProduct = new ArrayList<Product>();

    public static Product selectedProduct;
    
    public Product(int bookId, String title, int ISBN, String description, boolean available, Date relaeseDate, String coverImg, String author, int lendBy) {
        this.bookId = bookId;
        this.title = title;
        this.ISBN = ISBN;
        this.description = description;
        this.available = available;
        this.relaeseDate = relaeseDate;
        this.coverImg = coverImg;
        this.author = author;
        this.lendBy = lendBy;
        
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

    public int getLendBy() {
        return lendBy;
    }

    public static ArrayList<Product> getAllProduct() {
        return allProduct;
    }

    public String getAuthor() {
        return author;
    }

    public Date getAvailabDate() {
        return availabDate;
    }

    public int getBookId() {
        return bookId;
    }

    public String getDescription() {
        return description;
    }

    public int getISBN() {
        return ISBN;
    }

    public Date getLendDate() {
        return lendDate;
    }

    public Date getRelaeseDate() {
        return relaeseDate;
    }

    public static Product getSelectedProduct() {
        return selectedProduct;
    }
    
    public boolean getAvailable(){
        return available;
    }

    public static void setAllProduct(ArrayList<Product> allProduct) {
        Product.allProduct = allProduct;
    }

    public void setAvailabDate(Date availabDate) {
        this.availabDate = availabDate;
    }

    public static void setSelectedProduct(Product selectedProduct) {
        Product.selectedProduct = selectedProduct;
    }

    public static ArrayList<Product> getSearchedProduct() {
        return searchedProduct;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    public void setLendBy(int lendBy) {
        this.lendBy = lendBy;
    }

    public void setLendDate(Date lendDate) {
        this.lendDate = lendDate;
    }

    public void setRelaeseDate(Date relaeseDate) {
        this.relaeseDate = relaeseDate;
    }

    public static void setSearchedProduct(ArrayList<Product> searchedProduct) {
        Product.searchedProduct = searchedProduct;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    
}