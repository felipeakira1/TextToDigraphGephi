package TextAnalyzer;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.*;
import java.util.stream.Collectors;

public class AnalyzerWriter {
    public void writeFile(String fileName) {
        try {
            AnalyzerReader reader = new AnalyzerReader();
            reader.readFile(fileName);

            Map<String, Set<String>> adjacencies = reader.getAdjacencies();

            // Use a classe OrderMap para ordenar e remover duplicatas
            Map<String, List<String>> orderedAdjacencies = OrderMap.orderAndRemoveDuplicatesFromSet(adjacencies);

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName.replace(".txt", "_digraph.csv")))) {
                for (Map.Entry<String, List<String>> entry : orderedAdjacencies.entrySet()) {
                    String word = entry.getKey();
                    
                    List<String> adjacenciesList = entry.getValue();

                    if (word != null && !word.isEmpty()) {
                        String adjacenciesString = String.join(", ", adjacenciesList);
                        writer.write(word + ", " + adjacenciesString + "\n");
                    }
                }
                System.out.println("CSV file generated successfully: " + fileName.replace(".txt", "_digraph.csv"));
            }
        } catch (Exception e) {
            System.err.println("Error while writing the file: " + fileName.replace(".txt", "_digraph.csv"));
            e.printStackTrace();
        }
    }
}
