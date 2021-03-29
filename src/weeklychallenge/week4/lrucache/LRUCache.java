package weeklychallenge.week4.lrucache;

import java.util.HashMap;
import java.util.Map;

class LRUCache {

    Map<Integer, Entry> hashMap;
    Entry start, end;
    int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        hashMap = new HashMap<>();
    }

    public int get(int key) {
        if (hashMap.containsKey(key)) {
            Entry entry = hashMap.get(key);
            removeNode(entry);
            addAtTop(entry);
            return entry.val;
        }
        return -1;

    }

    public void put(int key, int value) {
        if (hashMap.containsKey(key)) {
            Entry entry = hashMap.get(key);
            entry.val = value; // update the value if key exists
            removeNode(entry);
            addAtTop(entry);
        } else {
            Entry entry = new Entry();
            entry.key = key;
            entry.val = value;
            if (hashMap.size() + 1 > capacity) { // if capacity already full, remove end
                hashMap.remove(end.key);
                removeNode(end);
            }
            hashMap.put(key, entry);
            addAtTop(entry);
        }
    }

    private void addAtTop(Entry entry) {
        entry.left = null;
        entry.right = start;
        if (start != null) start.left = entry;
        start = entry;
        if (end == null) end = start;
    }

    private void removeNode(Entry entry) {
        if (entry.left != null) {
            entry.left.right = entry.right;
        } else {
            start = entry.right;
        }

        if (entry.right != null) {
            entry.right.left = entry.left;
        } else {
            end = entry.left;
        }


    }


    private static class Entry {
        int key;
        int val;
        Entry left;
        Entry right;

    }
}
