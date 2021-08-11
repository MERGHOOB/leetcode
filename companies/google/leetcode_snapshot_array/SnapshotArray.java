package leetcode_snapshot_array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

class SnapshotArray {

    int currId = 0;
    List<TreeMap<Integer, Integer>> arr;

    public SnapshotArray(int length) {

        arr = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            arr.add(i, new TreeMap<>());
            arr.get(i).put(0, 0);
        }

    }

    public void set(int index, int val) {
        arr.get(index).put(currId, val);
    }

    public int snap() {
        return currId++;
    }

    public int get(int index, int snap_id) {
        return arr.get(index).floorEntry(snap_id).getValue();
    }
}

/**
 * Your SnapshotArray object will be instantiated and called as such:
 * SnapshotArray obj = new SnapshotArray(length);
 * obj.set(index,val);
 * int param_2 = obj.snap();
 * int param_3 = obj.get(index,snap_id);
 */
