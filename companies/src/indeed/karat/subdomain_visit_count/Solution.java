package indeed.karat.subdomain_visit_count;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/*
https://leetcode.com/problems/subdomain-visit-count
 */
class Solution {

    public List<String> subdomainVisits(String[] CPDomains) {

        Map<String, Integer> map = new HashMap<>();

        for (String domain : CPDomains) {
            String[] split = domain.split(" ");
            Integer count = Integer.valueOf(split[0].trim());
            String triggeredDomain = split[1].trim();
            String[] domains = triggeredDomain.split("[.]");

            int i = domains.length - 1;
            map.put(domains[i], map.getOrDefault(domains[i], 0) + count);
            i--;
            for (; i >= 0; i--) {
                domains[i] = domains[i] + "." + domains[i + 1];
                map.put(domains[i], map.getOrDefault(domains[i], 0) + count);
            }
        }

        List<String> result = new ArrayList<>();
        map.forEach((key, value) -> result.add(value + " " + key));

        return result;
    }
}
