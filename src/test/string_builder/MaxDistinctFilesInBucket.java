package test.string_builder;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MaxDistinctFilesInBucket {
    public static void main(String[] args) {
        String[] command = {"goto bucketA", "create fileA", "create fileB", "create fileA", "goto bucketB", "create fileA", "create fileB", "create fileC", "goto bucketC", "create fileA", "create fileB"};

        // Map to store the count of distinct files in each bucket
        Map<String, Integer> fileCounts = new HashMap<>();

        // Set to store the distinct files in the current bucket
        Set<String> distinctFiles = new HashSet<>();

        // Iterate over the array of commands
        for (String cmd : command) {
            if (cmd.startsWith("goto")) {
                // We have moved to a new bucket, update the fileCounts map
                String bucketName = cmd.split(" ")[1];
                fileCounts.put(bucketName, distinctFiles.size());
                // Clear the set for the new bucket
                distinctFiles.clear();
            } else if (cmd.startsWith("create")) {
                // Add the file to the current bucket's set of distinct files
                String fileName = cmd.split(" ")[1];
                distinctFiles.add(fileName);
            }
        }

        // Update the fileCounts map for the last bucket in the array
        String lastBucket = command[command.length-1].split(" ")[1];
        fileCounts.put(lastBucket, distinctFiles.size());

        // Find the bucket with the maximum number of distinct files
        int maxDistinctFiles = -1;
        String maxBucket = null;
        for (Map.Entry<String, Integer> entry : fileCounts.entrySet()) {
            if (entry.getValue() > maxDistinctFiles) {
                maxDistinctFiles = entry.getValue();
                maxBucket = entry.getKey();
            }
        }

        // Print the result
        System.out.println("Bucket with maximum distinct files: " + maxBucket);
    }
}
