import java.util.List;
import java.util.Map;

public class AnalyzerController {
    private final String[] fileNames;

    public AnalyzerController(String[] fileNames) {
        this.fileNames = fileNames;
    }

    public void processFiles() {
        try {
            for (String fileName : fileNames) {
                AnalyzerReader reader = new AnalyzerReader(".\\text\\");
                reader.readFile(fileName);
                Map<String, List<String>> adjacency = reader.getAdjacency();

                AnalyzerWriter writer = new AnalyzerWriter(".\\csv\\");
                writer.writeFile(fileName, adjacency);
            }
        } catch (Exception e) {
            System.err.println("Error during file processing:");
            e.printStackTrace();
        }
    }
}
