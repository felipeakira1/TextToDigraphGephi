import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class AnalyzerWriter {
    private final String csvFilePath;

    public AnalyzerWriter(String csvFilePath){
        this.csvFilePath = csvFilePath;
    }
    public void writeFile(String fileName, Map<String, List<String>> adjacencies) throws IOException {
        try {
            String nomeCSV = fileName.replace(".txt", "_digrafo.csv");
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter((csvFilePath + nomeCSV)));

            for (Map.Entry<String, List<String>> stringListEntry : adjacencies.entrySet()) {
                String word = stringListEntry.getKey();
                List<String> adjacenciesList = stringListEntry.getValue();

                for (String adjacent : adjacenciesList) {
                    bufferedWriter.write(word + "," + adjacent + "\n");
                }
            }

            System.out.println("Arquivo CSV gerado com sucesso: " + nomeCSV);
        } catch (IOException e){
            System.err.println("Erro ao processar o arquivo: " + fileName);
        }
    }
}
