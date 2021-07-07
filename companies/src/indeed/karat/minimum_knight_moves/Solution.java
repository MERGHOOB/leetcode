package indeed.karat.minimum_knight_moves;

import java.nio.charset.CoderResult;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/*
http://lixinchengdu.github.io/algorithmbook/leetcode/minimum-knight-moves.html
 */
class Solution {

    Set<Coordinate> visited = new HashSet<>();

    int minKnightMoves(int x, int y) {

        int[] xAxis = {-2, -1, 1, 2, 2, 1, -1, -2};
        int[] yAxis = {1, 2, 2, 1, -1, -2, -2, -1};

        x = Math.abs(x);
        y = Math.abs(y); // only deal with absolute: means non negative

        Queue<Coordinate> queue = new LinkedList<>();
        Coordinate source = new Coordinate(0, 0);
        queue.add(source);
        visited.add(source);
        int count = 0;
        while (!queue.isEmpty()) {

            int size = queue.size();
            while (size-- > 0) {
                Coordinate poll = queue.poll();
                assert poll != null : "It is not possible as loop is based on outer loop is based " +
                        "on queue.isEmpty and this loop is based on size";
                if (poll.x == x && poll.y == y) {
                    return count;
                }
                for (int i = 0; i < 8; i++) {
                    int nx = poll.x + xAxis[i];
                    int ny = poll.y + yAxis[i];

                    Coordinate neighbour = new Coordinate(nx, ny);

                    if (nx >= 0 && ny >= 0 && visited.contains(neighbour)) {
                        continue;
                    }
                    queue.add(neighbour);
                    visited.add(neighbour);
                }
            }
            count++;
        }
        throw new IllegalStateException("Answer is guaranteed");
    }

    private static class Coordinate {
        int x;
        int y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }


        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Coordinate)) {
                return false;
            }
            Coordinate other = (Coordinate) obj;
            return other.x == this.x && other.y == this.y;
        }
    }


    public static void main(String[] args) {
        System.out.println(new Solution().minKnightMoves(-1, -1));
    }
}
