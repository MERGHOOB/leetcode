package lowleveldesign.codingBlox.dao;

import lowleveldesign.codingBlox.entities.Contest;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContestDao {
    public Map<BigInteger, Contest> contestDB = new HashMap<>();
    public static BigInteger index = BigInteger.ZERO;

    public void save(Contest contest) {
        synchronized (index) {
            index = index.add(BigInteger.ONE);
            contest.setId(index);
            contestDB.put(index, contest);
        }
    }

    public List<Contest> findAll() {
        return new ArrayList<>(contestDB.values());
    }
}
