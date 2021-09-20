package leetcode_median_finder;

import java.util.Collections;
import java.util.PriorityQueue;

class MedianFinder {

    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

    /**
     * initialize your data structure here.
     */
    public MedianFinder() {

    }

    public void addNum(int num) {

        if (maxHeap.size() > 0 && num <= maxHeap.peek()) {
            maxHeap.add(num);
        } else {
            minHeap.add(num);
        }

        // balancing elements

        if (maxHeap.size() - minHeap.size() == 2) {
            minHeap.add(maxHeap.poll());
        } else if (minHeap.size() - maxHeap.size() == 2) {
            maxHeap.add(minHeap.poll());
        }


    }

    public double findMedian() {
        int minSize = minHeap.size();
        int maxSize = maxHeap.size();

        if (minSize == maxSize) {
			//noinspection ConstantConditions
			return (double) (maxHeap.peek() + minHeap.peek()) / 2;
        } else {
            return minSize > maxSize ? minHeap.peek() : maxHeap.peek();
        }

    }
}

/*
  Your MedianFinder object will be instantiated and called as such:
  MedianFinder obj = new MedianFinder();
  obj.addNum(num);
  double param_2 = obj.findMedian();
 */