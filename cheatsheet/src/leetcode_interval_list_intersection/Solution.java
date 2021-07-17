package leetcode_interval_list_intersection;

import java.util.ArrayList;
import java.util.List;

/*

//              (a,b) (c,d)
//              (0,2) (1,5) == >  0 < 1 and 2 < 5;  interval = 1,2 ==> (a < c) and b < d; interval(c,b)
//             (5,10) (1, 5) ==>  1 < 5 && 5 <10; interval (5,5) ==> c < a and d < b ; interval(a,d)
//             (5,10) (8,10) ==> 5 < 8 and 10 <= 10; (8,10) ==> a < c and b <= d; interval(c,b);
 */
class Solution {
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {

        List<int[]> res = new ArrayList<>();

        int i = 0;
        int j = 0;
        while (i < firstList.length && j < secondList.length) {


            // int startMax = Math.
            int a = firstList[i][0]; //0     0
            int b = firstList[i][1]; //2     3

            int c = secondList[j][0]; // 1   1
            int d = secondList[j][1]; // 5   1


            int startMax = Math.max(a, c);
            int endMin = Math.min(b, d);
            if (startMax <= endMin) {
                res.add(new int[]{startMax, endMin});
            }

            if (b == endMin) {
                i++;
            }
            if (d == endMin) {
                j++;
            }
        }

        return res.toArray(new int[res.size()][2]);
    }


    public static void main(String[] args) {
        int[][] firstList = {{0, 2}, {5, 10}, {13, 23}, {24, 25}};
        int[][] econdList = {{1, 5}, {8, 12}, {15, 24}, {25, 26}};
        System.out.println(new Solution().intervalIntersection(firstList, econdList));
    }
}
