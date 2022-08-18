package tracker.userinterface;


import tracker.logic.Logic;

import java.util.Scanner;

public class UserInterface {
    private final Logic logic;
    private final Scanner scanner;

    public UserInterface() {
        logic = new Logic();
        scanner = new Scanner(System.in);
        startProgram();
    }

    private void startProgram() {
        System.out.println("Learning Progress Tracker");
        while (true) {
            String input = scanner.nextLine().toUpperCase();

            if (input.equals("EXIT")) {
                System.out.println("Bye!");
                break;
            }
            if (input.isBlank()) {
                System.out.println("No input.");
                continue;
            }
            processInput(input);
        }
    }

    private void processInput(String input) {
        switch (input) {
            case "ADD STUDENTS" -> logic.addStudents();
            case "BACK" -> System.out.println("Enter 'exit' to exit the program.");
            case "LIST" -> logic.listStudents();
            case "ADD POINTS" -> logic.addPoints();
            case "FIND" -> logic.findStudent();
            case "STATISTICS" -> logic.statistics();
            case "NOTIFY" -> logic.notifyStudent();
            default -> System.out.println("Error: unknown command!");
        }
    }

}
