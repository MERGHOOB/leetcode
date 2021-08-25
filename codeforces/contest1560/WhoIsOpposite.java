import java.util.Scanner;

public class WhoIsOpposite {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int testcases = scanner.nextInt();
        while (testcases-- > 0) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            int target = scanner.nextInt();
            System.out.println(check(x, y, target));
        }
    }

    private static int check(int x, int y, int target) {
        int count = Math.abs(x - y) * 2;
        // at least this much node is required.
        ;// 3-4
        // count = 2;
        int cand = (target + Math.abs(x - y)) % count;
        if (cand == 0) {
            cand = count;
        }

        if (target > x && target < y) {
            return cand < x || cand > y ? cand : -1;
        } else if(target < x || target > y) {
            return cand > x || cand < y ? cand : -1;
        }
        return -1;
    }
}
