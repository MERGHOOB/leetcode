package assesments.blend.findPairintwosortedlistsumtogiventarget;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {

    public List<Integer> getPairSum(List<Integer> first, List<Integer> second, Integer target) {


        Set<Integer> secondSet = new HashSet<>(second);
        for(Integer val: first) {
            if(secondSet.contains(target-val)) {
                List<Integer> res = new ArrayList<>();
                res.add(val);
                res.add(target-val);
                return res;
            }
        }
        return null;

//        return getPairSumElementInNLOGN(first, second, target);

//        return getPairSumWithOSQUARETIME(first, second, target);
    }

    private List<Integer> getPairSumElementInNLOGN(List<Integer> first, List<Integer> second, Integer target) {
        for(int i = 0; i< first.size(); i++) { //
            int toFind = target - first.get(i);

            int index = Arrays.binarySearch(second.toArray(), 0, first.size() - 1, toFind); // logn

            if(index >= 0) {
                List<Integer> res= new ArrayList<>();
                res.add(first.get(i));
                res.add(second.get(index));
                return res;
            }
        }
        return null;
    }

    private List<Integer> getPairSumWithOSQUARETIME(List<Integer> first, List<Integer> second, Integer target) {
        int firstPointer = 0;
        int secondPointer = second.size() - 1;
        List<Integer> result = new ArrayList<>();
        while (firstPointer <= secondPointer) {
            Integer fromFirst = first.get(firstPointer);
            Integer fromSecond = second.get(secondPointer);
            int sum = fromFirst + fromSecond;

            if (sum == target) {
                result.add(fromFirst);
                result.add(fromSecond);
                return result;
            }

            if (sum > target) {
                secondPointer--;
            } else {
                firstPointer++;
            }
        }
        return null;
    }
}
