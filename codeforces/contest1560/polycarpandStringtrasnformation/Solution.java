package polycarpandStringtrasnformation;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Solution {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int testcases = scanner.nextInt();
        while (testcases-- > 0) {
            String str = scanner.next();
            new Solution().check(str);
        }
    }

    private void check(String str) {

        int n = str.length() - 1;
        char prev = str.charAt(n - 1);
        Set<Character> set = new HashSet<>();
        set.add(prev);

      



    }

}
