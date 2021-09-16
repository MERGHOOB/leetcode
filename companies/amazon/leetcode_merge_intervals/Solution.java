package leetcode_merge_intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public int[][] merge(int[][] intervals) {

        List<int[]> res = new ArrayList<>();
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        for(int i  = 1; i<intervals.length; i++) {

            if(intervals[i][0] <= intervals[i-1][1]) {
                intervals[i][0] = intervals[i-1][0];
                intervals[i][1] = Math.max(intervals[i-1][1], intervals[i][1]);
            }
            else {
                res.add(intervals[i-1]);
            }

        }

        res.add(intervals[intervals.length-1]);


        return res.toArray(new int[res.size()][2]);

    }
}