package com.margub.splitwise;


import com.margub.splitwise.entities.User;
import com.margub.splitwise.services.BalanceService;
import com.margub.splitwise.services.UserService;

import java.util.List;

public class SplitWise {
    private static SplitWise instance = new SplitWise();
    private UserService userService = new UserService();
    private BalanceService balanceService = new BalanceService();

    private SplitWise() {

    }

    public static SplitWise getInstance() {
        return instance;
    }

    public void show() {
        balanceService.getBalanceDetails().show();
    }

    public void show(String userName) {
        User user = userService.getUser(userName);
        balanceService
                .getBalanceDetails(user.getId()).show();
    }

    public void addEqualExpense(String lander, List<String> borrowerNames, String money) {
        borrowerNames.forEach(userService::createIfNotExist);
        User user = userService.getUser(lander);
        double moneyD = Double.parseDouble(money) / 4;
        borrowerNames.stream()
                .filter(name -> !name.equals(lander))
                .forEach(name -> balanceService.addBalance(user.getId(), userService.getUser(name).getId(), moneyD));

    }

    public void addExactExpense(String lander, List<String> users, List<String> moneys) {
        User landerUser = userService.createIfNotExist(lander);


        for (int i = 0; i < users.size(); i++) {
            User borrower = userService.createIfNotExist(users.get(i));
            balanceService.addBalance(landerUser.getId(), borrower.getId(), Double.parseDouble(moneys.get(i)));
        }

    }

    public void addPercentageExpense(String lander, String money, List<String> users, List<String> percentages) {
        User landerUser = userService.createIfNotExist(lander);
        Double totalMoney = Double.parseDouble(money);

        for (int i = 0; i < users.size(); i++) {
            User borrower = userService.createIfNotExist(users.get(i));
            if (borrower.getId().equals(landerUser.getId())) {
                continue;
            }
            balanceService.addBalance(landerUser.getId(), borrower.getId(), getPercentageMoney(totalMoney, Integer.parseInt(percentages.get(i))));
        }
    }

    private double getPercentageMoney(Double totalMoney, int percentage) {
        return (totalMoney * percentage) / 100;
    }
}
