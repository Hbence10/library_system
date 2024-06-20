package library_system;
import java.util.* ;
import java.awt.*;
import javax.swing.*;

public class History {
    private int bookId;
    private Date startDate;
    private Date endDate = new Date();
    private boolean ongoing;
    
    public static ArrayList<History> fullHistory = new ArrayList<History>();

    public History(int bookId, Date startDate) {
        this.bookId = bookId;
        this.startDate = startDate;
        
        fullHistory.add(this);
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setOngoing(boolean ongoing) {
        this.ongoing = ongoing;
    }

    public int getBookId() {
        return bookId;
    }
    
  public boolean getOngoing(){
      return ongoing;
  }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public static void setFullHistory(ArrayList<History> fullHistory) {
        History.fullHistory = fullHistory;
    }
  
  
}