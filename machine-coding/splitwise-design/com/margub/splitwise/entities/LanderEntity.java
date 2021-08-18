package com.margub.splitwise.entities;

public class LanderEntity {
    private User user;
    private int landedMoney;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getLandedMoney() {
        return landedMoney;
    }

    public void setLandedMoney(int landedMoney) {
        this.landedMoney = landedMoney;
    }
}
