package digital_wallet_driver_class;

import digital_wallet.DigitalWalletSystem;

import java.util.Scanner;

public class Main {

    public static final String CREATE_WALLET = "CreateWallet";
    public static final String STATEMENT = "STATEMENT";
    public static final String OFFER_2 = "Offer2";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String cmd = scanner.next();
        if(cmd.equalsIgnoreCase(CREATE_WALLET)) {
            String user = scanner.next();
            String amount = scanner.next();
            DigitalWalletSystem.getInstance().createWallet(user, amount);
        }
        else if(cmd.equalsIgnoreCase("TransferMoney"))
        {
            String sender  = scanner.next();
            String receiver = scanner.next();
            String amount = scanner.next();
            DigitalWalletSystem.getInstance().transferMoney(sender, receiver, amount);
        }
        else if(cmd.equalsIgnoreCase(STATEMENT)) {
            String user = scanner.next();
            DigitalWalletSystem.getInstance().statement(user);
        }
        else if(cmd.equalsIgnoreCase(OFFER_2)) {
            DigitalWalletSystem.getInstance().fire(OFFER_2);
        }

    }
}
