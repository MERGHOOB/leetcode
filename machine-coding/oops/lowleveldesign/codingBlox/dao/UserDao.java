package lowleveldesign.codingBlox.dao;

import lowleveldesign.codingBlox.entities.User;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDao {

    public static Map<BigInteger, User> map = new HashMap<>();
    public static BigInteger index = BigInteger.ZERO;

    public void saveAll(List<User> users) {
        users.stream().forEach(user -> {
            save(user);
        });
    }

    public List<User> findAll() {
        return new ArrayList<>(map.values());
    }

    public void save(User user) {
        synchronized (index) {
            index = index.add(BigInteger.ONE);
            user.setId(index);
            map.put(index, user);
        }
    }

    public void update(User user) {
        map.put(user.getId(), user);
    }
}
