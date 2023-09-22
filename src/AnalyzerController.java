import java.io.IOException;
import java.util.List;
import java.util.Map;

public class AnalyzerController {
    private final String[] fileNames;
    private final String textFilePath;
    private final String csvFilePath;

    public AnalyzerController(String[] fileNames, String textFilePath, String csvFilePath) {
        this.fileNames = fileNames;
        this.textFilePath = textFilePath;
        this.csvFilePath = csvFilePath;
    }

    public void process() throws IOException {
        AnalyzerReader reader = new AnalyzerReader(textFilePath);
        AnalyzerWriter writer = new AnalyzerWriter(csvFilePath);
        for(String name : fileNames){
            Map<String, List<String>> adjacencies = reader.readFile(name);
            writer.writeFile(name, adjacencies);
        }
    }
}
