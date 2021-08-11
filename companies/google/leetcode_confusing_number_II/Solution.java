package leetcode_confusing_number_II;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;

class Solution {
    static Map<Character, Character> map;

    static {
        map = new HashMap<>();
        map.put('0', '0');
        map.put('1', '1');
        map.put('6', '9');
        map.put('8', '8');
        map.put('9', '6');
    }

    /**
     * @param k: the number of auction participants
     * @return: the number of confusing numbers
     */

    int count;
    int N;

    public int theConfusingNumbers(int k) {
        count = 0;
        N = k;
        combos(1);
        combos(6);
        combos(8);
        combos(9);

        return count;


    }

    private void combos(int n) {
        if (n > N) {
            return;
        }

        if (isConfusing(n)) {
//            System.out.println(n);
            count += 1;
        }

        n = n * 10;
        for (Character ch : map.keySet()) {
            int chInt = ch - '0';
            combos(n + chInt);
        }

    }

    private boolean isConfusing(int n) {


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
//                System.out.println(" ");
                return true;
            }
        }

        return false;

    }


    public static void main(String[] args) {
        System.out.println(new Solution().theConfusingNumbers(100000000));
    }
}
