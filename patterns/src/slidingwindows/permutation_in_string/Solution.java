package slidingwindows.permutation_in_string;
/*

     s1 = "ab", j = 2
     s2 = "eidbaooo"
     */
@SuppressWarnings("unused")
class Solution {
    public boolean checkInclusion(String s1, String s2) {

        int[] hash1 = new int[26];
        int[] hash2 = new int[26];

        int s1len = s1.length();
        int s2len = s2.length();
        if (s1len > s2len) {
            return false;
        }

        int i = 0, j = 0;




        while (j < s1len) {
            hash1[s1.charAt(j) - 'a']++;
            hash2[s2.charAt(j) - 'a']++;
            j++;


        }
        j -= 1; // to point on the end of window;

        while (j < s2len) {
            if (compare(hash1, hash2)) {
                return true;
            }

            hash2[s2.charAt(i) - 'a']--;
            i++;

            j++;
            if (j < s2len) {
                hash2[s2.charAt(j) - 'a']++;
            }


        }

        return false;


    }

    boolean compare(int[] first, int[] second) {

        for (int i = 0; i < first.length; i++) {
            if (first[i] != second[i]) return false;
        }

        return true;

    }
}