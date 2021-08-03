package lowleveldesign;

import lowleveldesign.codingBlox.entities.Contest;
import lowleveldesign.codingBlox.entities.Question;
import lowleveldesign.codingBlox.entities.User;
import lowleveldesign.codingBlox.enums.Difficulty;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // create USER
        final User hariom = CodingBlockSystem.getInstance().createUser("hariom");
        final User chandan = CodingBlockSystem.getInstance().createUser("chandan");
        final User naveen = CodingBlockSystem.getInstance().createUser("naveen");
        final User omprakash = CodingBlockSystem.getInstance().createUser("omprakash");
        // store all users
        CodingBlockSystem.getInstance().storeMultiUsers(Arrays.asList(hariom, chandan, naveen, omprakash));
        User zuber = CodingBlockSystem.getInstance().createUser("zuber");

        CodingBlockSystem.getInstance().findAllUsers().forEach(System.out::println);


        //
        final Question question1 = CodingBlockSystem.getInstance().createQuestion("que 1", Difficulty.low, 10);
        final Question question2 = CodingBlockSystem.getInstance().createQuestion("que 2", Difficulty.low, 20);
        final Question question3 = CodingBlockSystem.getInstance().createQuestion("que 3", Difficulty.medium, 20);
        final Question question4 = CodingBlockSystem.getInstance().createQuestion("que 4", Difficulty.medium, 30);
        final Question question5 = CodingBlockSystem.getInstance().createQuestion("que 5", Difficulty.high, 40);
        final Question question6 = CodingBlockSystem.getInstance().createQuestion("que 6", Difficulty.high, 50);
        final Question question7 = CodingBlockSystem.getInstance().createQuestion("que 7", Difficulty.low, 10);
        final Question question8 = CodingBlockSystem.getInstance().createQuestion("que 8", Difficulty.low, 20);
        final Question question9 = CodingBlockSystem.getInstance().createQuestion("que 9", Difficulty.low, 20);
        final Question question10 = CodingBlockSystem.getInstance().createQuestion("que 9", Difficulty.low, 20);

        CodingBlockSystem.getInstance().storeMultiQuestions(Arrays.asList(question1,
                question2,
                question3,
                question4,
                question5,
                question6,
                question7,
                question8,
                question9,
                question10
        ));

        @SuppressWarnings("unused")
        Question question = CodingBlockSystem.getInstance().createQuestion("que 7", Difficulty.low, 10);//Not sure weather this should be stored or not
        CodingBlockSystem.getInstance().findAllQuestions().forEach(System.out::println);

        //Get all questions level wise
        List<Question> lowLevelQuestions = CodingBlockSystem.getInstance().listQuestions(Difficulty.low);
        System.out.println(lowLevelQuestions);

        List<Question> mediumLevelQuestions = CodingBlockSystem.getInstance().listQuestions(Difficulty.medium);
        System.out.println(mediumLevelQuestions);

        List<Question> highLevelQuestions = CodingBlockSystem.getInstance().listQuestions(Difficulty.high);
        System.out.println(highLevelQuestions);

        // create Contest
        final Contest contest1 = CodingBlockSystem.getInstance().createContest("contest 1", Difficulty.low, hariom);
        final Contest contest2 = CodingBlockSystem.getInstance().createContest("contest 1", Difficulty.medium, chandan);

        CodingBlockSystem.getInstance().listAllContests().forEach(System.out::println);

        // find all contest by difficulty level
        List<Contest> lowLevelContests = CodingBlockSystem.getInstance().listContests(Difficulty.low);
        System.out.println(lowLevelContests);
        System.out.println(CodingBlockSystem.getInstance().listContests(Difficulty.medium));
        System.out.println(CodingBlockSystem.getInstance().listContests(Difficulty.high));

        // user can register to contest
        CodingBlockSystem.getInstance().attendContest(contest1, chandan);
        CodingBlockSystem.getInstance().attendContest(contest1, naveen);
        CodingBlockSystem.getInstance().attendContest(contest2, omprakash);
        CodingBlockSystem.getInstance().attendContest(contest2, naveen);

        // runContests
//        CodingBlockSystem.getInstance().addQuestionsToContest(contest1, CodingBlockSystem.getInstance().findAllQuestions());
        CodingBlockSystem.getInstance().runContest(contest1, hariom);

        // hariom completes 3 question
        CodingBlockSystem.getInstance().calculate(hariom, contest1);
        CodingBlockSystem.getInstance().calculate(chandan, contest1);
        CodingBlockSystem.getInstance().calculate(naveen, contest1);

        // Leader board
        CodingBlockSystem.getInstance().leaderboard(false);


    }


}
