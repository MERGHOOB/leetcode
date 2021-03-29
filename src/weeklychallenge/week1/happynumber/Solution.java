package weeklychallenge.week1.happynumber;

/**
 * Write an algorithm to determine if a number n is happy.
 * <p>
 * A happy number is a number defined by the following process:
 * <p>
 * Starting with any positive integer, replace the number by the sum of the squares of its digits.
 * Repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1.
 * Those numbers for which this process ends in 1 are happy.
 * Return true if n is a happy number, and false if not.
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 231 - 1
 * <p>
 * Input: n = 19
 * Output: true
 * Explanation:
 * 1^2 + 9^2 = 82
 * 8^2 + 2^2 = 68
 * 6^2 + 8^2 = 100
 * 1^2 + 0^2 + 0^2 = 1
 */
class Solution {
    public boolean isHappy(int n) {
        return isHappyWithOutMemory(n);
    }

    private boolean isHappyWithOutMemory(int n) {
        int sum = n, x = n;

        while (sum > 9) {

            sum = 0;

            while (x > 0) {
                int d = x % 10;
                sum += d * d;
                x = x / 10;
            }
            x = sum;
        }
        return sum == 1 || sum == 7;
    }


}