package digital_wallet.transactionallogs;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class LogService {

    Map<String, Queue<List<String>>> logs = new HashMap<>();

    private static final LogService logService = new LogService();

    private LogService() {
    }

    public static LogService getInstance() {
        return logService;
    }

    public void statement(String user) {
        logs.get(user).forEach(System.out::println);
    }

    public void log(String user, List<String> list) {
        logs.putIfAbsent(user, new LinkedList<>());
        logs.get(user).add(list);
    }

    public List<String> getTopUsers(int k) {
        List<String> result = new ArrayList<>();
        PriorityQueue<Map.Entry<String, Queue<List<String>>>> priorityQueue =
                new PriorityQueue<>(Comparator.comparing(q -> q.getValue().size()));

        priorityQueue.addAll(logs.entrySet());

        int size = priorityQueue.size();
        int target = size - k;
        while (!priorityQueue.isEmpty() && size != target) {
            result.add(priorityQueue.poll().getKey());
        }

        return result;
    }
}
