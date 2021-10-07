package atm.transaction;

import atm.enums.TransactionStatus;

import java.util.Date;

public class Transaction {
    private int transactionId;
    private Date creationTime;
    private TransactionStatus transactionStatus;

    public boolean makeTransaction() {
        return true;
    }
}
