import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnalyzerReader {

    private final String textFilePath;

    public AnalyzerReader(String textFilePath){
        this.textFilePath = textFilePath;
    }
    public Map<String, List<String>> readFile(String fileName) {
        Map<String, List<String>> writerAdjacencies = new HashMap<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(textFilePath + fileName));
            String linha;
            Map<String, List<String>> adjacencias = new HashMap<>();

            String palavraAnterior = null;

            while ((linha = bufferedReader.readLine()) != null) {
                // Divide a linha em palavras usando espaços como delimitadores
                String[] palavrasLinha = linha.split(" ");

                for (String palavra : palavrasLinha) {
                    palavra = palavra.toLowerCase(); // Converter para minúsculas para evitar diferenciação de maiúsculas e minúsculas

                    if (palavraAnterior != null) {
                        // Adicione palavra à lista de adjacências da palavra anterior
                        adjacencias.computeIfAbsent(palavraAnterior, k -> new ArrayList<>()).add(palavra);
                    }

                    palavraAnterior = palavra;
                }
            }
            writerAdjacencies = adjacencias;
        } catch (IOException e){
            System.err.println("Erro ao processar o arquivo: " + fileName);
            e.printStackTrace();
        }

        return writerAdjacencies;
    }
}
