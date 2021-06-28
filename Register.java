public class Register extends Person
{
    private String password, color;
    private double balance;

    public Register(String id, String name, String gender, String password, String color)
    {
        super(id, name, gender);
        this.password = password;
        this.color = color;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setBalance(double bal) { balance += bal; }
    public double getBalance() { return balance; }
}
