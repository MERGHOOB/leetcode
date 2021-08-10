package leetcode_text_justification;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public static void main(String[] args) {
        String[] text1 = {"It underscores our responsibility",
                "to deal more kindly with one another"};
        int lineLength1 = 15;
        String[] text2 = {"The Earth is",
                "the only world",
                "known so far",
                "to harbor life"};
        int lineLength2 = 18;
        String[] text3 = {"Some modern typesetting programs",
                "offer four justification",
                "options"};
        int lineLength3 = 24;
//
        System.out.println(new Solution().fullJustify(text1, lineLength1));
        System.out.println(new Solution().fullJustify(text2, lineLength2));
        System.out.println(new Solution().fullJustify(text3, lineLength3));
    }
    public List<String> fullJustify(String[] words, int maxWidth) {

        List<String> lines = new ArrayList<>();

        int currWidth = 0;
        List<String> collectedWords = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (currWidth + word.length() + 1 <= maxWidth + 1) {
                currWidth += (word.length() + 1);
                collectedWords.add(word);

            } else {
                String line = createLine(collectedWords, maxWidth, currWidth, false);
                lines.add(line);
                currWidth = 0;
                collectedWords.clear();
                i--;
            }
        }
        String line = createLine(collectedWords, maxWidth, currWidth, true);
        lines.add(line);

        return lines;
    }

    private String lastLine(List<String> words, int maxWidth, int currWidth) {
        StringBuilder line = new StringBuilder();

        for (String word : words) {
            line.append(word);
            maxWidth -= word.length();
            if (maxWidth > 0) {
                line.append(" ");
                maxWidth--;
            }
        }

        while (maxWidth-- > 0) {
            line.append(" ");
        }

        return line.toString();
    }

    String createLine(List<String> words, int maxWidth, int currWidth, boolean isLastLine) {
        if (isLastLine || words.size() == 1) {

            return lastLine(words, maxWidth, currWidth);
        }
        int space = maxWidth - currWidth + words.size();
        int gaps = words.size() - 1;
        int minSpace = space / gaps;
        int restSpace = space % gaps;

        StringBuilder line = new StringBuilder();
        for (String word : words) {
            line.append(word);


            //
            int temp = minSpace;
            while (gaps > 0 && temp-- > 0) {
                line.append(" ");
            }
            gaps--;
            if (restSpace-- > 0) {
                line.append(" ");
            }
        }


        return line.toString();

    }
}