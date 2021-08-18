package com.margub.splitwise.services;

import com.margub.splitwise.entities.BalanceDetails;
import com.margub.splitwise.entities.BorrowerEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BalanceService {

    Map<Long, List<BorrowerEntity>> owesMap = new HashMap<>();


    public BalanceDetails getBalanceDetails(Long id) {

        BalanceDetails balanceDetails = new BalanceDetails();


        owesMap.getOrDefault(id, new ArrayList<>())
                .stream()
                .filter(borrowerEntity -> borrowerEntity.getBorrowedMoney() > 0)
                .forEach(borrowerEntity -> balanceDetails.addDetail(id, borrowerEntity.getUserId(), borrowerEntity.getBorrowedMoney()));

        owesMap.keySet().stream()
                .filter(key -> !key.equals(id))
                .forEach(key -> {
                    List<BorrowerEntity> borrowerEntityList = owesMap.get(key);
                    borrowerEntityList.stream()
                            .filter(borrowerEntity -> borrowerEntity.getUserId().equals(id))
                            .filter(borrowerEntity -> borrowerEntity.getBorrowedMoney() > 0)
                            .forEach(borrowerEntity -> balanceDetails.addDetail(key, id, borrowerEntity.getBorrowedMoney()));
                });

        return balanceDetails;
    }

    public BalanceDetails getBalanceDetails() {
        BalanceDetails balanceDetails = new BalanceDetails();

        owesMap.forEach((key, value) -> value.stream()
                .filter(borrowerEntity -> borrowerEntity.getBorrowedMoney() > 0)
                .forEach(borrowerEntity -> balanceDetails.addDetail(key, borrowerEntity.getUserId(), borrowerEntity.getBorrowedMoney())));
        return balanceDetails;
    }

    public void addBalance(Long landerId, Long borrowerId, double money) {
        owesMap.putIfAbsent(landerId, new ArrayList<>());

        List<BorrowerEntity> borrowerEntities = owesMap.get(landerId);
        for (BorrowerEntity borrowerEntity : borrowerEntities) {
            if (borrowerEntity.getUserId().equals(borrowerId)) {
                borrowerEntity.setBorrowedMoney(borrowerEntity.getBorrowedMoney() + money);
                return;
            }
        }

        BorrowerEntity borrowerEntity = new BorrowerEntity();
        borrowerEntity.setUserId(borrowerId);
        borrowerEntity.setBorrowedMoney(money);
        borrowerEntities.add(borrowerEntity);
    }
}
