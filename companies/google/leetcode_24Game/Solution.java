package leetcode_24Game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public boolean judgePoint24(int[] cards) {
        // convert everything in double

        return judgeWithoutRounding(cards);

//
//        int n = cards.length;
//        List<Double> doubles = new ArrayList<>();
//        for (int card : cards) {
//            doubles.add((double) card);
//        }
//
//        return judge24(doubles);


    }

    private boolean judgeWithoutRounding(int[] cards) {
//        List.of(new N)
        Number[] numbers = {
                new Number(cards[0]),
                new Number(cards[1]),
                new Number(cards[2]),
                new Number(cards[3])
        };

        return dfs(Arrays.asList(numbers));
    }

    private boolean dfs(List<Number> numbers) {
        if (numbers.size() == 1) {
            return numbers.get(0).equals(24);
        }

        for (int i = 0; i < numbers.size(); i++) {
            for (int j = i + 1; j < numbers.size(); j++) {

                for (Number n : compute(numbers.get(i), numbers.get(j))) {
                    List<Number> next = new ArrayList<>();
                    next.add(n);
                    for (int k = 0; k < numbers.size(); k++) {
                        if (k == i || k == j) {
                            continue;
                        }
                        next.add(numbers.get(k));
                    }
                    if (dfs(next)) {
                        return true;
                    }
                }

            }
        }


        return false;
    }

    private List<Number> compute(Number first, Number second) {
        List<Number> result = new ArrayList<>();
        if (first.equals(0) || second.equals(0)) {
            result.add(first.equals(0) ? second : first);
            return result;
        }

        result.add(first.add(second));
        result.add(first.subtract(second));
        result.add(second.subtract(first));
        result.add(first.multiply(second));
        result.add(first.divide(second));
        result.add(second.divide(first));

        return result;

    }

    private boolean judge24(List<Double> doubles) {

        if (doubles.size() == 1) {
            return Math.abs(doubles.get(0) - 24) < 0.0001;  // this is a roounding issuel
        }

        for (int i = 0; i < doubles.size(); i++) {
            for (int j = i + 1; j < doubles.size(); j++) {

                for (double computed : compute(doubles.get(i), doubles.get(j))) {

                    List<Double> next = new ArrayList<>();
                    next.add(computed);
                    for (int k = 0; k < doubles.size(); k++) {
                        if (k != i && k != j) next.add(doubles.get(k));
                    }
                    if (judge24(next)) {
                        return true;
                    }
                }

            }
        }
        return false;
    }

    private List<Double> compute(double first, double second) {
        List<Double> result = new ArrayList<>();
        if (first == 0.0 || second == 0.0) {
            result.add(first == 0.0 ? second : first);
            return result;
        }
        result.add(first + second);
        result.add(first - second);
        result.add(second - first);
        result.add(first * second);
        result.add(first / second);
        result.add(second / first);
        return result;


    }

    // how to resolve the rounding issue;


    private static class Number {
        int dividend;
        int divisor;

        public Number(int val) {
            this.dividend = val;
            this.divisor = 1;
        }

        public Number(int dividend, int divisor) {
            this.dividend = dividend;
            this.divisor = divisor;
        }

        public boolean equals(int num) {
            return dividend % divisor == 0 && dividend / divisor == num;
        }

        public Number subtract(Number o) {
            return new Number(this.dividend * o.divisor - o.dividend * this.divisor, this.divisor * o.divisor);
        }

        public Number multiply(Number o) {
            return new Number(this.dividend * o.dividend, this.divisor * o.divisor);
        }

        public Number add(Number o) {
            return new Number(this.dividend * o.divisor + o.dividend * this.divisor, this.divisor * o.divisor);

        }

        public Number divide(Number o) {
            if (o.dividend == 0) {
                throw new IllegalArgumentException("Invalid division by zero");
            }

            return new Number(this.dividend * o.divisor, o.dividend * this.divisor);


        }
    }

}