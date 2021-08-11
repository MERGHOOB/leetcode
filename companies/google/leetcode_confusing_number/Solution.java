package leetcode_confusing_number;

import java.util.HashMap;
import java.util.Map;

class Solution {

    public boolean isConfusing(int n) {
        Map<Character, Character> map = new HashMap<>();
        map.put('0', '0');
        map.put('1', '1');
        map.put('6', '9');
        map.put('8', '8');
        map.put('9', '6');


        String s = String.valueOf(n);
        char[] chars = s.toCharArray();
        int length = chars.length;
        char[] temp = new char[length];
        for (char ch : chars) {
            if (!map.containsKey(ch)) {
                return false;
            }
            temp[--length] = map.get(ch);
        }

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != temp[i]) {
                return true;
            }
        }

        return false;

    }

    public static void main(String[] args) {
        System.out.println(new Solution().isConfusing(12));
    }
}
