package library_system;


public class Account {
    private int userId;
    private String username; 
    private String password;
    private String email;
    private boolean admin;
    
   private static Account logedAcc;

    public Account(int userId, String username, String password, String email,boolean admin) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.admin = admin;
        
        logedAcc = this;
    }

    @Override
    public String toString() {
        return "username: " + username + " password: " + password + " a";
    }
    
    public boolean getAdmin(){
        return this.admin;
    }

    public String getUsername() {
        return username;
    }

    public int getUserId() {
        return userId;
    }

    public static void setLogedAcc(Account logedAcc) {
        Account.logedAcc = logedAcc;
    }

    public String getEmail() {
        return email;
    }

    public static Account getLogedAcc() {
        return logedAcc;
    }

    public String getPassword() {
        return password;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}