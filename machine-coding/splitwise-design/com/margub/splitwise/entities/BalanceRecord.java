package com.margub.splitwise.entities;

public class BalanceRecord {

    private Long landerId;
    private Long borrowerId;
    private double money;

    public void setLander(Long landerId) {
        this.landerId = landerId;
    }

    public void setBorrower(Long borrowerId) {
        this.borrowerId = borrowerId;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "User" + borrowerId + " owes User" + landerId + ": " + money;
//        return "BalanceRecord{" +
//                "landerId=" + landerId +
//                ", borrowerId=" + borrowerId +
//                ", money=" + money +
//                '}';
    }
}
