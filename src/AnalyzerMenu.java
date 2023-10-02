import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Class: AnalyzerStart
 * @author Miguel Donanzam - m260851@dac.unicamp.br
 * @author Julio Morino - j173434@dac.unicamp.br
 * @author Felipe Akira - f172885@dac.unicamp.br
 */

public class AnalyzerMenu {
	private String[] fileNamesArgs; // Array de nome de arquivos dos argumentos
	
	public AnalyzerMenu(String[] fileNamesArgs) {
		this.fileNamesArgs = fileNamesArgs;
	}
	
	public void displayMenu() {
		Scanner optionReader = new Scanner(System.in);
		int choice;
        boolean finishProgram = false;
        
		while(!finishProgram) {
			try {
        		System.out.println("======================MENU======================");
                System.out.println("1. Type in file names for .csv convertion");
                System.out.println("2. Convert all files to .csv form");
                System.out.println("3. Exit");
                System.out.println("================================================");
                System.out.print("Option: ");
                choice = optionReader.nextInt();       
                switch (choice) {
                    case 1:
                    	AnalyzerController controller = new AnalyzerController(fileNamesArgs);
                    	controller.typeFileNamesForConversion();
                    	controller.processFileNames();
                    	break;
                    	
                    case 2:
                    	AnalyzerController controller2 = new AnalyzerController(fileNamesArgs);
                        controller2.processAllFiles();
                        finishProgram = true;
                        break;
                        
                    case 3:
                        finishProgram = true;
                        break;
                        
                    default:
                        System.err.println("Invalid option!");
                }
        	} catch(InputMismatchException e) {
        		System.err.println("Invalid input. Please enter a valid number.");
            	e.printStackTrace();
        		continue;
        	} catch(Exception e) {
        		System.err.println("An unexpected error ocurred");
            	System.err.println("Error details: " + e.getMessage());
            	e.printStackTrace();
        		continue;
            	
        	}
		}
        optionReader.close();
	}
	
	
}
