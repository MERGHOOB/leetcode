package minimum_number_of_moves_to_make_pallindrome;

class Solution {


    public int minMovesToMakePalindrome(String s) {

        // in this it is confirmed that there will always a possiblity to create Pallindrome
        return minMovesToMakePalindrome(s.toCharArray(), 0, s.length() - 1);
    }

    private int minMovesToMakePalindrome(char[] s, int start, int end) {
        if (start >= end) {
            return 0;
        }

        if (s[start] == s[end]) {
            return minMovesToMakePalindrome(s, start + 1, end - 1);
        }

        int res = 0;
        int k = end;
        while (s[k] != s[start]) {
            k--;
        }

        if (k == start) {
            res++;
            swap(s, start, start + 1); // put it in between
            return res + minMovesToMakePalindrome(s, start, end);
        }
        while (k < end) {
            res++;
            swap(s, k, k + 1);
            k++;
        }


        return res + minMovesToMakePalindrome(s, start+1, end-1);


    }

    private void swap(char[] s, int start, int end) {
        char temp = s[start];
        s[start] = s[end];
        s[end] = temp;
    }


}