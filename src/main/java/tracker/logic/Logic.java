package tracker.logic;

import tracker.courses.Courses;
import tracker.students.Student;
import tracker.students.Students;

import java.util.Arrays;
import java.util.Scanner;

public class Logic {

    private final Scanner scanner;
    private final Students students;
    private final Validation validation;

    public Logic() {
        scanner = new Scanner(System.in);
        students = new Students();
        validation = new Validation();
    }

    public void addStudents() {
        System.out.println("Enter student credentials or 'back' to return:");
        int studentsAddedCount = 0;
        String[] credentials;

        while (true) {
            String words = scanner.nextLine().toUpperCase();
            if ("BACK".equals(words)) {
                break;
            }
            credentials = words.split("\\s+");
            if (credentials.length < 3) {
                System.out.println("Incorrect credentials.");
                continue;
            }
            if (add(credentials)) {
                studentsAddedCount++;
            }
        }

        System.out.printf("Total %d students have been added.\n", studentsAddedCount);
    }

    public boolean add(String[] credentials) {
        String firstName = credentials[0];
        String lastName = String.join(" ", Arrays.copyOfRange(credentials, 1, credentials.length - 1));
        String email = credentials[credentials.length - 1];
        if (validation.validatePerson(firstName, lastName, email)) {
            if (students.add(new Student(firstName, lastName, email)) == -1) {
                System.out.println("This email is already taken.");
                return false;
            }
            System.out.println("The student has been added.");
            return true;

        }
        return false;
    }

    public void listStudents() {
        if (students.getStudents().isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        System.out.println("Students:");
        for (Integer index : students.getStudents().keySet()) {
            System.out.println(index);
        }
    }

    public void addPoints() {
        System.out.println("Enter an id and points or 'back' to return:");
        String[] points;
        while (true) {
            points = scanner.nextLine().split("\\s+");
            if (points[0].equalsIgnoreCase("BACK")) {
                break;
            }
            try {
                int id = Integer.parseInt(points[0]);
                if (!students.getStudents().containsKey(id)) {
                    System.out.printf("No student is found for id=%d.\n", id);
                    continue;
                }
            } catch (NumberFormatException e) {
                System.out.printf("No student is found for id=%s.\n", points[0]);
                continue;
            }
            int id = Integer.parseInt(points[0]);
            if (!validation.validatePointsFormat(points)) {
                continue;
            }
            int javaPoints = Integer.parseInt(points[1]);
            int DSAPoints = Integer.parseInt(points[2]);
            int databasesPoints = Integer.parseInt(points[3]);
            int springPoints = Integer.parseInt(points[4]);

            students.updatePoints(id, javaPoints, DSAPoints, databasesPoints, springPoints);
            System.out.println("Points updated.");

        }
    }

    public void findStudent() {
        System.out.println("Enter an id or 'back' to return:");
        String input = scanner.next();
        while (!input.equalsIgnoreCase("BACK")) {
            int id = Integer.parseInt(input);
            if (!students.getStudents().containsKey(id)) {
                System.out.printf("No student is found for id=%d.\n", id);
                input = scanner.next().toUpperCase();
                continue;
            }
            System.out.println(id + "" + students.getStudents().get(id));
            input = scanner.next();
        }
    }

    /**
     * Give certification to Students who completed course
     */
    public void notifyStudent() {
        int count = 0;
        boolean notified;
        for (Student student : students.getStudents().values()) {
            notified = false;
            for (Courses course : Courses.values()) {
                if (student.getPoints(course) >= course.getCompletion() && !student.getCertified(course)) {
                    certificate(student, course);
                    student.setCertified(course);
                    notified = true;
                }
            }
            if (notified) {
                count++;
            }
        }
        System.out.printf("Total %d students have been notified.\n", count);
    }

    public void certificate(Student student, Courses course) {
        System.out.println("To: " + student.getEmail());
        System.out.println("Re: Your Learning Progress");
        System.out.printf("Hello, %s! You have accomplished our %s course!\n", student.getFullName(), course.getName());
    }

    public void statistics() {
        new Statistics().statistics(this);
    }

    public Students getStudents() {
        return students;
    }


}
