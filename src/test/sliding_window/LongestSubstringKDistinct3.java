package test.sliding_window;

import java.util.HashMap;
import java.util.Map;
//Find the longest substring with k unique characters in a given string (length)
public class LongestSubstringKDistinct3 {

    public static int longestSubstringKDistinct(String s, int k) {
        // Initialize variables
        int maxLength = 0;
        int start = 0;
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

            // Update the maximum length
            maxLength = Math.max(maxLength, end - start + 1);
        }

        return maxLength;
    }

    public static void main(String[] args) {
        String s = "abcba";
        int k = 2;
        int length = longestSubstringKDistinct(s, k);
        System.out.println("Length of the longest substring with at most " + k +
                " distinct characters: " + length);
    }
}
