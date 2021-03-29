package weeklychallenge.week2.minStack;

import java.util.Stack;

/**
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 * <p>
 * Implement the MinStack class:
 * <p>
 * MinStack() initializes the stack object.
 * void push(val) pushes the element val onto the stack.
 * void pop() removes the element on the top of the stack.
 * int top() gets the top element of the stack.
 * int getMin() retrieves the minimum element in the stack.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input
 * ["MinStack","push","push","push","getMin","pop","top","getMin"]
 * [[],[-2],[0],[-3],[],[],[],[]]
 * <p>
 * Output
 * [null,null,null,null,-3,null,0,-2]
 * <p>
 * Explanation
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin(); // return -3
 * minStack.pop();
 * minStack.top();    // return 0
 * minStack.getMin(); // return -2
 * <p>
 * <p>
 * Constraints:
 * <p>
 * -231 <= val <= 231 - 1
 * Methods pop, top and getMin operations will always be called on non-empty stacks.
 * At most 3 * 104 calls will be made to push, pop, top, and getMin.
 */
class MinStack {
    Stack<MinStackEntry> stack = new Stack<>();

    public MinStack() {

    }

    public void push(int val) {
        MinStackEntry minStackEntry = new MinStackEntry();
        minStackEntry.val = val;
        minStackEntry.minValue = (stack.isEmpty() || getMin() > val) ? val : getMin();
        stack.push(minStackEntry);

    }

    public void pop() {
        stack.pop();
    }

    public int top() {
        return stack.peek().val;
    }

    public int getMin() {
        return stack.peek().minValue;
    }

    private class MinStackEntry {
        int val;
        int minValue;
    }
}
