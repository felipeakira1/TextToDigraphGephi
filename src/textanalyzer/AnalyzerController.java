package textanalyzer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Class: textanalyzer.AnalyzerController
 *
 * <p>
 *     Acts as a driver that coordinates the reading, writing, and processing of text files, allowing file names to be
 *     supplied by the user or passed as arguments as needed. It also handles cases where filenames are not found and
 *     provides information about these cases.
 * </p>
 *
 * @author Miguel Donanzam - m260851@dac.unicamp.br
 * @author Julio Morino - j173434@dac.unicamp.br
 * @author Felipe Akira - f172885@dac.unicamp.br
 */

public class AnalyzerController {
	/**
	 * Stores the file names gotten from user file name input
	 */
	private final ArrayList<String> fileNamesUser;

	/**
	 * Stores the file names passed as arguments to main function
	 */
	private final String[] fileNamesArgs;

	/**
	 * Stores the file names that were not present among the ones passed as arguments to main function
	 */
	private ArrayList<String> notFoundFiles;
	
    /**
	 * Initializes the class' variables with their respective values.
	 *
     * @param fileNamesArgs receives the file names from class analyzer.AnalyzerStart to then start file processing.
     */
    public AnalyzerController(String[] fileNamesArgs) {
    	this.fileNamesUser = new ArrayList<>();
    	this.fileNamesArgs = fileNamesArgs;
    	this.notFoundFiles = new ArrayList<>();
    }

	/**
	 * Opens an input stream with a Scanner type object and gets user file name input,
	 * and splits the resulting string into multiple ones by space then attributes it
	 * to String[] splitFileNames
	 */
    public void typeFileNamesForConversion() {
    	Scanner readFileNames = new Scanner(System.in);
    	System.out.print("\nType the name(s) of the file(s): ");

		if (readFileNames.hasNextLine()) {
			String fileNames = readFileNames.nextLine();

			String[] splitFileNames = fileNames.split(" ");

			for(String splitFileName : splitFileNames) {
				fileNamesUser.add(splitFileName + ".txt");
			}
		}
		else {
			readFileNames.next();
		}
    }

	/**
	 * Gets all the file names the user input, verifies if they are within the arguments passed to main function
	 * and then sends it to function processFile(). Otherwise, it adds the name of said file to an ArrayList named
	 * notFoundFiles that will later be read and have the names of the not found file displayed on terminal.
	 */
    public void processFileNames() {
    	for(String fileNameUser : fileNamesUser) {
        	boolean isValidFileName = false;
        	for(String fileNameArgs : fileNamesArgs) {
        		if(fileNameUser.equals(fileNameArgs)) {
        			isValidFileName = true;
        			processFile(fileNameUser);
        			break;
        		}
        	}
        	if(!isValidFileName) {
        		if(notFoundFiles == null) {
        			notFoundFiles = new ArrayList<>();
        		}
        		notFoundFiles.add(fileNameUser);
        	}
    	}
    	System.out.println();

    	if(!notFoundFiles.isEmpty()) {
    		Iterator<String> it = notFoundFiles.iterator();
            System.out.print("The following file(s) couldn't be found: ");
            if(it.hasNext()){
                System.out.print(it.next());
            }
            while(it.hasNext()){
                System.out.print(", " + it.next());
            }
            System.out.println("\n");
    	}
	}
    
    /**
     * Uses objects of type analyzer.AnalyzerReader and analyzer.AnalyzerWriter and tries to read the .txt files in directory "text"
     * and generates a digraphs out of them in .csv form, saving them in directory "csv" for later processing using Gephi.
	 *
	 * @param fileName receives the name of the file to be processed.
     */
    public static void processFile(String fileName) {
        try {
            AnalyzerReader reader = new AnalyzerReader(".\\files\\text\\");
            AnalyzerWriter writer = new AnalyzerWriter(".\\files\\csv\\");

            reader.readFile(fileName);
            writer.writeFile(fileName);
            
        } catch (Exception e) {
            System.err.println("Error during file processing:");
            e.printStackTrace();
        }
    }

	/**
	 * Gets all the file names passed as arguments to main function and passes them as arguments to
	 * readFile and writeFile functions
	 */
    public void processAllFiles() {
    	try {
    		AnalyzerReader reader = new AnalyzerReader(".\\files\\text\\");
            AnalyzerWriter writer = new AnalyzerWriter(".\\files\\csv\\");
            
            for(String fileName : fileNamesArgs) {
            	reader.readFile(fileName);
                writer.writeFile(fileName);
            }
    	} catch(Exception e) {
    		System.err.println("An error ocurred.\nError details: ");
    		e.printStackTrace();
    	}
    	System.out.println();
    }
}
