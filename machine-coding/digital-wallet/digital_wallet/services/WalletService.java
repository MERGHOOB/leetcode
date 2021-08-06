package digital_wallet.services;

import digital_wallet.entities.Wallet;
import digital_wallet.exceptions.AmountNotValidException;
import digital_wallet.exceptions.UserValidationException;
import digital_wallet.transactionallogs.LogService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WalletService {


    Map<String, Wallet> walletDB = new HashMap<>();
    private static Long index = 0L;

    public Wallet createWallet(String user, Double amount) throws UserValidationException {
        if (walletDB.containsKey(user)) {
            throw new UserValidationException("User already exists!");
        }
        Wallet wallet = new Wallet();
        wallet.setUserName(user);
        wallet.setAmount(amount);
        wallet.setFdAmount(0L);
        synchronized (index) {
            index = index + 1;
            wallet.setId(index);
        }
        walletDB.put(user, wallet);
        return wallet;
    }

    public void transfer(String sender, String receiver, Double amount) throws AmountNotValidException {
        Wallet senderWallet = walletDB.get(sender);
        Wallet wallet = walletDB.get(receiver);

        synchronized (senderWallet) {
            synchronized (wallet) {
                if (senderWallet.getAmount() > amount) {
                    wallet.setAmount(wallet.getAmount() + amount);
                    senderWallet.setAmount(senderWallet.getAmount() - amount);
                } else if (senderWallet.getFdAmount() + senderWallet.getAmount() > amount) {
                    senderWallet.setAmount(senderWallet.getFdAmount() + senderWallet.getAmount() - amount);
                    senderWallet.setFdAmount(0L);
                    wallet.setAmount(wallet.getAmount() + amount);
                } else {
                    throw new AmountNotValidException("There is not enough amount in your account");
                }

                if (senderWallet.getAmount() == wallet.getAmount()) {
                    senderWallet.setAmount(senderWallet.getAmount() + 10);
                    wallet.setAmount(wallet.getAmount() + 10);
                    LogService.getInstance().log(sender, List.of("Offer1", "Credit", "10"));
                    LogService.getInstance().log(receiver, List.of("Offer1", "Credit", "10"));
                }
            }
        }
    }

    public void statement(String user) {
        LogService.getInstance().statement(user);
    }

    public double getAmount(String user) {
        return walletDB.get(user).getAmount();
    }

    public long getCreationStamp(String user) {
        return walletDB.get(user).getId();
    }

    public void updateAmount(String user, double amount) {
        Wallet wallet = walletDB.get(user);
        synchronized (wallet) {
            wallet.setAmount(wallet.getAmount() + amount);
        }
    }
}
