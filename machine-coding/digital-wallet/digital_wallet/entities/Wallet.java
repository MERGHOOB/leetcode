package digital_wallet.entities;

public class Wallet {
    Long id;
    double amount;
    double fdAmount;
    String userName; // this must be unique

    public Wallet() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getFdAmount() {
        return fdAmount;
    }

    public void setFdAmount(double fdAmount) {
        this.fdAmount = fdAmount;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
