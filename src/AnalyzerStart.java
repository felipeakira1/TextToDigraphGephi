import java.io.IOException;
import java.util.Scanner;


public class AnalyzerStart {
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.out.println("Uso incorreto. Forneça o caminho do arquivo de texto e o caminho do arquivo CSV.");
        }

        Scanner reader = new Scanner(System.in);
        String fileNames;
        String[] fullFileNames;

        System.out.println("Type in the file(s) name(s): ");
        fileNames = reader.nextLine();
        fullFileNames = fileNames.split(" ");
        for(int i = 0; i < fullFileNames.length; i++){
            fullFileNames[i] = fullFileNames[i] + ".txt";
        }

        AnalyzerController analyzerController = new AnalyzerController(fullFileNames, ".\\text\\", ".\\csv\\");
        analyzerController.process();
    }
}