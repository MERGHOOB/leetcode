package indeed.karat.find_contagious_history;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Solution {


    private static List<String> findContiguousHistory(String[] user0, String[] user1) {

        HashMap<String, List<String>> map = new HashMap<>();
        return findContiguousHistory(user0, 0, user1, 0, map);
    }

    private static List<String> findContiguousHistory(String[] first, int i, String[] second, int j, HashMap<String, List<String>> map) {


        if (i >= first.length || j >= second.length) {
            return new ArrayList<>();
        }

        String key = i + "-" + j;
        if (map.containsKey(key)) {
            return map.get(key);
        }

        List<String> result = new ArrayList<>();
        while (i < first.length && j < second.length && first[i].equals(second[j])) {

            result.add(first[i]);
            i++;
            j++;
        }
        List<String> excludeSecond = findContiguousHistory(first, i, second, j + 1, map);
        List<String> excludeFirst = findContiguousHistory(first, i + 1, second, j, map);

        List<String> candidate = excludeFirst.size() > excludeSecond.size() ? excludeFirst : excludeSecond;
        map.put(key, result.size() > candidate.size() ? result : candidate);
        return map.get(key);
    }

    public static void main(String[] argv) {
        String[] user0 = {"/start", "/green", "/blue", "/pink", "/register", "/orange", "/one/two"};
        String[] user1 = {"/start", "/pink", "/register", "/orange", "/red", "a"};
        String[] user2 = {"a", "/one", "/two"};
        String[] user3 = {"/pink", "/orange", "/yellow", "/plum", "/blue", "/tan", "/red", "/amber", "/HotRodPink", "/CornflowerBlue", "/LightGoldenRodYellow", "/BritishRacingGreen"};
        String[] user4 = {"/pink", "/orange", "/amber", "/BritishRacingGreen", "/plum", "/blue", "/tan", "/red", "/lavender", "/HotRodPink", "/CornflowerBlue", "/LightGoldenRodYellow"};
        String[] user5 = {"a"};
        String[] user6 = {"/pink", "/orange", "/six", "/plum", "/seven", "/tan", "/red", "/amber"};

        System.out.println(findContiguousHistory(user0, user1));// ["/pink", "/register", "/orange"]
        System.out.println(findContiguousHistory(user0, user2));// [] (empty)
        System.out.println(findContiguousHistory(user0, user0));// ["/start", "/green", "/blue", "/pink", "/register", "/orange", "/one/two"]
        System.out.println(findContiguousHistory(user2, user1));// ["a"]
        System.out.println(findContiguousHistory(user5, user2));// ["a"]
        System.out.println(findContiguousHistory(user3, user4));// ["/plum", "/blue", "/tan", "/red"]
        System.out.println(findContiguousHistory(user4, user3));// ["/plum", "/blue", "/tan", "/red"]
        System.out.println(findContiguousHistory(user3, user6));// ["/tan", "/red", "/amber"]

    }
}
