package com.margub.splitwise.entities;

import java.util.ArrayList;
import java.util.List;

public class BalanceDetails {


    List<BalanceRecord> balanceList = new ArrayList<>();

    public void addDetail(Long landerId, Long borrowerId, double money) {

        BalanceRecord balance = new BalanceRecord();
        balance.setLander(landerId);
        balance.setBorrower(borrowerId);
        balance.setMoney(money);
        balanceList.add(balance);
    }

    public void show() {
        if (balanceList.isEmpty()) {
            System.out.println("No balances");
            return;
        }
        balanceList.forEach(System.out::println);
    }
}
