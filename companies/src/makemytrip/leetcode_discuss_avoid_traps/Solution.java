package makemytrip.leetcode_discuss_avoid_traps;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
There is a cave of N cells where each has traps  or is safe.

from a cell i --> i + 1 and i + 2,
Also if i is special: i --> i + a ==> a = numbers of prime number in [1,i]
also i is special only if ==> a/i >= r1/r2

Given --> details of cave, r1 and r2, N, Find the minimum number


 */
public class Solution {


    public static final int sieveLength = 100001;
    private final static int[] sieve = new int[sieveLength];

    static {
        init();
    }

    private static void init() {
        for (int i = 2; i < sieveLength; i++) {
            if (sieve[i] == 0) {
//                double maximumPossibleValue = Math.sqrt(sieveLength);
                for (int j = 2; i * j < sieveLength; j++) {
                    sieve[i * j] = 1;
                }
            }
        }
    }

    public int minimumNumberOfSteps(char[] cells, int r1, int r2) {
        int[] steps = new int[cells.length];
        Arrays.fill(steps, Integer.MAX_VALUE);
        steps[0] = 0;
        for (int i = 0; i < cells.length; i++) {
            if (cells[i] == '*') {
                continue;
            }
            if (i + 1 < cells.length && cells[i + 1] != '*') {
                steps[i + 1] = Math.min(steps[i + 1], steps[i] + 1);
            }
            if (i + 2 < cells.length && cells[i + 2] != '*') {
                steps[i + 2] = Math.min(steps[i + 2], steps[i] + 1);

            }
            int primeCount = getPrimeCount(i);
            if (primeCount > 2
                    && isSpecial(primeCount, i, r1, r2)
                    && i + primeCount < cells.length
                    && cells[i + primeCount] != '*') {
                steps[i + primeCount] = Math.min(steps[i + primeCount], steps[i] + 1);

            }

        }
        if (steps[cells.length - 1] == Integer.MAX_VALUE) {
            return -1;

        }
        return steps[cells.length - 1];
    }

    private int minimumNumberOfSteps(char[] cells, int currentPosition, int r1, int r2, Map<Integer, Integer> memo) {
        if (currentPosition == cells.length - 1) {
            memo.put(currentPosition, 0);
        }
        if (memo.containsKey(currentPosition)) {
            return memo.get(currentPosition);
        }
        int min1 = -1, min2 = -1, minPrime = -1;
        if (currentPosition + 1 < cells.length && cells[currentPosition + 1] == '#') {
            min1 = minimumNumberOfSteps(cells, currentPosition + 1, r1, r2, memo);
        }
        if (currentPosition + 2 < cells.length && cells[currentPosition + 2] == '#') {
            min2 = minimumNumberOfSteps(cells, currentPosition + 2, r1, r2, memo);
        }
        int primeCount = getPrimeCount(currentPosition);

        if (primeCount > 2 && isSpecial(primeCount, currentPosition, r1, r2)) {
            if (currentPosition + primeCount < cells.length
                    && cells[currentPosition + primeCount] == '#') {
                minPrime = minimumNumberOfSteps(cells, currentPosition + primeCount, r1, r2, memo);
            }
        }
        if (min1 == -1 && min2 == min1 && minPrime == min1) {
            memo.put(currentPosition, -1);
            return memo.get(currentPosition);
        }
        int min = Integer.MAX_VALUE;
        if (min1 != -1) {
            min = Math.min(min, min1);
        }
        if (min2 != -1) {
            min = Math.min(min, min2);
        }
        if (minPrime != -1) {
            min = Math.min(min, minPrime);
        }
        memo.put(currentPosition, 1 + min);
        return memo.get(currentPosition);
    }

    private boolean isSpecial(int primeCount, int currentPosition, int r1, int r2) {
        //a/i >= r1/r2
        return (primeCount * r2) >= (currentPosition * r1);

    }

    private int getPrimeCount(int currentPosition) {
        int count = 0;
        for (int i = 2; i <= currentPosition; i++) {
            if (sieve[i] == 0) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        String cells = "########";
        System.out.println(new Solution().minimumNumberOfSteps(cells.toCharArray(), 1, 2));
    }

}
