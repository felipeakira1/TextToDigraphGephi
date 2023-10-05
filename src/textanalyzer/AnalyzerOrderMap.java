package textanalyzer;

import java.util.*;

/**
 * Class: textanalyzer.AnalyzerReader
 *
 * <p>
 *     Responsible for putting items in an adjacency list in order.
 * </p>
 *
 * @author Julio Morino - j173434@dac.unicamp.br
 * @author Miguel Donanzam - m260851@dac.unicamp.br
 */

public class AnalyzerOrderMap {
    /**
     * Takes all the words in an adjacency list put them in alphabetic order and removes duplicates.
     *
     * @param inputMap receives the resulting adjacency list from getAdjacency() and orders it alphabetically
     * @return returns the alphabetically ordered adjacency list
     */
    public static Map<String, List<String>> orderAndRemoveDuplicatesFromSet(Map<String, Set<String>> inputMap) {
        Map<String, List<String>> orderedMap = new TreeMap<>();

        for (Map.Entry<String, Set<String>> entry : inputMap.entrySet()) {
            List<String> adjacency = new ArrayList<>(entry.getValue());
            adjacency.sort(String.CASE_INSENSITIVE_ORDER);
            orderedMap.put(entry.getKey(), adjacency);
        }

        return orderedMap;
    }
}
