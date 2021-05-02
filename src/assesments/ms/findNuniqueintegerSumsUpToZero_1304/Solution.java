package assesments.ms.findNuniqueintegerSumsUpToZero_1304;

class Solution {
    public int[] sumZero(int n) {

        int start = 0, end = n - 1;
        int value = n >> 1;
        int[] result = new int[n];
        while (start < end) {
            result[start++] = value;
            result[end--] = -value;
            value--;
        }

        return result;
    }
}
