package atm.transaction;

public class Transfer extends Transaction {

    private int destinationAccountNumber;

    public int getDestinationAccountNumber() {
        return destinationAccountNumber;
    }
}
