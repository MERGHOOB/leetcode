package makemytrip.leetcode_discuss_hackNumber;

/**
 * You are given two string: S1 and S2, a character C(which can be either Y or N) and Integer I
 * <p>
 * Hack Number:
 * if the first occurrence(the index of first char) of the S2 in S1 starting from or after I (initial position)
 * depends on C.
 * <p>
 * if C == Y:
 * matched S2 must have a space to the left of it in S1 or either start from s1 and it must have the same for right of in S1
 * or at the end of S1
 * <p>
 * i.e-> It must be a whole word in S1
 * <p>
 * else if C == N
 * there is no compulsion, it can also be inside the word of s1.
 */
class Solution {

    public static final String GOODBYE_WATSON = "Goodbye Watson";

    public static void main(String[] args) {
        String s1 = "We love to hack on hackerearth";
        String s2 = "hack";
        String mode = "Y";
        Integer initialPosition = 0;

        System.out.println(new Solution().getHackNumber(s1, s2, mode, initialPosition));
    }

    private String getHackNumber(String s1,
                                 String s2,
                                 String mode,
                                 Integer initialPosition) {

        String substring = s1.substring(initialPosition);

        int firstIndex = substring.indexOf(s2);
        if (firstIndex == -1) { //not present
            return GOODBYE_WATSON;
        }
        if ("N".equals(mode)) {  // if mode is equal to N, check the, send the index of first occurrence in s1;
            return String.valueOf(initialPosition + firstIndex);
        }
        // it means mode is "Y"
        // and so whole word is required after initial position
        String[] split = substring.split(" ");
        for (String word : split) {
            if (s2.equals(word.trim())) {
                return String.valueOf(initialPosition + substring.indexOf(s2));
            }
        }

        return GOODBYE_WATSON;

    }
}
