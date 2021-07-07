package indeed.karat.maximal_rectangle_of_zero;

import java.util.Stack;

/*
Round 2: same problem but now the matrix may contains many rectangles. Return the start and end indexes of each rec in an array.

matrix = [
["0","1","1","1","1"],
["1","1","0","0","1"],
["0","1","0","0","1"],
["0","1","1","1","1"],
["1","0","1","1","1"]
] => 4 rectangles in here . 0 by itself is also a rectangle, and vertical rectangle also counts.
 */
class Solution {

    public int[] maximumRectangleOfZero(char[][] matrix) {

        int[] height = new int[matrix[0].length];
        int[] result = new int[4];
        int maxArea = 0;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < height.length; j++) {

                height[j] = matrix[i][j] == '0' ? height[j] + 1 : 0;

            }

            Stack<Integer> stack = new Stack<>();
            stack.push(0);
            for (int k = 1; k < height.length; k++) {

                int curr = height[k];
                while (!stack.isEmpty() && curr < height[stack.peek()]) {
                    int h = height[stack.pop()];
                    int w;
                    if (stack.isEmpty()) {
                        w = k;

                    } else {
                        w = k - stack.peek() - 1;
                    }
                    int candidateArea = h * w;

                    if (candidateArea > maxArea) {
                        result[0] = i - h + 1;
                        result[1] = stack.isEmpty()? 0 :  stack.peek()+1;
                        result[2] = i;
                        result[3] = k-1 ;
                        maxArea = candidateArea;
                        System.out.println(maxArea);
                    }


                }
                stack.push(k);
            }

            int k = height.length;
            while (!stack.isEmpty()) {
                int h = height[stack.pop()];
                int w;
                if (stack.isEmpty()) {
                    w = k;

                } else {
                    w = k - stack.peek() - 1;
                }
                int candidateArea = h * w;

                if (candidateArea > maxArea) {
                    result[0] = i - h + 1;
                    result[1] = stack.isEmpty()? 0 :  stack.peek()+1;
                    result[2] = i;
                    result[3] = k ;
                    maxArea = candidateArea;
                    System.out.println(maxArea);
                }
            }


        }

        return result;
    }

    public static void main(String[] args) {

    char [][] matrix ={
            {'0','1','1','1','1'},
            {'0','0','0','0','1'},
            {'0','0','0','0','1'},
            {'0','1','1','1','1'},
//            {'1','0','1','1','1'}
    };
//    char [][] matrix ={
//            {'0','1','1','1','1'},
////            {'1','1','0','0','1'},
////            {'0','1','0','0','1'},
////            {'0','1','1','1','1'},
////            {'1','0','1','1','1'}
//    };

        int[] x = new Solution().maximumRectangleOfZero(matrix);
        System.out.println(x[0] + " " + x[1]);
        System.out.println(x[2] + " " + x[3]);


    }
}
