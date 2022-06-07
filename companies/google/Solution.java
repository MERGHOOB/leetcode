import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {

    public static String[] solution(String[] x) {
        // Your code here
        sort(x, 0, x.length - 1);
        return x;
    }

    private static void sort(String[] x, int left, int right) {

        if(left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;

        sort(x, left, mid);
        sort(x, mid + 1, right);
        merge(x, left, mid, right);
    }

    private static void merge(String[] x, int left, int mid, int right) {
        int leftSize = mid - left + 1;
        int rightSize = right - mid;

        String[] leftArray = new String[leftSize];
        String[] rightArray = new String[rightSize];
        for (int i = 0; i < leftSize; i++) {
            leftArray[i] = x[left + i];
        }
        for (int j = 0; j < rightSize; j++) {
            rightArray[j] = x[mid + 1 + j];
        }

        int i = 0, j = 0;
        int k = left;
        while(i<leftSize && j<rightSize) {
            if(compareStrings(leftArray[i], rightArray[j]) < 0) {
                x[k++] = leftArray[i++];
            }
            else {
                x[k++]= rightArray[j++];
            }
        }

        while(i<leftSize) {
            x[k++] = leftArray[i++];
        }
        while(j<rightSize) {
            x[k++] = rightArray[j++];
        }

    }

    private static List<Integer> splitIntoInteger(String str) {
        List<Integer> list = new ArrayList<>();
        String[] split = str.split("[.]");
        int i = 0;
        for (; i < split.length; i++) {
            list.add(Integer.parseInt(split[i]));
        }
        while (i < 3) {
            list.add(0);
            i++;
        }
        list.add(split.length);
        return list;
    }

    public static int compareStrings(String o1, String o2) {
        List<Integer> xList = splitIntoInteger(o1);
        List<Integer> yList = splitIntoInteger(o2);

        for (int i = 0; i < 3; i++) {
            if (!xList.get(i).equals(yList.get(i))) {
                return xList.get(i)- yList.get(i);
            }
        }

        return xList.get(3)- yList.get(3);

    }

    public static void main(String[] args) {

        String x[] = {"1.11", "2.0.0", "1.2", "2", "0.1", "1.2.1", "1.1.1", "2.0"};
//        String x[] = {"1.1.2", "1.0", "1.3.3", "1.0.12", "1.0.2"};

        sort(x, 0, x.length-1);
        System.out.println(Arrays.asList(x));
    }

    /*
    -- Java cases --
Input:
Solution.solution({"1.11", "2.0.0", "1.2", "2", "0.1", "1.2.1", "1.1.1", "2.0"})
Output:
    0.1,1.1.1,1.2,1.2.1,1.11,2,2.0,2.0.0

Input:
Solution.solution({"1.1.2", "1.0", "1.3.3", "1.0.12", "1.0.2"})
Output:
    1.0,1.0.2,1.0.12,1.1.2,1.3.3

     */

}
