package minimum_flips_to_convert_number;

import java.util.BitSet;

class Solution {
    public int minBitFlips(int start, int goal) {

//       return Integer.bitCount(start ^ goal);

        int xor = start ^ goal;
        int flips = 0;
        while (xor != 0) {
            flips++;
            xor = xor & (xor -1);
        }
        return flips;
        /*
            10 = 1010
            7  = 0111
           xor =  1101 (13)
         & xor-1= 1100 (12)
         xor    = 1100 (12)
         & xor-1= 1011 (11)
         xor    = 1000 (8)
         & xor-1= 0111 (7)
         xor    = 0000 (0)
         */
    }

    public static void main(String[] args) {
        new Solution().minBitFlips(10,7);
    }
}