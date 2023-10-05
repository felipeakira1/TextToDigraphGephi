package textanalyzer;

import java.io.*;
import java.util.*;

/**
 * Class: textanalyzer.AnalyzerWriter
 *
 * <p>
 *     Gets the TreeMap created by AnalyzerReader class and generates a .csv file with it with each line starting
 *     with a word, that will NOT repeat under any circumstance in the file, followed by its adjacent ones found
 *     throughout the text, which will also not repeat, with only the first occurrence of said adjacent.
 * </p>
 *
 * @author Miguel Donanzam - m260851@dac.unicamp.br
 * @author Julio Morino - j173434@dac.unicamp.br
 * @author Felipe Akira - f172885@dac.unicamp.br
 */

public class AnalyzerWriter {
    /**
     * Stores the path to the directory where the created .csv files should be stored.
     */
    private final String filePath;

    /**
     * Initializes the class variable with its respective value passed as a parameter.
     *
     * @param filePath receives the path to the directory where the created .csv files should be stored.
     */
    public AnalyzerWriter(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Gets the adjacency list from AnalyzerReader, calls function orderAndRemoveDuplicatesFromSet() to order and
     * remove duplicates, then proceeds to generate a .csv file with the name designed in fileName parameter with
     * all the words and their adjacent separated by a comma in each line. In case the process fails, an exception
     * is thrown and the program is terminated.
     *
     * @param fileName receives the name that should be given to the new file to be generated and saved in .csv form
     *                 in directory ./files/csv/
     */
    public void writeFile(String fileName) {
        try {
            AnalyzerReader reader = new AnalyzerReader(".\\files\\text\\");
            reader.readFile(fileName);

            Map<String, Set<String>> adjacency = reader.getAdjacency();
            Map<String, List<String>> orderedAdjacency = AnalyzerOrderMap.orderAndRemoveDuplicatesFromSet(adjacency);

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath + fileName.replace(".txt", "_digraph.csv")))) {
                for (Map.Entry<String, List<String>> entry : orderedAdjacency.entrySet()) {
                    String word = entry.getKey();

                    List<String> adjacenciesList = entry.getValue();

                    if (word != null && !word.isEmpty()) {
                        String adjacenciesString = String.join(",", adjacenciesList);
                        writer.write(word + "," + adjacenciesString + "\n");
                    }
                }
                System.out.println("CSV file generated successfully: " + fileName.replace(".txt", "_digraph.csv"));
            }
        } catch (Exception e) {
            System.err.println("\nError while writing the file: " + fileName.replace(".txt", "_digraph.csv"));
            System.err.println("Error details: " + e.getMessage());
            e.printStackTrace();
        }
    }
}