package com.margub.splitwise.entities;

import java.util.Objects;

public class BorrowerEntity {


    private Long userId;
    private double borrowedMoney;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public double getBorrowedMoney() {
        return borrowedMoney;
    }

    public void setBorrowedMoney(double borrowedMoney) {
        this.borrowedMoney = borrowedMoney;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BorrowerEntity that = (BorrowerEntity) o;
        return Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }
}
