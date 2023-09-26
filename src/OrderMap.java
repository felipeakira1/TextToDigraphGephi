import java.util.*;

public class OrderMap {
	/**
    public static Map<String, List<String>> orderAndRemoveDuplicatesFromList(Map<String, List<String>> inputMap) {
        // Crie um TreeMap para ordenar as chaves em ordem alfabética
        Map<String, List<String>> orderedMap = new TreeMap<>(inputMap);

        // Remova duplicatas nas listas de adjacências
        for (Map.Entry<String, List<String>> entry : orderedMap.entrySet()) {
            List<String> adjacencies = entry.getValue();
            Set<String> uniqueAdjacencies = new LinkedHashSet<>(adjacencies); // LinkedHashSet para manter a ordem de inserção
            entry.setValue(new ArrayList<>(uniqueAdjacencies));
        }

        return orderedMap;
    }
	*/
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
