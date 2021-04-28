package january2021.week3.countedsortedvowelstring;

import java.util.Arrays;

/**
 * Given an integer n, return the number of strings of length n that consist only of vowels (a, e, i, o, u) and are lexicographically sorted.
 * <p>
 * A string s is lexicographically sorted if for all valid i, s[i] is the same as or comes before s[i+1] in the alphabet.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: n = 1
 * Output: 5
 * Explanation: The 5 sorted strings that consist of vowels only are ["a","e","i","o","u"].
 * Example 2:
 * <p>
 * Input: n = 2
 * Output: 15
 * Explanation: The 15 sorted strings that consist of vowels only are
 * ["aa","ae","ai","ao","au","ee","ei","eo","eu","ii","io","iu","oo","ou","uu"].
 * Note that "ea" is not a valid string since 'e' comes after 'a' in the alphabet.
 * Example 3:
 * <p>
 * Input: n = 33
 * Output: 66045
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 50
 */
class Solution {
/*
     n==1:  a--> 1 , e --> 1, i --> 1,o-->1, u --> 1; // i.e with starting a, there is just one  with b there is just one
     n==2: a--> 5( aa, ae, ai, ao, au), e-->4(ee, ei, eo, eu), i--> 3, o-->2, u-->1
     n==3: a--> 15, e--> 10, i--> 6, o--> 3, u-->1;    uis always one--. sorted uuu

     by looking of it
     n==2(a) --> sum(n==1, for a to u)
     n==2(e) --> sum(n==1, from e to u)
        ...

        same for n==3 --> sum(n==2, from a to e)
 */

    public int countVowelStrings(int n) {
        int[] dp = new int[5];
        Arrays.fill(dp, 1);

        while (n !=0) {
            for (int i = 4; i >= 1; i--) {
                dp[i - 1] += dp[i];
            }
            n--;
        }
        return dp[0];
    }

    public static void main(String[] args) {
        int i = new Solution().countVowelStrings(3);
        System.out.println(i);
    }

}



