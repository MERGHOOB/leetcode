package january2021.week2.addtwolists;

import utils.ListNode;

/**
 * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order, and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.
 * <p>
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * <p>
 * <p>
 * <p>
 * Input: l1 = [2,4,3], l2 = [5,6,4]
 * Output: [7,0,8]
 * Explanation: 342 + 465 = 807.
 * Example 2:
 * <p>
 * Input: l1 = [0], l2 = [0]
 * Output: [0]
 * Example 3:
 * <p>
 * Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 * Output: [8,9,9,9,0,0,0,1]
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode res = new ListNode(-1);
        ListNode result = res;
        int carry = 0;

        while (l1 != null && l2 != null) {

            int value = l1.val + l2.val + carry;
            res.next = new ListNode(value % 10);
            carry = value / 10;
            l1 = l1.next;
            l2 = l2.next;
            res = res.next;

        }

        while (l1 != null) {
            int value = carry + l1.val;
            res.next = new ListNode(value % 10);
            carry = value / 10;
            l1 = l1.next;
            res = res.next;
        }

        while (l2 != null) {
            int value = carry + l2.val;
            res.next = new ListNode(value % 10);
            carry = value / 10;
            l2 = l2.next;
            res = res.next;
        }

        return result.next;
    }
}
