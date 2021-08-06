package digital_wallet.services;

import digital_wallet.enums.Offer;
import digital_wallet.transactionallogs.LogService;

import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;

public class OfferService {
    // log db
    // user db
    public static void fire(Offer offer, WalletService walletService) {


        // to get transactional details : need log service
        // to get amount details of users: walletService
        int k = 3;
        List<String> topUsers = LogService.getInstance().getTopUsers(3);
        if (topUsers.size() != k) {
            topUsers.sort(new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    if (walletService.getAmount(o1) == walletService.getAmount(o2)) {
                        return Long.compare(walletService.getCreationStamp(o1), walletService.getCreationStamp(o2));
                    }
                    return Double.compare(walletService.getAmount(o1), walletService.getAmount(o2));
                }
            });

        }

        if(topUsers.size() >=1) {
            String user = topUsers.get(0);
            int amountForFirstUser = 10;
            walletService.updateAmount(user, amountForFirstUser);
            LogService.getInstance().log(user, List.of(user, "Offer2", String.valueOf(amountForFirstUser)));
        }
        walletService.updateAmount(topUsers.get(1), 5);
        walletService.updateAmount(topUsers.get(2), 2);

    }
}
