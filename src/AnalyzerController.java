import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Class: AnalyzerController
 * @author Miguel Donanzam - m260851@dac.unicamp.br
 * @author Julio Morino - j173434@dac.unicamp.br
 */

public class AnalyzerController {
	private ArrayList<String> fileNamesUser;
	private String[] fileNamesArgs; // Array de nome de arquivos dos argumentos
	private ArrayList<String> notFoundFiles;
	
    /**
     * @param fileNames receives the file names from class AnalyzerStart to then start file processing.
     */
    public AnalyzerController(String[] fileNamesArgs) {
    	this.fileNamesUser = new ArrayList<>();
    	this.fileNamesArgs = fileNamesArgs;
    	this.notFoundFiles = new ArrayList<>();
    }
    
    public void typeFileNamesForConversion() {
    	 Scanner readFileNames = new Scanner(System.in);
    	 System.out.print("Type the name(s) of the file(s): ");
    	 
         String fileNames = readFileNames.nextLine();
         
         // Split the input into individual file names
         String[] splitFileNames = fileNames.split(" ");
         
         for(String splitFileName : splitFileNames) {
	         fileNamesUser.add(splitFileName + ".txt");
         }
         readFileNames.close();
    }
    
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
    	if(!notFoundFiles.isEmpty()) {
    		Iterator<String> it = notFoundFiles.iterator();
            System.out.print("\nThe following files couldn't be found: ");
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
     * Uses objects of type AnalyzerReader and AnalyzerWriter and tries to read the .txt files in directory "text"
     * and generates a digraphs out of them in .csv form, saving them in directory "csv" for later processing using Gephi.
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
    
    public void processAllFiles() {
    	try {
    		AnalyzerReader reader = new AnalyzerReader(".\\files\\text\\");
            AnalyzerWriter writer = new AnalyzerWriter(".\\files\\csv\\");
            
            for(String fileName : fileNamesArgs) {
            	reader.readFile(fileName);
                writer.writeFile(fileName);
            }
    	} catch(Exception e) {
    		
    	}
    }
}
