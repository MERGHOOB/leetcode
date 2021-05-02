package assesments.ms.minstepstomakepilestoequalheight;

import java.util.Arrays;
import java.util.Collections;

class Solution {
    int minSteps(Integer[] piles) {


        Arrays.sort(piles, Collections.reverseOrder());
        int steps = 0;

        int i = 0, end = piles.length - 1;
        while (i < end) {
            if (!piles[i].equals(piles[i + 1])) {
                steps += (i + 1);
            }
            i++;
        }
        return steps;
    }
    public static void main(String[] args) {
        System.out.println(new Solution().minSteps(new Integer[]{5,2,1}));
        System.out.println(new Solution().minSteps(new Integer[]{4,5,5,4,2}));
    }
}
