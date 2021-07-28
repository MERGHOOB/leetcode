package leetcode_remove_zero_sum_consecutive_nodes_from_linked_list;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode removeZeroSumSublists(ListNode head) {

        ListNode root = new ListNode(0);
        root.next = head;
        Map<Integer, ListNode> map = new HashMap<>();
        map.put(0, root);
        int currentSum = 0;
        while (head != null) {
            currentSum += head.val;
            if(map.containsKey(currentSum)) {
                //Delete bad reference
                ListNode prefix = map.get(currentSum);
                ListNode start = prefix;

                int aux = currentSum;
                while (prefix != head) {
                    prefix = prefix.next;
                    aux += prefix.val;
                    if(prefix != head) {
                        map.remove(aux);
                    }
                }

                start.next = head.next;
            }
            else {
                map.put(currentSum, head);
            }
            head = head.next;
        }


        return root.next;
    }

    public static void main(String[] args) {

        ListNode head = new ListNode(0);
        ListNode temp = head;

        int[] nums = {1, 3, 2, -3, -2, 5, 5, -5, 1};
        //         0, 1, 4, 6, 3,  1,  6, 11, 6, 7
//        int[] nums = {1, -1};
        for (int num : nums) {
            temp.next = new ListNode(num);
            temp = temp.next;
        }


        ListNode listNode = new Solution().removeZeroSumSublists(head.next);
        while (listNode != null) {
            System.out.print(listNode.val + " ");
            listNode = listNode.next;
        }
        System.out.println();
    }

    private static class ListNode {


        int val;
        ListNode next;

        ListNode() {
        }

        public ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}