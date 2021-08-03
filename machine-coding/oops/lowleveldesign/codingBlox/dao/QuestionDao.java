package lowleveldesign.codingBlox.dao;

import lowleveldesign.codingBlox.entities.Question;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestionDao {
    Map<BigInteger, Question> questionDB = new HashMap<>();
    public static BigInteger index = BigInteger.ZERO;

    public void saveAll(List<Question> questions) {
        questions.forEach(this::save);
    }

    public List<Question> findAll() {
        return new ArrayList<>(questionDB.values());
    }

    public void save(Question question) {
        synchronized (index) {
            index = index.add(BigInteger.ONE);
            question.setId(index);
            questionDB.put(index, question);
        }
    }
}
