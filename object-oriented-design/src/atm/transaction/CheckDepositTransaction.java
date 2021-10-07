package atm.transaction;

public class CheckDepositTransaction extends Deposit{

    private String checkNumber;
    private String bankCode;

    public String getCheckNumber() {
        return checkNumber;
    }
}
