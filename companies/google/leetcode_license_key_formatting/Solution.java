package leetcode_license_key_formatting;

class Solution {

    public static final char HYPHEN = '-';

    public static void main(String[] args) {
//        System.out.println(new Solution().licenseKeyFormatting("5F3Z-2e-9-w", 4));
        System.out.println(new Solution().licenseKeyFormatting("2-5g-3-J", 2));
    }

    public String licenseKeyFormatting(String s, int k) {

        StringBuilder stringBuilder = new StringBuilder();
        int temp = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '-') {
                continue;
            }

            stringBuilder.append(Character.toUpperCase(s.charAt(i)));
            temp++;
            if (temp == k) {
                stringBuilder.append('-');
                temp = 0;
            }
        }

        if (stringBuilder.length() != 0 && stringBuilder.charAt(stringBuilder.length() - 1) == '-') {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }

        return stringBuilder.reverse().toString();
    }
}