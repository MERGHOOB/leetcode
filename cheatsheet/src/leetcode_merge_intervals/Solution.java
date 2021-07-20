package leetcode_merge_intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class Solution {
    public int[][] merge(int[][] intervals) {

        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        List<int[]> res = new ArrayList<>();

        int[] pre = intervals[0];
        int x = 1;
        while (x < intervals.length) {

            if (pre[1] >= intervals[x][0]) {
                //merge
                pre[1] = Math.max(pre[1], intervals[x][1]);
            } else {
                res.add(pre);
                pre = intervals[x];
            }

            x++;
        }

        res.add(pre);
        return res.toArray(new int[res.size()][2]);

    }
}