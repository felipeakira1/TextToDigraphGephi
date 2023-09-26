
/**
 * Class: AnalyzerController
 * @author Miguel Donanzam - m260851@dac.unicamp.br
 * @author Julio Morino - j173434@dac.unicamp.br
 */

public class AnalyzerController {
    private final String[] fileNames;

    /**
     * @param fileNames receives the file names from class AnalyzerStart to then start file processing.
     */
    public AnalyzerController(String[] fileNames) {
        this.fileNames = fileNames;
    }

    /**
     * Uses objects of type AnalyzerReader and AnalyzerWriter and tries to read the .txt files in directory "text"
     * and generates a digraphs out of them in .csv form, saving them in directory "csv" for later processing using Gephi.
     */
    public void processFiles() {
        try {
            AnalyzerReader reader = new AnalyzerReader(".\\files\\text\\");
            AnalyzerWriter writer = new AnalyzerWriter(".\\files\\csv\\");

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
