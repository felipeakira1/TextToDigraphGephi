import java.io.*;
import java.util.*;

/**
 * Class: AnalyzerWriter
 * @author Miguel Donanzam - m260851@dac.unicamp.br
 * @author Julio Morino - j173434@dac.unicamp.br
 * @author Felipe Akira - f172885@dac.unicamp.br
 */

public class AnalyzerWriter {
    private final String filePath;

    /**
     * @param filePath stores the path to the directory where the created .csv files should be stored.
     */
    public AnalyzerWriter(String filePath) {
        this.filePath = filePath;
    }

    public void writeFile(String fileName) {
        try {
            AnalyzerReader reader = new AnalyzerReader(".\\files\\text\\");
            reader.readFile(fileName);

            Map<String, Set<String>> adjacency = reader.getAdjacency();

            // Use a classe OrderMap para ordenar e remover duplicatas
            Map<String, List<String>> orderedAdjacencies = OrderMap.orderAndRemoveDuplicatesFromSet(adjacency);

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath + fileName.replace(".txt", "_digraph.csv")))) {
                for (Map.Entry<String, List<String>> entry : orderedAdjacencies.entrySet()) {
                    String word = entry.getKey();
                    boolean isFirstWord = true;

                    List<String> adjacenciesList = entry.getValue();

                    if (word != null && !word.isEmpty()) {
                        String adjacenciesString = String.join(",", adjacenciesList);
                        writer.write(word + "," + adjacenciesString + "\n");
                    }
                }
                System.out.println("CSV file generated successfully: " + fileName.replace(".txt", "_digraph.csv"));
            }
        } catch(FileNotFoundException e) {
        	System.err.println("File not found: " + fileName);
            System.err.println("More details: ");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Error reading the file: " + fileName);
            System.err.println("More details: ");
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.err.println("Invalid file name: " + fileName);
            System.err.println("More details: ");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("An error has ocurred. ");
            System.err.println("More details: ");
            e.printStackTrace();
        }
    }
}