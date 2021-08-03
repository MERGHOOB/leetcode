package lowleveldesign.codingBlox.entities;

import lowleveldesign.codingBlox.builders.QuestionBuilder;
import lowleveldesign.codingBlox.enums.Difficulty;

import java.math.BigInteger;

public class Question {
    String question;
    Difficulty level;
    BigInteger score;
    BigInteger id;

    public static QuestionBuilder builder() {

        return new QuestionBuilder(new Question());

    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Difficulty getLevel() {
        return level;
    }

    public void setLevel(Difficulty level) {
        this.level = level;
    }

    public BigInteger getScore() {
        return score;
    }

    public void setScore(BigInteger score) {
        this.score = score;
    }


    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Question{" +
                "question='" + question + '\'' +
                ", level=" + level +
                ", score=" + score +
                ", id=" + id +
                '}';
    }
}
