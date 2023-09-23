package TextAnalyzer;

public class AnalyzerController {
    private String[] fileNames;

    public AnalyzerController(String[] fileNames) {
        this.fileNames = fileNames;
    }

    public void processFiles() {
        try {
            AnalyzerReader reader = new AnalyzerReader();
            AnalyzerWriter writer = new AnalyzerWriter();

            for (String fileName : fileNames) {
                reader.readFile(fileName);
                writer.writeFile(fileName);
            }
        } catch (Exception e) {
            System.err.println("Error during file processing:");
            e.printStackTrace();
        }
    }
}
