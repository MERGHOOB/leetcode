package leetcode_expressive_words;

class Solution {
    public int expressiveWords(String s, String[] words) {

        int res  = 0;

        for(String word: words) {

            if(fuzzy(s, word)) {
                res++;
            }

        }

        return res;

    }

    public boolean fuzzy(String s, String word) {

        int i,j;

        for(i = 0, j= 0; i<s.length() && j<word.length();) {

            if(s.charAt(i) != word.charAt(j)) {
                return false;
            }

            int countI = getCharCount(i,s);
            int countJ = getCharCount(j,word);

            if(countI != countJ && countI<3 || (countJ > countI)) {
                return false;
            }
            i = i+ countI;
            j = j+ countJ;

        }

        return i==s.length() && j == word.length();


    }

    int getCharCount(int start, String str) {
        char ch = str.charAt(start);
        int c = 0;
        for(int i = start; i<str.length() && str.charAt(i) == ch; i++) {
            c++;
        }
        return c;
    }
}