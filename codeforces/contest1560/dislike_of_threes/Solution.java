package dislike_of_threes;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Solution {

    public static List<Integer> list;

    static {
        list = new ArrayList<>();
        for (int num = 1; num < 1667; num++) {

            if (num % 3 == 0 || num % 10 == 3) {
                continue;
            }
            list.add(num);
        }

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCases = scanner.nextInt();
        while (testCases-- > 0) {
            int k = scanner.nextInt();
            System.out.println(list.get(k-1));
        }

    }
}
