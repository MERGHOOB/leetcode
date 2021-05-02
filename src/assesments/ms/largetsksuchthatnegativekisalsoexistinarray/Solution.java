package assesments.ms.largetsksuchthatnegativekisalsoexistinarray;

import java.util.HashSet;
import java.util.Set;

class Solution {
    public int findLargetK(Integer[] input) {

        int max = 0;
        Set<Integer> hashset = new HashSet<>();
        for (Integer val : input) {
            if (hashset.contains(negativeOf(val))) {

                max = Integer.max(max, Math.abs(val));
            }
            hashset.add(val);
        }
        return max;
    }

    private Integer negativeOf(Integer input) {
        return -(input);
    }
}
