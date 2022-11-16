public class Login {
    private String username;
    private String password;
    private int customerid;

    public Login(String username, String password, int customerid){
        this.username = username;
        this.password = password;
        this.customerid = customerid;
    }
    public int getCustomerid() {
        return customerid;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
}
