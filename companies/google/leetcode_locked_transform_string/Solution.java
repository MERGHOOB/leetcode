package leetcode_locked_transform_string;

import java.util.HashMap;
import java.util.Map;
/*
https://www.lintcode.com/problem/307/
 */
public class Solution {
    /**
     * @param A: a string A
     * @param B: a string B
     * @return: return the minimum number of operations to transform
     */
    public int transform(String A, String B) {
        // write your code here

        Map<Character, Integer> set = new HashMap<>();
        char[] a = A.toCharArray();
        for (char ch : a) {
            set.put(ch, set.getOrDefault(ch, 0) + 1);
        }
        char[] b = B.toCharArray();
        for (char ch : B.toCharArray()) {
            if (!set.containsKey(ch)) {
                return -1;
            }
            set.put(ch, set.get(ch) - 1);
        }

        for (char ch : set.keySet()) {
            if (set.get(ch) != 0) {
                return 0;
            }
        }

        int len = a.length;
        int res = 0;
        int i = len - 1, j = len - 1;
        while (i >= 0) {
            if (b[j] == a[i]) {
                j--;
            } else {
                res++;
            }

            i--;
        }
        return res;

    }
}