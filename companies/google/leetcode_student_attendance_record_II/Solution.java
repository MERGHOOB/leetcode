package leetcode_student_attendance_record_II;

import java.util.Arrays;

/*
An attendance record for a student can be represented as a string where each character signifies
whether the student was absent, late, or present on that day. The record only contains the following three characters:

'A': Absent.
'L': Late.
'P': Present.
Any student is eligible for an attendance award if they meet both of the following criteria:

The student was absent ('A') for strictly fewer than 2 days total.
The student was never late ('L') for 3 or more consecutive days.
Given an integer n, return the number of possible attendance records of length n
that make a student eligible for an attendance award. The answer may be very large, so return it modulo 109 + 7.


 */
class Solution {

    public static int MOD = 1000000007;

    public static void main(String[] args) {
        int i = new Solution().checkRecordNotClear(10);
        System.out.println(i);

        System.out.println(new Solution().checkRecord(10));
    }

    public int checkRecord(int n) {
        int res = 0;

        int[][][] dp = new int[n + 1][3][2];
        // 3 dimension array as for each i, we will have 3 choices for count of consecutive 'L' (0/1/2) and two values for 'A' (0/1)



        /*
        dp[i][0][0] =  0 consecutive L, 0A => appending P at the end; PP, PLP, PLLP
        dp[i][0][1] ==0 consecutive L, 1 A ==>  dp[i-1][0][0] ==> PA, PLA, PLLA, PLLA, APP, APLP, APLLP
        dp[i][1][0] == 1consecutive L, 0A --> 0L 0A ==> dp[i-1][0][0]
        dp[i][1][1] == 1 L, 1A ==> 0L1A (PA, AP) ==> dp[i-1][0][1]
        dp[i][2][0]== 2 consecutive L and 0A ==> 1L,0A ==> dp[i-1][1][0]
        dp[i][2][1] == 2 consecutive L and 1 A ==> it can only comes from 1L,1A ==> dp[i-1][1][1]

        //sum of all the state for dp[n] is the value which is required;

        //Base cases:
        string of len1;
        dp[1][0][0] = 1; // 0L, 0A; P
        dp[1][0][1] = 1; // 0L, 1A; A
        dp[1][1][0] = 1; // 1L, 0A; L
        dp[1][1][1] = 0 ; // 1L, 1A;
        dp[1][2][0] = 0;
        dp[1][2][1] = 0;

         */
        dp[1][0][0] = 1; // 0L, 0A; P
        dp[1][0][1] = 1; // 0L, 1A; A
        dp[1][1][0] = 1; // 1L, 0A; L
        dp[1][1][1] = 0; // 1L, 1A;
        dp[1][2][0] = 0;
        dp[1][2][1] = 0;

        for (int i = 2; i <= n; i++) {
            dp[i][0][0] = ((dp[i - 1][0][0] + dp[i - 1][1][0]) % MOD + (dp[i - 1][2][0]) % MOD) % MOD;
            dp[i][0][1] =
                    (((dp[i - 1][0][0] + dp[i - 1][1][0]) % MOD + (dp[i - 1][2][0]) % MOD) % MOD +
                            ((dp[i - 1][0][1] + dp[i - 1][1][1]) % MOD + (dp[i - 1][2][1]) % MOD) % MOD) % MOD;
            dp[i][1][0] = dp[i - 1][0][0] % MOD;
            dp[i][1][1] = dp[i - 1][0][1] % MOD;
            dp[i][2][0] = dp[i - 1][1][0] % MOD;
            dp[i][2][1] = dp[i - 1][1][1] % MOD;
                    /*
 dp[i][0][0] =  0 consecutive L, 0A => appending P at the end; PP, PLP, PLLP
        dp[i][0][1] ==0 consecutive L, 1 A ==>  dp[i-1][0][0] ==> PA, PLA, PLLA, PLLA, APP, APLP, APLLP
        dp[i][1][0] == 1consecutive L, 0A --> 0L 0A ==> dp[i-1][0][0]
        dp[i][1][1] == 1 L, 1A ==> 0L1A (PA, AP) ==> dp[i-1][0][1]
        dp[i][2][0]== 2 consecutive L and 0A ==> 1L,0A ==> dp[i-1][1][0]
        dp[i][2][1] == 2 consecutive L and 1 A ==> it can only comes from 1L,1A ==> dp[i-1][1][1]
 */
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                res += dp[n][i][j];
                res %= MOD;
            }
        }
        Integer a[]  = new Integer[7];
        Integer[] integers = Arrays.copyOf(a, a.length);
        return res;
    }




/*
    Pn = An-1 + Pn-1 + Ln-1; // n>=2
    Ln = An-1 + Pn-1 + A(n-2) + P(n-2); // cause L(n-2) can not be used
    An = An-1 + An-2 + An-3;

    P(1) = 1;  // P
    L(1)= 1; //L
    L(2) = 3 // PL, AL,LL
    A(1) = 1;  // A
    A(2) = 2; // PA, LA
    A(3) = 4; // PL, PP, LL, LP

 */

    public int checkRecordNotClear(int n) {

        if (n == 1) {
            return 3;
        }
        if (n == 2) {
            return 8;
        }
        int[] p = new int[n];
        int[] a = new int[n];
        int[] l = new int[n];
        p[0] = 1;
        l[0] = 1;
        l[1] = 3;
        a[0] = 1;
        a[1] = 2;
        a[2] = 4;

        for (int i = 1; i < n; i++) {
            a[i - 1] %= MOD;
            p[i - 1] %= MOD;
            l[i - 1] %= MOD;

            p[i] = (p[i - 1] + a[i - 1]) % MOD + l[i - 1] % MOD;
            if (i > 1) {
                l[i] = (a[i - 1] + p[i - 1]) % MOD + (a[i - 2] + p[i - 2]) % MOD;
                l[i] %= MOD;
            }
            if (i > 2) {
                a[i] = (a[i - 1] + a[i - 2]) % MOD + (a[i - 3] % MOD);
                a[i] %= MOD;
            }
        }

        return ((a[n - 1] % MOD + p[n - 1] % MOD) % MOD + l[n - 1] % MOD) % MOD;


    }


}
