package TextAnalyzer;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class AnalyzerReader {
    private Map<String, Set<String>> adjacencies;

    public AnalyzerReader() {
        adjacencies = new HashMap<>();
    }

    public void readFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            String previousWord = null;

            while ((line = reader.readLine()) != null) {
                // Split the line into words using spaces as delimiters
                String[] wordsInLine = line.split(" ");

                for (String word : wordsInLine) {
                    word = word.toLowerCase(); // Convert to lowercase to avoid case sensitivity

                    if (previousWord != null && !previousWord.isEmpty()) {
                        // Add the word to the adjacency list of the previous word
                        adjacencies.computeIfAbsent(previousWord, k -> new LinkedHashSet<>()).add(word);
                    }

                    previousWord = word;
                }
            }
        } catch (Exception e) {
            System.err.println("Error while reading the file: " + fileName);
            e.printStackTrace();
        }
    }

    public Map<String, Set<String>> getAdjacencies() {
        return adjacencies;
    }
}

