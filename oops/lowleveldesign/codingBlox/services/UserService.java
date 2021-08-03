package lowleveldesign.codingBlox.services;

import lowleveldesign.codingBlox.dao.UserDao;
import lowleveldesign.codingBlox.entities.Contest;
import lowleveldesign.codingBlox.entities.User;

import java.math.BigInteger;
import java.util.List;

public class UserService {

    public static final UserDao userDao = new UserDao();

    public User createUser(String user) {
        User user1 = new User();
        user1.setUserName(user);
        user1.setScore(BigInteger.valueOf(1500));
        userDao.save(user1);
        return user1;
    }

    public void attendContest(Contest contest, User user) {
        user.getContests().add(contest);
    }

    public boolean withdrawContest(Contest contest, String userName) {
        return true;
    }


    public void storeMultiUsers(List<User> users) {
        users.forEach(user -> {
            user.setScore(BigInteger.valueOf(1500));
        });
        userDao.saveAll(users);
    }

    public List<User> findAll() {
        return userDao.findAll();
    }


}
