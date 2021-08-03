package lowleveldesign.codingBlox.services;

import lowleveldesign.codingBlox.dao.QuestionDao;
import lowleveldesign.codingBlox.entities.Question;
import lowleveldesign.codingBlox.enums.Difficulty;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

public class QuestionService {

    public static final QuestionDao questionDao = new QuestionDao();


    public List<Question> listAllQuestions() {
        return questionDao.findAll();
    }

    public List<Question> listQuestions(Difficulty difficulty) {
        return questionDao.findAll()
                .stream()
                .filter(question -> question.getLevel().equals(difficulty))
                .collect(Collectors.toList());
    }


    public void storeMultiQuestions(List<Question> questions) {
        questionDao.saveAll(questions);
    }

    public Question createQuestion(String question, Difficulty level, int points) {
        Question question1 = new Question();
        question1.setQuestion(question);
        question1.setLevel(level);
        question1.setScore(BigInteger.valueOf(points));
        questionDao.save(question1);
        return question1;
    }

    public List<Question> findAll() {
        return questionDao.findAll();
    }
}
