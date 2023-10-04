package analyzer;

import java.util.*;

/**
 * Class: AnalyzerReader
 * @author Julio Morino - j173434@dac.unicamp.br
 */

public class OrderMap {
    public static Map<String, List<String>> orderAndRemoveDuplicatesFromSet(Map<String, Set<String>> inputMap) {
        // Crie um TreeMap para ordenar as chaves em ordem alfabética
        Map<String, List<String>> orderedMap = new TreeMap<>();

        // Remova duplicatas nas listas de adjacências
        for (Map.Entry<String, Set<String>> entry : inputMap.entrySet()) {
            List<String> adjacencies = new ArrayList<>(entry.getValue()); // Converter Set para List
            adjacencies.sort(String.CASE_INSENSITIVE_ORDER); // Ordenar a lista
            orderedMap.put(entry.getKey(), adjacencies);
        }

        return orderedMap;
    }
}
