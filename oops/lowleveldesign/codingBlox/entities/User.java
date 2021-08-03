package lowleveldesign.codingBlox.entities;

import lowleveldesign.codingBlox.builders.UserBuilder;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class User {
    String userName;
    private BigInteger id;

    @Override
    public String toString() {
        return "User{" +
                "userId='" + id + '\'' +
                "userName='" + userName + '\'' +
//                ", contests=" + contests +
                ", score=" + score +
                '}';
    }

    private List<Contest> contests = new ArrayList<>();
    BigInteger score;

    public static UserBuilder builder() {
        return new UserBuilder(new User());
    }

    public void setScore(BigInteger score) {
        this.score = score;
    }

    public void setUserName(String username) {
        this.userName = username;
    }

    public String getUserName() {
        return userName;
    }

    public List<Contest> getContests() {
        return contests;
    }

    public void setContests(List<Contest> contests) {
        this.contests = contests;
    }

    public BigInteger getScore() {
        return score;
    }

    public void setId(BigInteger index) {
        this.id = index;
    }

    public BigInteger getId() {
        return id;
    }
}
