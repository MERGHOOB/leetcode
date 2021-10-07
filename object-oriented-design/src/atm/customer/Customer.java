package atm.customer;

import atm.enums.Address;
import atm.enums.CustomerStatus;
import atm.transaction.Transaction;

public class Customer {
    String name;
    String email;
    String phone;
    Address address;
    CustomerStatus customerStatus;

    private Card card;
    private Account account;


    void makeTransaction(Transaction transaction) {

    }

    public Address getBillingAddress() {
        return address;
    }

}