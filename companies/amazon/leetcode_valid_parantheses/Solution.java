package leetcode_valid_parantheses;

import java.util.Stack;

class Solution {
    public boolean isValid(String s) {

        Stack<Character> stack = new Stack<>();

        for (char ch : s.toCharArray()) {
            if (isOpen(ch)) {
                stack.push(ch);
            } else {
                if (stack.isEmpty() || getOpen(ch) != stack.peek()) {
                    return false;
                }
                stack.pop();
            }
        }

        return stack.isEmpty();
    }

    private boolean isOpen(char c) {
        return c == '(' || c == '[' || c == '{';
    }

    private char getOpen(char c) {

        if (c == ')') return '(';
        else if (c == ']') return '[';
        else if (c == '}') return '{';
        return '-';


    }
}
