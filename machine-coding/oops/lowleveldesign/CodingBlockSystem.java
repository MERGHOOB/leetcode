package lowleveldesign;

import lowleveldesign.codingBlox.entities.Contest;
import lowleveldesign.codingBlox.services.ContestService;
import lowleveldesign.codingBlox.entities.Question;
import lowleveldesign.codingBlox.services.QuestionService;
import lowleveldesign.codingBlox.entities.User;
import lowleveldesign.codingBlox.services.UserService;
import lowleveldesign.codingBlox.enums.Difficulty;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
CreateUser <user_name>
CreateQuestion <difficulty_level>
ListQuestion <difficulty_level>
CreateContest <contest_name> <contest_level> <contest_creator_user_name>
ListContest <difficulty_level>
AttendContest <contest_id> <user_name>
RunContest <contest_id> <contest_creator_user_name>
LeaderBoard <sorting order asc/desc>
ContestHistory <contest_id>
WithdrawContest <contest_id>
 */
public class CodingBlockSystem {

    public static final UserService userservice = new UserService();
    public static final QuestionService questionService = new QuestionService();
    public static final ContestService contestService = new ContestService();
    // Centralized Class which knows the all internal classes/db

    public static final CodingBlockSystem codingBlockSystem = new CodingBlockSystem();

    private CodingBlockSystem() {

    }

    public static CodingBlockSystem getInstance() {
        return codingBlockSystem;
    }


    public User createUser(String userName) {
        return User.builder().userName(userName).build();
    }

    public void storeMultiUsers(List<User> asList) {
        userservice.storeMultiUsers(asList);
    }

    public List<User> findAllUsers() {
        return userservice.findAll();
    }

    public Question createQuestion(String s, Difficulty low, int i) {
        return Question.builder().question(s).level(low).score(BigInteger.valueOf(i)).build();
    }

    public void storeMultiQuestions(List<Question> asList) {
        questionService.storeMultiQuestions(asList);
    }

    public List<Question> findAllQuestions() {
        return questionService.findAll();
    }

    public List<Question> listQuestions(Difficulty difficulty) {
        return questionService.listQuestions(difficulty);
    }

    public Contest createContest(String contest, Difficulty difficulty, User user) {
        Contest contest1 = contestService.createContest(contest, difficulty, user);
        userservice.attendContest(contest1, user);
        return contest1;
    }

    public List<Contest> listAllContests() {
        return contestService.listAllContests();
    }

    public List<Contest> listContests(Difficulty difficulty) {
        return contestService.listContests(difficulty);
    }

    public void attendContest(Contest contest, User user) {
        userservice.attendContest(contest, user);
    }

    public void runContest(Contest contest, User user) {
        if (!contest.getOwner().equals(user)) {
            throw new RuntimeException("WARNING: Owner is allowed to run contest!!!");
        }

        contest.getQuestions().addAll(questionService.listQuestions(contest.getLevel()));
    }

    public void calculate(User user, Contest contest) {

        int size = contest.getQuestions().size();
        Random random = new Random();
        // let's select only 2 question
        int i = 0;
        BigInteger contestScore = BigInteger.ZERO;
        while (i < 3) {
            int questionNumber = random.nextInt(size);
            contestScore = contestScore.add(contest.getQuestions().get(questionNumber).getScore());
            i++;
        }

        contestScore = contestScore.subtract(BigInteger.valueOf(getLevelBaseCut(contest.getLevel())));
        user.setScore(user.getScore().add(contestScore));
        System.out.println(user);
    }

    private long getLevelBaseCut(Difficulty level) {
        if (level == Difficulty.low) {
            return 50;
        } else if (level == Difficulty.medium) {
            return 30;
        } else {
            return 0;
        }
    }

    public void leaderboard(boolean isAscending) {
        System.out.println("    leader board    ");
        List<User> all = new ArrayList<>(List.copyOf(userservice.findAll()));

        all.sort((user1, user2) -> {
            if (isAscending) {
                return user1.getScore().compareTo(user2.getScore());
            } else {
                return user2.getScore().compareTo(user1.getScore());
            }

        });

        all.forEach(System.out::println);

    }
}


