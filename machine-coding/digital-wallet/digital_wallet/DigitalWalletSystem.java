package digital_wallet;

import digital_wallet.entities.Wallet;
import digital_wallet.enums.Offer;
import digital_wallet.exceptions.AmountNotValidException;
import digital_wallet.exceptions.UserValidationException;
import digital_wallet.services.OfferService;
import digital_wallet.services.WalletService;
import digital_wallet.transactionallogs.LogService;

import java.util.List;

public class DigitalWalletSystem {


    public static final DigitalWalletSystem digitalWalletSystem = new DigitalWalletSystem();
    public static final WalletService walletService = new WalletService();


    private DigitalWalletSystem() {

    }

    public static DigitalWalletSystem getInstance() {

        return digitalWalletSystem;
    }

    public void createWallet(String user, String amount) {
        try {
            walletService.createWallet(user, Double.valueOf(amount));
            LogService.getInstance().log(user, List.of(user, amount));
        } catch (UserValidationException e) {
            System.out.println(e.getMessage());
        }


    }

    public void transferMoney(String sender, String receiver, String amount) {
        try {
            walletService.transfer(sender, receiver, Double.valueOf(amount));
            LogService.getInstance().log(sender, List.of(sender, "Debit", amount));
            LogService.getInstance().log(receiver, List.of(receiver, "Credit", amount));
        } catch (AmountNotValidException e) {
            System.out.println(e.getMessage());
        }
    }

    public void statement(String user) {
        walletService.statement(user);
    }

    public void fire(String offer2) {
        Offer offer = Offer.valueOf(offer2);
        OfferService.fire(offer, walletService);
    }
}
