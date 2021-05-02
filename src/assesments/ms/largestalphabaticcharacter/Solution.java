package assesments.ms.largestalphabaticcharacter;

class Solution {
    public static String largestCharacter(String str) {
        boolean[] uppercase = new boolean[26];
        boolean[] lowercase = new boolean[26];

        for (Character character : str.toCharArray()) {
            if (Character.isLowerCase(character)) {
                lowercase[character - 'a'] = true;
            } else {
                uppercase[character - 'A'] = true;
            }
        }

        for (int i = 25; i >= 0; i--) {
            if (uppercase[i] == lowercase[i]) {
                return String.valueOf((char) (i + 'A'));
            }
        }

        return "-1";
    }
}
