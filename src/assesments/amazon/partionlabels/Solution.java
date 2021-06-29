package assesments.amazon.partionlabels;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {

    public List<Integer> partitionLabels(String s) {

        List<Integer> result  = new ArrayList<>();
        int [] lastIndexMap = new int[256];
        for(int i = 0; i< s.length(); i++) {
            lastIndexMap[s.charAt(i)] = i;
        }


        int prev = -1, x = 0, y = -1;
        while(x < s.length() && y < s.length()) {

            y = Integer.max(y, lastIndexMap[s.charAt(x)]);
            if(x == y) {
                result.add(x - prev);
                prev = x;
            }
            x++;

        }
        return result;
    }

    public static void main(String[] args) {
        String str = "ababcbacadefegdehijhklij";
        System.out.println(new Solution().partitionLabels(str));

    }
}
