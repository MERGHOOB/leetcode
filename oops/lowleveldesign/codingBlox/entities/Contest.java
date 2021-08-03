package lowleveldesign.codingBlox.entities;

import lowleveldesign.codingBlox.enums.Status;
import lowleveldesign.codingBlox.enums.Difficulty;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Contest {
    String contestName;
    List<Question> questions = new ArrayList<>();
    Difficulty level;
    Status status;
    private User owner;
    private BigInteger id;

    public String getContestName() {
        return contestName;
    }

    public void setContestName(String contestName) {
        this.contestName = contestName;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public Difficulty getLevel() {
        return level;
    }

    public void setLevel(Difficulty level) {
        this.level = level;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setOwner(User user) {
        this.owner = user;
    }

    @Override
    public String toString() {
        return "Contest{" +
                "ContestID='" + id + '\'' +
                "contestName='" + contestName + '\'' +
                ", questions=" + questions +
                ", level=" + level +
                ", status=" + status +
                ", owner=" + owner +
                '}';
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public BigInteger getId() {
        return id;
    }

    public User getOwner() {
        return owner;
    }
}
