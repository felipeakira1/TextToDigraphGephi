import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Class: AnalyzerStart
 * @author Miguel Donanzam - m260851@dac.unicamp.br
 * @author Julio Morino - j173434@dac.unicamp.br
 */

public class AnalyzerStart {

    /**
     * @param args string array that stores the names of files to be analyzed.
     */
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please provide at least one input file name.");
            return;
        }

        Scanner optionReader = new Scanner(System.in);
        int choice;
        boolean finishProgram = false;

        while(!finishProgram) {
            System.out.println("======================MENU======================");
            System.out.println("1. Type in file names for .csv convertion");
            System.out.println("2. Convert all files to .csv form");
            System.out.println("3. Exit");
            System.out.println("================================================");
            System.out.print("Option: ");
            choice = optionReader.nextInt();

            switch (choice) {
                case 1:
                    System.out.println();
                    Scanner readFileNames = new Scanner(System.in);

                    System.out.print("Type the name(s) of the file(s): ");
                    String fileNames = readFileNames.nextLine();
                    String[] splitFileNames = fileNames.split(" ");
                    ArrayList<String> notFound = new ArrayList<>();


                    for (String splitFileName : splitFileNames) {
                        splitFileName = splitFileName + ".txt";
                        boolean found = false;

                        for (String fileName : args) {
                            if (splitFileName.equals(fileName)) {
                                AnalyzerController controller = new AnalyzerController(new String[]{splitFileName});
                                controller.processFiles();
                                found = true;
                                break;
                            }
                        }

                        if (!found) {
                            notFound.add(splitFileName);
                        }
                    }

                    Iterator<String> it = notFound.iterator();
                    System.out.print("\nThe following files couldn't be found: ");
                    if(it.hasNext()){
                        System.out.print(it.next());
                    }
                    while(it.hasNext()){
                        System.out.print(", " + it.next());
                    }
                    System.out.println("\n");

                    break;
                case 2:
                    for (String fileName : args) {
                        AnalyzerController controller = new AnalyzerController(new String[]{fileName});
                        controller.processFiles();
                    }
                    finishProgram = true;
                    break;
                case 3:
                    finishProgram = true;
                    break;
                default:
                    System.err.println("Invalid option!");
            }
        }
    }
}