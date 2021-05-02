package assesments.ms.jumpgameIII;

import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public boolean canReach(int[] arr, int start) {

        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        boolean [] visited = new boolean[arr.length];
        visited[start] = true;
        while (!queue.isEmpty()) {
            Integer poll = queue.poll();
            if (arr[poll] == 0) {
                return true;
            }
            int indexJump = arr[poll];
            int left = poll - indexJump;
            if (left >= 0 && !visited[left]) {
                queue.add(left);
                visited[left] = true;
            }
            int right = poll + indexJump;
            if (right < arr.length && !visited[right]) {
                queue.add(right);
                visited[right] = true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int [] ints = {3,0,2,1,2};
        System.out.println(new Solution().canReach(ints, 2));
    }
}