import java.io.*;
import java.util.*;

public class AnalyzerWriter {
    private final String filePath;

    public AnalyzerWriter(String filePath) {
        this.filePath = filePath;
    }

    public void writeFile(String fileName, Map<String, List<String>> adjacency) {
        try {
            Set<String> writtenWords = new HashSet<>(); // Conjunto para manter o controle das palavras já escritas

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath + fileName.replace(".txt", "_digraph.csv")))) {
                for (Map.Entry<String, List<String>> entry : adjacency.entrySet()) {
                    String word = entry.getKey();
                    List<String> adjacenciesList = entry.getValue();

                    // Verifica se a palavra já foi escrita na primeira coluna
                    if (!writtenWords.contains(word)) {
                        for (String adjacent : adjacenciesList) {
                            writer.write(word + "," + adjacent + "\n");
                        }
                        writtenWords.add(word); // Adiciona a palavra ao conjunto de palavras escritas
                    } else {
                        // Se a palavra já foi escrita, escreva apenas os adjacentes
                        for (String adjacent : adjacenciesList) {
                            writer.write("," + adjacent + "\n");
                        }
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