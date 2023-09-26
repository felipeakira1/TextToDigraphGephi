import java.io.BufferedReader;
import java.io.FileReader;
import java.text.Normalizer;
import java.util.*;

/**
 * Class: AnalyzerReader
 * @author Miguel Donanzam - m260851@dac.unicamp.br
 * @author Julio Morino - j173434@dac.unicamp.br
 */

public class AnalyzerReader {
    private final String filePath;
    private final TreeMap<String, Set<String>> adjacency;

    /**
     * @param filePath stores the path to the directory where the .txt files to be read are located.
     */
    public AnalyzerReader(String filePath) {
        this.filePath = filePath;
        adjacency = new TreeMap<>();
    }

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
        } catch (Exception e) {
            System.err.println("Error while reading the file: " + fileName);
            e.printStackTrace();
        }
    }

    /**
     * @return adjacent words to words written in first column of .csv files to class AnalyzerWriter objects.
     */
    public Map<String, Set<String>> getAdjacency() {
        return adjacency;
    }

    public static String[] filterLine(String[] array) {
        String[] resultado = new String[array.length];

        for (int i = 0; i < array.length; i++) {
            String texto = array[i];

            texto = Normalizer.normalize(texto, Normalizer.Form.NFD);
            texto = texto.replaceAll("\\p{InCombiningDiacriticalMarks}", "");

            texto = texto.replaceAll("[^\\\\sa-zA-Z0-9]", "");

            resultado[i] = texto;
        }

        return resultado;
    }
}
