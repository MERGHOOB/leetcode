package indeed.karat.sequence_of_actions_yields_error;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
"""
Given a collection of actions and userIds, an error occurs when a userId takes
a specific action in order for example,

A => B => C : This sequence of actions yields error

Write a function that takes in a list of (Actions, UserIds) pairs and returns the user Id that ecounters the error

Sample Input:

action_user_1 = [
["A", "1"], <- A(1)
["B", "1"], <- B(1)
["B", "2"],
["C", "1"], <- C(1)
["C", "2"],
["C", "3"],
["A", "2"],  <- A(2)
["A", "3"],
["A", "2"],
["B", "2"],  <- B(2)
["C", "2"], <- C(2)
]

Error action -> ["ABC"]
Expected output -> [1,2]

action_user_2 = [
["A", "1"],
["A", "1"]
["A", "1"]
["B", "1"],
["B", "2"], <- B
["C", "2"],
["C", "2"],
["C", "3"],
["A", "2], <- A
["A", "3"],
["A", "2"],
["B", "2],
["C", "2"], <- C
]

Error action -> ["BAC"]
Expected output -> [2]
"""

 */
class Solution {


    public List<String> findUserIds(String[][] actions, String errorString) {

        Map<String, List<String>> userIdSequences = new HashMap<>();
        for (String[] action : actions) {
            userIdSequences.putIfAbsent(action[1], new ArrayList<>());
            userIdSequences.get(action[1]).add(action[0]);
        }
        List<String> erroredUIDs = new ArrayList<>();
        for (String userId : userIdSequences.keySet()) {
            if (isSubSequence(errorString.toCharArray(), userIdSequences.get(userId))) {
                erroredUIDs.add(userId);
            }
        }
        return erroredUIDs;
    }

    private boolean isSubSequence(char[] errorString, List<String> actions) { //o(n)
        int i = 0, j = 0;
        while (i < actions.size() && j < errorString.length) {

            if (actions.get(i).equals(Character.toString(errorString[j]))) {
                i++;
                j++;
            } else {
                i++;
            }
        }
        return j == errorString.length;
    }
}
