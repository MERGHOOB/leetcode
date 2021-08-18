package com.margub.splitwise.services;


import com.margub.splitwise.entities.User;

import java.util.HashMap;
import java.util.Map;

public class UserService {
    private static Map<String, User> userDBBasedOnName = new HashMap<>();
    private static Map<Long, User> userDBBasedOnID = new HashMap<>();

    public long id = 0L;

    public User getUser(String userName) {
        return userDBBasedOnName.getOrDefault(userName, createIfNotExist(userName));
    }

    public User getUser(Long id) {
        return userDBBasedOnID.get(id);
    }

    public User createIfNotExist(String userName) {
        User user1 = userDBBasedOnName.get(userName);
        if (user1 != null) {
            return user1;
        }
        User user = new User();
        user.setName(userName);
        user.setId(++id);
        userDBBasedOnID.put(user.getId(), user);
        userDBBasedOnName.put(user.getName(), user);

        return user;
    }
}
