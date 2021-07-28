package leetcode_valid_parantheses;

import java.util.Stack;

class Solution {
    public boolean isValid(String str) {

        Stack<Character> s = new Stack<>();
        for (int i = 0; i < str.length(); i++) {

            char ch = str.charAt(i);
            if (isClosed(ch)) {
                if (s.isEmpty() || s.peek() != getOpen(ch)) {
                    return false;
                }
                s.pop();
            } else {
                s.push(ch);
            }
        }
        return s.isEmpty();

    }

    public boolean isClosed(Character ch) {
        return ch == ']' || ch == '}' || ch == ')';
    }

    public Character getOpen(Character ch) {
        if (ch == ']') return '[';
        if (ch == '}') return '{';
        if (ch == ')') return '(';

        return '-';
    }
}
