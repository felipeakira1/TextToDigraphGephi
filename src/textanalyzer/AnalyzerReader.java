package textanalyzer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Class: textanalyzer.AnalyzerReader
 *
 * <p>
 *     Reads .txt files and saves each occurrence of each word and the first occurrence of each
 *     of their adjacent words in a TreeMap that will be later processed by AnalyzerWriter class
 *     and have a .csv file generated with it.
 * </p>
 *
 * @author Miguel Donanzam - m260851@dac.unicamp.br
 * @author Julio Morino - j173434@dac.unicamp.br
 * @author Felipe Akira - f172885@dac.unicamp.br
 */

public class AnalyzerReader {
    /**
     * Stores the path to the .txt files to be read.
     */
    private final String filePath;

    /**
     * Stores all the adjacent words to the words in first column.
     */
    private final TreeMap<String, Set<String>> adjacency;

    /**
     * Initializes the class' variables with their respective values passed as parameters.
     *
     * @param filePath stores the path to the directory where the .txt files to be read are located.
     */
    public AnalyzerReader(String filePath) {
        this.filePath = filePath;
        adjacency = new TreeMap<>();
    }

    /**
     * Reads each line of the file designated in parameter fileName and splits all the words in a line into multiple
     * strings, filters away any punctuation, spaces and hypes with function filterLine(), converts the to lower case
     * and the adds them to an adjacency list in which all the words place after the words in the first column are
     * adjacent to the ones in the first column.
     *
     * @param fileName receives the current name of the file to be read.
     */
    public void readFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath + fileName))) {
            String line;
            String previousWord = null;

            while ((line = reader.readLine()) != null) {
                // Split the line into words using spaces as delimiters
                String[] wordsInLine = line.split(" ");

                wordsInLine = filterLine(wordsInLine);

                for (String word : wordsInLine) {
                    word = word.toLowerCase(); // Convert to lowercase to avoid case sensitivity

                    if (previousWord != null) {
                        // Add the word to the adjacency list of the previous word
                        adjacency.computeIfAbsent(previousWord, k -> new LinkedHashSet<>()).add(word);
                    }

                    previousWord = word;
                }
            }
        } catch(FileNotFoundException e) {
        	System.err.println("File not found: " + fileName);
        } catch (IOException e) {
        	System.err.println("Error while reading the file: " + fileName);
            System.err.println("Error details: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
        	System.err.println("An unexpected error ocurred while reading the file: " + fileName);
        	System.err.println("Error details: " + e.getMessage());
        	e.printStackTrace();
        }
    }

    /**
     * Returns the adjacency list created in function readFile().
     *
     * @return adjacent words to words written in first column of .csv files to class analyzer.AnalyzerWriter objects.
     */
    public Map<String, Set<String>> getAdjacency() {
        return adjacency;
    }

    /**
     * Strips away any type of punctuation, spaces and hyphens, leaving only the words themselves.
     *
     * @param array receives each line read from the current .txt file on read.
     * @return returns the filtered line.
     */
    public static String[] filterLine(String[] array) {
        String[] result = new String[array.length];
        for (int i = 0; i < array.length; i++) {
            String text = array[i];
            text = text.replaceAll("[^\\\\s\\p{L}\\p{M}a-zA-Z0-9]", "");
            result[i] = text;
        }
        return result;
    }
}
