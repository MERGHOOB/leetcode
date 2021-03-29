package weeklychallenge.week2.backspacecompare;

/**
 * Given two strings S and T, return if they are equal when both are typed into empty text editors. # means a backspace character.
 * <p>
 * Note that after backspacing an empty text, the text will continue empty.
 * <p>
 * Example 1:
 * <p>
 * Input: S = "ab#c", T = "ad#c"
 * Output: true
 * Explanation: Both S and T become "ac".
 * Example 2:
 * <p>
 * Input: S = "ab##", T = "c#d#"
 * Output: true
 * Explanation: Both S and T become "".
 * Example 3:
 * <p>
 * Input: S = "a##c", T = "#a#c"
 * Output: true
 * Explanation: Both S and T become "c".
 * Example 4:
 * <p>
 * Input: S = "a#c", T = "b"
 * Output: false
 * Explanation: S becomes "c" while T becomes "b".
 */
class Solution {
    public boolean backspaceCompare(String s, String t) {

        int i = s.length() - 1;
        int j = t.length() - 1;


        while (i >= 0 || j >= 0) {

            int skipS = 0;
            while (i >= 0) {
                if (s.charAt(i) == '#') {
                    skipS++;
                    i--;
                } else if (skipS > 0) {
                    skipS--;
                    i--;

                } else break;
            }

            int skipT = 0;
            while (j >= 0) {
                if (t.charAt(j) == '#') {
                    skipT++;
                    j--;
                } else if (skipT > 0) {
                    skipT--;
                    j--;
                } else break;
            }

            if (i >= 0 && j >= 0 && t.charAt(j) != s.charAt(i)) {
                return false;
            }
            if ((i >= 0) != (j >= 0)) {
                return false;
            }
            i--;
            j--;

        }
        return true;
    }


    public static void main(String[] args) {
        new Solution().backspaceCompare("xywrrmp", "xywrrmu#p");
    }
}
