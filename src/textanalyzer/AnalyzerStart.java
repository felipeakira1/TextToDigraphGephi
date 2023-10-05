package textanalyzer;

/**
 * Class: textanalyzer.AnalyzerStart
 *
 * <p>
 *     Responsible for initializing the program.
 * </p>
 *
 * @author Miguel Donanzam - m260851@dac.unicamp.br
 * @author Julio Morino - j173434@dac.unicamp.br
 * @author Felipe Akira - f172885@dac.unicamp.br
 */

public class AnalyzerStart {

    /**
     * Initializes the program.
     *
     * @param args string array that stores the names of files to be analyzed.
     */
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please, provide at least one input file name.");
            return;
        }

        AnalyzerMenu menu = new AnalyzerMenu(args);
        menu.displayMenu();
    }
}