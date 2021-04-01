package january2021.week1.removeduplicatesfromsortedlistII;

import utils.ListNode;

/***
 *   Remove Duplicates from Sorted List II
 *
 * Given the head of a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list. Return the linked list sorted as well.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: head = [1,2,3,3,4,4,5]
 * Output: [1,2,5]
 * Example 2:
 *
 *
 * Input: head = [1,1,1,2,3]
 * Output: [2,3]
 *
 *
 * Constraints:
 *
 * The number of nodes in the list is in the range [0, 300].
 * -100 <= Node.val <= 100
 * The list is guaranteed to be sorted in ascending order.
 */
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode prev = null, curr = head;

        while (curr != null && curr.next != null) {
            if (curr.val == curr.next.val) {
                int temp = curr.val;
                while (curr != null && curr.val == temp) {
                    if (prev == null) {
                        curr = curr.next;
                        head = curr;
                    } else {
                        prev.next = curr.next;
                        curr = curr.next;
                    }
                }
            } else {
                prev = curr;
                curr = curr.next;
            }
        }
        return head;
    }
}
