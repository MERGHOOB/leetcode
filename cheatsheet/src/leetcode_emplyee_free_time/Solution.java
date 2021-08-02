package leetcode_emplyee_free_time;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Solution {
    /**
     * @param schedule: a list schedule of employees
     * @return: Return a list of finite intervals
     */
    public List<Interval> employeeFreeTime(int[][] schedule) {
        // Write your code here
        List<Interval> res = new ArrayList<>();
        List<Interval> intervals = new ArrayList<>();
        for (int[] val : schedule) {
            for (int i = 0; i < val.length; i += 2) {
                intervals.add(new Interval(val[i], val[i+1]));
            }
        }

        intervals.sort((a,b)-> a.start != b.start? a.start - b.start : a.end - b.end);

        for (int i = 1; i < intervals.size(); i++) {
            if (intervals.get(i).start > intervals.get(i - 1).end) {
                res.add(
                        new Interval(
                                intervals.get(i - 1).end,
                                intervals.get(i).start)
                );
            }

            // update the end of last interval: (6,14), (9,12), (13,15); if following line is not present; (12,13) will be accounted
            intervals.get(i).end = Math.max(intervals.get(i).end,
                    intervals.get(i-1).end);
        }

        return res;

    }

    private static class Interval {
        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int start;
        public int end;
    }


    public static void main(String[] args) {
        int [][] schedule = {{1,3,6,7},{2,4},{2,5,9,12}};
        List<Interval> x = new Solution().employeeFreeTime(schedule);
        for(Interval interval: x) {
            System.out.println(interval.start + " - " + interval.end);
        }
//        System.out.println(x);
    }
}