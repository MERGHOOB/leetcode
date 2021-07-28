package leetcode_my_calander_I;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

class MyCalendar {

    List<Interval> intervals;
    TreeMap<Integer, Integer> treeMap = new TreeMap<>();


    public MyCalendar() {
    }

    public boolean book(int start, int end) {

        return bookUsingTreeMap(start, end);
//        return binarySearch(start, end);

    }

    private boolean bookUsingTreeMap(int start, int end) {

        Map.Entry<Integer, Integer> floorEntry = treeMap.floorEntry(start);
        if (floorEntry != null && floorEntry.getValue() > start) {
            return false;
        }

        Map.Entry<Integer, Integer> ceiling = treeMap.ceilingEntry(end);
        if (ceiling != null && ceiling.getKey() < end) {
            return false;
        }

        treeMap.put(start, end);

        return true;
    }

    private boolean binarySearch(int start, int end) {
        intervals = new ArrayList<>();

        Interval interval = new Interval(start, end);

        if (intervals.isEmpty()) {
            intervals.add(interval);
            return true;
        }

        int left = 0, right = intervals.size() - 1;

        while (left <= right) {

            int mid = left + (right - left) / 2;


            if (
                    (intervals.get(mid).start <= interval.start
                            && intervals.get(mid).end > interval.start)
                            || (intervals.get(mid).start < interval.end
                            && intervals.get(mid).end >= interval.end)

            ) {
                return false;
            }

            if (interval.start >= intervals.get(mid).end) {
                left = mid + 1;
            } else if (interval.end <= intervals.get(mid).start) {
                right = mid - 1;
            }

        }

        intervals.add(left, interval); // O(n) ; can this be done in logn // use TreeMap
        return true;

    }


    static class Interval {
        int start, end;

        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }

    }


    public static void main(String[] args) {
        MyCalendar myCalendar = new MyCalendar();
        System.out.println(myCalendar.book(10, 20));
        System.out.println(myCalendar.book(15, 25));
        System.out.println(myCalendar.book(20, 30));
    }

}