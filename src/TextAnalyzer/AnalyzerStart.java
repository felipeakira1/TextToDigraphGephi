package TextAnalyzer;

public class AnalyzerStart {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please provide at least one input file name.");
            return;
        }

        for (String fileName : args) {
            AnalyzerController controller = new AnalyzerController(new String[]{fileName});
            controller.processFiles();
        }
    }
}
