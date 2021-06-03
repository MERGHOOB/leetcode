package assesments.amazon.topNCompetitors;

import java.util.*;
import java.util.stream.Collectors;

class Solution {

    private static class Holder implements Comparable<Holder> {
        String name;

        public Holder(String name, Integer count) {
            this.name = name;
            this.count = count;
        }

        Integer count;

        @Override
        public int compareTo(Holder o) {
            if (count.equals(o.count)) {
                return name.compareTo(o.name);
            }
            return Integer.compare(o.count, count);
        }

        public String getName() {
            return name;
        }
    }

    List<String> getNCompetitors(int numOfCompetitors, int topCompetitors,
                                 List<String> competitors, int numReviews, List<String> reviews) {

        Map<String, Holder> map = new HashMap<>();
        competitors.forEach(value -> {
            map.put(value, new Holder(value, 0));
        });

        for (String review : reviews) {
            String[] split = review.trim().toLowerCase().split("\\s+");
            for (String word : split) {
                if (map.containsKey(word)) {
                    map.get(word).count += 1;
                }
            }
        }

        Collection<Holder> values = map.values();
        List<Holder> result = new ArrayList<>(values);
        Collections.sort(result);

        List<String> collect = result.stream()
                .map(holder -> holder.name)
                .collect(Collectors.toList());
        return collect;
    }
    public static void main(String[] args) {
        List<String> reviews = new ArrayList(
                Arrays.asList("newshop is providing good services in the city; everyone should use newshop",
                        "best services by newshop", "fashionbeats has great services in the city",
                        "I am proud to have fashionbeats", "mymarket has awesome services, Mymarket",
                        "Thanks Newshop for the quick delivery"));

        List<String> competitors = new ArrayList<String>(
                Arrays.asList("newshop", "shopnow", "afashion", "fashionbeats", "mymarket", "tcellular"));

        System.out.println(new Solution().getNCompetitors(6, 2, competitors, 6, reviews));
    }
}
