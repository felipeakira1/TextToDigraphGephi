package TextAnalyzer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;
import java.util.Map;

public class AnalyzerWriter {
    public void writeFile(String fileName) {
        try {
            AnalyzerReader reader = new AnalyzerReader();
            reader.readFile(fileName); // Chama o método para atualizar o mapa interno

            Map<String, List<String>> adjacencies = reader.getAdjacencies(); // Obtém o mapa interno

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName.replace(".txt", "_digraph.csv")))) {
                for (Map.Entry<String, List<String>> entry : adjacencies.entrySet()) {
                    String word = entry.getKey();
                    List<String> adjacenciesList = entry.getValue();

                    for (String adjacent : adjacenciesList) {
                        writer.write(word + "," + adjacent + "\n");
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
