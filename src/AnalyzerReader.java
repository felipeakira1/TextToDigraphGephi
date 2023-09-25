import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class AnalyzerReader {
    private final String filePath;
    private final TreeMap<String, List<String>> adjacencies; // Usando TreeMap para manter as chaves ordenadas

    public AnalyzerReader(String filePath) {
        this.filePath = filePath;
        adjacencies = new TreeMap<>();
    }

    public void readFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath + fileName))) {
            String line;
            String previousWord = null;

            while ((line = reader.readLine()) != null) {
                // Split the line into words using spaces as delimiters
                String[] wordsInLine = line.split(" ");

                for (String word : wordsInLine) {
                    word = word.toLowerCase(); // Convert to lowercase to avoid case sensitivity

                    if (previousWord != null) {
                        // Add the word to the adjacency list of the previous word
                        adjacencies.computeIfAbsent(previousWord, k -> new ArrayList<>()).add(word);
                    }

                    previousWord = word;
                }
            }
        } catch (Exception e) {
            System.err.println("Error while reading the file: " + fileName);
            e.printStackTrace();
        }
    }

    public Map<String, List<String>> getAdjacency() {
        return adjacencies;
    }
}
