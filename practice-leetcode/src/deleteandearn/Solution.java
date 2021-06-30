package deleteandearn;

import java.util.Map;
import java.util.TreeMap;

/**
 * https://leetcode.com/problems/delete-and-earn
 */
class Solution {
    public int deleteAndEarn(int[] nums) {

        int[] map = new int[10000];
        for (int num : nums) {
            map[num]++;
        }


        return calculate(map);

    }

    private int calculate(int[] map) {

        int dontDel = 0, delEle = 0, prev = -1;

        for (int k = 0; k < map.length; k++) {

            int max = Math.max(dontDel, delEle);
            if (k == prev + 1) {

                delEle = k * map[k] + dontDel; // if we going to delete element then we need to add dontDel value of prev;
                dontDel = max; // if we are not goint to don't Delete, now new benifit; use maximum till now;
            } else {
                delEle = k * map[k] + max;
                dontDel = max;
            }
            prev = k;
        }

        return Math.max(delEle, dontDel);
    }
}
