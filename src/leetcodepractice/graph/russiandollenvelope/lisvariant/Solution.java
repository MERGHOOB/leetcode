package leetcodepractice.graph.russiandollenvelope.lisvariant;


import java.util.Arrays;

class Solution {
    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0) {
            return 0;
        }
        Arrays.sort(envelopes, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return Integer.compare(o2[1], o1[1]); // descending in height
                // this way we can safely skip the envelope with same width but higher height
            }
            return Integer.compare(o1[0], o2[0]); // ascending in width
        });

        int[] lis = new int[envelopes.length];

        lis[0] = envelopes[0][1]; // height based increasing sequence
        int len = 1;

        for (int i = 1; i < envelopes.length; i++) {
            if (envelopes[i][1] > lis[len-1]) {
                lis[len++] = envelopes[i][1];
            } else {
                int insertionPoint = Arrays.binarySearch(lis, 0, len, envelopes[i][1]);
                if (insertionPoint < 0) {
                    insertionPoint = -(insertionPoint + 1); //when not found it return -(low+1); to find low
                }
                lis[insertionPoint] = envelopes[i][1];
            }
        }
        return len;
    }


}
