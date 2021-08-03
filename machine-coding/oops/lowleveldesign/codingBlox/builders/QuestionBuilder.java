package lowleveldesign.codingBlox.builders;

import lowleveldesign.codingBlox.entities.Question;
import lowleveldesign.codingBlox.enums.Difficulty;

import java.math.BigInteger;

public class QuestionBuilder {
    Question que;

    public QuestionBuilder(Question question) {
        this.que = question;
    }

    public QuestionBuilder question(String question) {
        que.setQuestion(question);
        return this;
    }

    public QuestionBuilder level(Difficulty low) {
        que.setLevel(low);
        return this;
    }

    public QuestionBuilder score(BigInteger value) {
        que.setScore(value);
        return this;
    }

    public Question build() {
        return que;
    }
}
