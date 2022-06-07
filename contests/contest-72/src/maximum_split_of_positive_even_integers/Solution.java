package maximum_split_of_positive_even_integers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
    public List<Long> maximumEvenSplit(long finalSum) {

        if (finalSum % 2 == 1) {
            return Collections.emptyList();
        }

        List<Long> result = new ArrayList<>();

        long value = 2;
        while (finalSum >= value) {

            result.add(value);
            finalSum -= value;

            value = value + 2;

        }
        int lastIndex = result.size() - 1;
        result.set(lastIndex, result.get(lastIndex) + finalSum);


        return result;
    }
}
