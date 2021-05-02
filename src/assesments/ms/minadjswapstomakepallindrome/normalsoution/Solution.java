package assesments.ms.minadjswapstomakepallindrome.normalsoution;

class Solution {

    int minAdjacentSwaps(String str) {
        if (!isPossiblePalindrome(str)) {
            return -1;
        }

        char[] chars = str.toCharArray();
        int front = 0, back = chars.length - 1;
        int swaps = 0;

        while (front < back) {
            if (chars[front] != chars[back]) {
                // front != back
                // find rightmost character same as front
                int mid = back;
                while (mid > front && chars[mid] != chars[front]) {
                    mid--;
                }

                //char not found
                if (mid == front) {
                    char temp = chars[mid];
                    chars[mid] = chars[mid + 1];
                    chars[mid + 1] = temp;
                    swaps++;
                    continue; // no need to update front and back
                }

                // char found
                for (int i = mid; i < back; i++) {
                    char temp = chars[i];
                    chars[i] = chars[i + 1];
                    chars[i + 1] = temp;
                    swaps++;
                }

            }
            front++;
            back--;
        }
        return swaps;
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

}
