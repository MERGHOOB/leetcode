package indeed.karat.rectangle_of_zero;

/**
 * ["1","1","1","1","1"],
 * ["1","1","0","0","1"],
 * ["1","1","0","0","1"],
 * ["1","1","1","1","1"]
 * ]
 * <p>
 * <p>
 * one one rectangle is present in one zero--> it means all zeros are clustered together.
 * if we find a zero --. it will be the top left corner and to get width and height --. we will move to right and down till 1 comes
 * this is how we calculate height and width
 */
class Solution {

    public int[] rectangleOfZero(char[][] matrix) {

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {

                if (matrix[i][j] != '0') {
                    continue;
                }
                int[] result = new int[4]; // x1, y1, x2, y2

                result[0] = i;
                result[1] = j;

                int k = i;
                while (k < matrix.length && matrix[k][j] == '0') {
                    k++;
                }
                result[2] = k - 1;
                k = j;
                while (k < matrix[i].length && matrix[i][k] == '0') {
                    k++;
                }
                result[3] = k - 1;

                return result;
            }
        }
        throw new IllegalStateException("Either constraints are broken Or there is no zero present in matrix");
    }

}
