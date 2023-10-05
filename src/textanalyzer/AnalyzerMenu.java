package textanalyzer;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Class: textanalyzer.AnalyzerStart
 *
 * <p>
 *     Prints out a menu on terminal displaying options for the user and lets them decide
 *     what action they want to take during execution.
 * </p>
 *
 * @author Miguel Donanzam - m260851@dac.unicamp.br
 * @author Julio Morino - j173434@dac.unicamp.br
 * @author Felipe Akira - f172885@dac.unicamp.br
 */

public class AnalyzerMenu {
    /**
     * Stores the file names received from String[] args from main function in class AnalyzerStart.
     */
    private final String[] fileNamesArgs;

    /**
     * Initializes the class variable with its respective value passed as a parameter.
     *
     * @param fileNamesArgs will receive all the file names from String[] args in main function in class AnalyzerStart.
     */
    public AnalyzerMenu(String[] fileNamesArgs) {
        this.fileNamesArgs = fileNamesArgs;
    }

    /**
     * Displays a menu with three different options for using the program.
     * The first option opens an input stream using a Scanner type object so that the system can get user
     * file name input;
     * The second option goes through all the text files in ./files/text/ directory and converts them all into
     * a .csv file for later processing using Gephi.
     * The third option is completely dedicated to finishing the program's execution.
     */
    public void displayMenu() {
        int choice;
        Scanner optionReader = new Scanner(System.in);

        System.out.println("======================MENU======================");
        System.out.println("1. Type in file names for .csv conversion");
        System.out.println("2. Convert all files to .csv form");
        System.out.println("3. Exit");
        System.out.println("================================================");
        System.out.print("Option: ");
        choice = optionReader.nextInt();

        try {
            switch (choice) {
                case 1:
                    AnalyzerController controller = new AnalyzerController(fileNamesArgs);
                    controller.typeFileNamesForConversion();
                    controller.processFileNames();
                    displayMenu();
                    break;

                case 2:
                    AnalyzerController controller2 = new AnalyzerController(fileNamesArgs);
                    controller2.processAllFiles();
                    displayMenu();
                    break;

                case 3:
                	optionReader.close();
                    return;

                default:
                    System.out.println("Invalid option!");
            }
        } catch (InputMismatchException e) {
            System.err.println("Invalid input. Please enter a valid number.");
        } catch (NoSuchElementException e) {
            System.err.println("Input not found. Please enter a valid option.");
        } catch (Exception e) {
            System.err.println("An unexpected error occurred");
            System.err.println("Error details: " + e.getMessage());
            e.printStackTrace();
        }

        optionReader.close();
    }
}
