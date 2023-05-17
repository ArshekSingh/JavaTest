package test.sliding_window;

import java.util.HashMap;
import java.util.Map;
//Find the longest substring with k unique characters in a given string
public class LongestSubstringKDistinct {
    public static String longestSubstringKDistinct(String s, int k) {
        // Initialize variables
        int maxLength = 0;
        int start = 0;
        int maxStart = 0;
        Map<Character, Integer> charCount = new HashMap<>();

        // Iterate over the string
        for (int end = 0; end < s.length(); end++) {
            char c = s.charAt(end);

            // Update the character count
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);

            // Shrink the window if the number of distinct characters exceeds K
            while (charCount.size() > k) {
                char leftChar = s.charAt(start);
                charCount.put(leftChar, charCount.get(leftChar) - 1);
                if (charCount.get(leftChar) == 0) {
                    charCount.remove(leftChar);
                }
                start++;
            }

            // Update the maximum length and starting index
            if (end - start + 1 > maxLength) {
                maxLength = end - start + 1;
                maxStart = start;
            }
        }

        return s.substring(maxStart, maxStart + maxLength);
    }

    public static void main(String[] args) {
        String s = "aaabbcccceeaaaaa";
        int k = 2;
        String longestSubstring = longestSubstringKDistinct(s, k);
        System.out.println("Longest substring with " + k + " distinct characters: " + longestSubstring);
    }
}