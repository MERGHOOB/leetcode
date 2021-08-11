package leetcode_confusing_number_II;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SolutionII {

    Map<Integer, Integer> map = new HashMap<>();
    int res = 0;

    public int confusingNumberII(int N) {
        map.put(0, 0);
        map.put(1, 1);
        map.put(6, 9);
        map.put(8, 8);
        map.put(9, 6);
        helper(N, 0);
        return res;
    }

    private void helper(int N, long cur) {
        if (isConfusingNumber(cur)) {
            res++;
        }

        for (Integer i : map.keySet()) {
            long next = cur * 10 + i;
            if (next <= N && next != 0) {
                helper(N, next);
            }
        }
    }

    private boolean isConfusingNumber(long n) {
        long src = n;
        long res = 0;
        while (n > 0) {
            res = res * 10 + map.get((int) n % 10);
            n /= 10;
        }
        return res != src;
    }

    public static void main(String[] args) {
        long time = new Date().getTime();
        System.out.println(new SolutionII().confusingNumberII(100000000));
        System.out.println(new Date().getTime() - time);
    }
}
