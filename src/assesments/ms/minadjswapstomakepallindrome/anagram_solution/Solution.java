package assesments.ms.minadjswapstomakepallindrome.anagram_solution;

class Solution {

    public int getNoOfSwaps(String s) {
        // check if possible palindrome
        // then compute pallindrome
        // no of swaps based on geekforgeeks question:
        //https://www.geeksforgeeks.org/minimum-number-of-adjacent-swaps-to-convert-a-string-into-its-given-anagram/
        // s, pallindrome


        if (!isPossiblePalindrome(s)) {
            return -1;
        }
        String palindrome = computePalindrome(s);
        return countSteps(s.toCharArray(), palindrome.toCharArray(), s.length());
    }

    private int countSteps(char[] src, char[] target, int size) {
        int i = 0, j = 0;
        int result = 0;
        while (i < size) {
            j = i;
            // find index element of src string which is equal to  ith element of target
            while (src[j] != target[i]) {
                j++;
            }

            while (i < j) {
                char temp = src[j];
                src[j] = src[j - 1];
                src[j - 1] = temp;
                j--;
                result += 1;
            }

            i++;
        }
        return result;
    }

    private String computePalindrome(String s) {
        int size = s.length();
        char[] result = s.toCharArray();
        int[] count = new int[26]; // assuming lowercase latter

        for (Character c : s.toCharArray()) {
            count[c - 'a']++;
        }
        int start = 0, end = size - 1;
        int mid = size / 2;
        for (int i = 0; i < 25; i++) {
            if (count[i] % 2 == 1) {
                result[mid] = (char) ('a' + i);
                count[i]--;
            }
            while (count[i] != 0) {
                result[start++] = (char) ('a' + i);
                result[end--] = (char) ('a' + i);
                count[i] -= 2;
            }
        }
        return new String(result);
    }

    private boolean isPossiblePalindrome(String s) {
        int[] count = new int[26]; // assuming lowercase latter

        for (Character c : s.toCharArray()) {
            count[c - 'a']++;
        }
        int oddCount = 0;
        for (int i = 0; i < 26; i++) {
            if (count[i] % 2 == 1) {
                oddCount++;
            }
        }
        return oddCount <= 1;
    }

    public static void main(String[] args) {
        String s = "mamad";
        System.out.println(new Solution().getNoOfSwaps(s));
    }
}
