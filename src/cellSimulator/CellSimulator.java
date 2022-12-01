package cellSimulator;

import java.util.Scanner;

import view.GUIController;

public class CellSimulator {
	
	public CellSimulator() {
//		runSimulators();
		
		// this is temporary and only for testing the program
		GUIController programGUIController = new GUIController();
		programGUIController.launchGUI();
	}
	
	private void runSimulators() {
		Scanner scanner = new Scanner(System.in);
		boolean isCellSimulatorExisted = false;
		while (!isCellSimulatorExisted) {
			System.out.println("Welcome! Which cell simulator would you like to try?\n"
					+ "(please type in the letter shown in parentheses associated with the particular simulator)\n");
			System.out.println("1.	  Water-World (W)");

			String gameAnswer = scanner.nextLine().toUpperCase();

			if(gameAnswer.contentEquals("W")) {
				GUIController programGUIController = new GUIController();
				programGUIController.launchGUI();
				isCellSimulatorExisted = true;
			} else {
				System.out.println("Sorry, we don't have this cell simulator. Try again as your wish!\n\n");
			}
		}
		
		scanner.close();
	}
	
}
