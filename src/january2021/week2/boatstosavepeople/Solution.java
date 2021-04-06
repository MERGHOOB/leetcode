package january2021.week2.boatstosavepeople;

import java.util.Arrays;

class Solution {
    public int numRescueBoats(int[] people, int limit) {

        Arrays.sort(people);

        if (people[people.length - 1] > limit)
            return -1;

        int head = 0, tail = people.length - 1;
        int count = 0;
        while (head <= tail) {
            if (people[head] + people[tail] <= limit) {
                head++;
            }
            tail--;
            count++;
        }

        return count;

    }
}
