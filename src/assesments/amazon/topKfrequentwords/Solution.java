package assesments.amazon.topKfrequentwords;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public List<String> topKFrequent(String[] words, int k) {

        Map<String, Integer> freqMap = new HashMap<>();


        for (String word : words) {
            if (!freqMap.containsKey(word)) {
                freqMap.put(word, 1);
            } else {
                freqMap.put(word, freqMap.get(word) + 1);
            }
        }
        List<String> uniqueWords = new ArrayList<>(freqMap.keySet());
        uniqueWords.sort((o1, o2) -> {

            if (freqMap.get(o1).equals(freqMap.get(o2))) {
                return o1.compareTo(o2);
            }
            return freqMap.get(o2).compareTo(freqMap.get(o1));
        });

       return uniqueWords.subList(0, k);
    }

    public static void main(String[] args) {
        String[] words = {"i", "love", "leetcode", "i", "love", "coding"};
        List<String> strings = new Solution().topKFrequent(words, 2);
        strings.forEach(System.out::println);
    }
}
