package lowleveldesign.codingBlox.services;

import lowleveldesign.codingBlox.dao.ContestDao;
import lowleveldesign.codingBlox.entities.Contest;
import lowleveldesign.codingBlox.entities.User;
import lowleveldesign.codingBlox.enums.Difficulty;

import java.util.List;
import java.util.stream.Collectors;

public class ContestService {
    private static final ContestDao contestDao = new ContestDao();

    public Contest createContest(String contestName, Difficulty level, User owner) {
        Contest contest = new Contest();
        contest.setContestName(contestName);
        contest.setLevel(level);
        contest.setOwner(owner);
        contestDao.save(contest);
        return contest;
    }


    public List<Contest> listAllContests() {
        return contestDao.findAll();
    }

    public List<Contest> listContests(Difficulty difficulty) {
        return contestDao.findAll()
                .stream()
                .filter(contest -> contest.getLevel().equals(difficulty))
                .collect(Collectors.toList());
    }

    public boolean runContest(Contest contest, String userName) {
        return true;
    }

    public void contestHistory() {

    }

//    public void addUserToContest(Contest contest, User user) {
//        contest
//    }
}
