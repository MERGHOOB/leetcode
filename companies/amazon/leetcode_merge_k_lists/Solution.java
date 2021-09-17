package leetcode_merge_k_lists;

import java.util.PriorityQueue;

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

    public static final int DUMMY_HEAD_VALUE = 0;

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        if (lists.length == 1) {
            return lists[0];
        }
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.val, b.val));

        for (ListNode node : lists) {
            if (node != null) pq.add(node);
        }

        ListNode dummyHead = new ListNode(DUMMY_HEAD_VALUE);
        ListNode iter = dummyHead;
        while (!pq.isEmpty()) {
            ListNode poll = pq.poll();
            iter.next = poll;
            iter = poll;
            if (poll.next != null) {
                pq.add(poll.next);
            }
        }

        return dummyHead.next;
    }

    private static class ListNode {
        public ListNode next;
        public int val;

        public ListNode(int val) {
            this.val = val;
        }

        public ListNode(ListNode next, int val) {
            this.next = next;
            this.val = val;
        }
    }
}