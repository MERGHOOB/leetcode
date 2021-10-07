package atm;

import atm.atmresources.CardReader;
import atm.atmresources.CashDeposit;
import atm.atmresources.CashDispenser;
import atm.atmresources.CheckDeposit;
import atm.atmresources.Keypad;
import atm.atmresources.Printer;
import atm.atmresources.Screen;
import atm.customer.Card;
import atm.customer.Customer;
import atm.enums.Address;
import atm.transaction.Transaction;

public class ATM {

    private int atmID;
    private Address location;

    private CardReader cardReader;
    private Keypad keypad;
    private Screen screen;
    private CashDispenser cashDispenser;
    private Printer printer;

    private CheckDeposit checkDeposit;
    private CashDeposit cashDeposit;

    public Customer authenticate(Card card) {
        return null;
    }

    public boolean makeTransaction(Customer customer, Transaction transaction) {
        return false;
    }


}
