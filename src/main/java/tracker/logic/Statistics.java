package tracker.logic;

import tracker.courses.Courses;
import tracker.students.Student;

import java.util.*;

/**
 * Prints statistics about courses.
 */
public class Statistics {
    private Map<Integer, Student> students;

    /**
     * Prints statistics about courses.
     */
    public void statistics(Logic logic) {
        students = logic.getStudents().getStudents();
        System.out.println("Type the name of a course to see details or 'back' to quit:");
        printPopularity();
        printActivity();
        printDifficulty();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            boolean exist = false;
            String input = scanner.nextLine().toUpperCase();

            if (input.equals("BACK")) {
                return;
            }
            List<Student> sortedStudents = new ArrayList<>();
            for (Courses course : Courses.values()) {
                if (course.name().equals(input)) {
                    listCourse(course, sortedStudents);
                    exist = true;
                    break;
                }
            }
            if (!exist) {
                System.out.println("Unknown course.");
            }


        }
    }

    /**
     * List id points and completion of selected course.
     * @param course course
     * @param sortedStudents list of to be sorted students
     */
    private void listCourse(Courses course, List<Student> sortedStudents) {
        System.out.println(course.getName());
        System.out.println("id points completed");
        for (var student : students.entrySet()) {
            if (student.getValue().getPoints(course) > 0) {
                sortedStudents.add(student.getValue());
            }
        }
        sortedStudents.stream()
                .sorted(Comparator.comparing((Student a) -> a.getPoints(course))
                        .reversed()
                        .thenComparing(Student::getId))
                .forEach((Student a) -> System.out.printf(Locale.US,
                        "%-2d %-6d %.1f%s\n",a.getId(), a.getPoints(course)
                        , a.getCompletion(course), "%"));
    }

    /**
     * Prints most popular courses and least popular ones based on students enrolled.
     */
    private void printPopularity() {
        int enrolled = Integer.MIN_VALUE;
        StringBuilder str = new StringBuilder();
        for (Courses course : Courses.values()) {
            if (course.isStarted()) {
                continue;
            }
            if (course.getEnrolledStudents() > enrolled) {
                str = new StringBuilder(course.getName() + " ");
                enrolled = course.getEnrolledStudents();
            } else if (course.getEnrolledStudents() == enrolled) {
                str.append(course.getName()).append(" ");
            }
        }
        String mostPopular = str.toString().isEmpty() ? "n/a" : str.toString().trim();
        System.out.printf("Most popular: %s\n", mostPopular);

        str = new StringBuilder();
        boolean smaller = false;
        for (Courses course : Courses.values()) {
            if (course.isStarted()) {
                continue;
            }
            if (course.getEnrolledStudents() < enrolled) {
                str = new StringBuilder(course.getName() + " ");
                enrolled = course.getEnrolledStudents();
                smaller = true;
            } else if (course.getEnrolledStudents() == enrolled && smaller) {
                str.append(course.getName()).append(" ");
            }

        }
        String leastPopular = str.toString().isEmpty() ? "n/a" : str.toString().trim();
        System.out.printf("Least popular: %s\n", leastPopular);
    }

    /**
     * Prints activity based on submitted tasks.
     */
    private void printActivity() {
        int submissions = -1;
        StringBuilder str = new StringBuilder();
        for (Courses course : Courses.values()) {
            if (course.isStarted()) {
                continue;
            }
            if (course.getTasksDone() > submissions) {
                str = new StringBuilder(course.getName() + " ");
                submissions = course.getTasksDone();
            } else if (course.getTasksDone() == submissions) {
                str.append(course.getName()).append(" ");
            }
        }
        String highestActivity = str.toString().isEmpty() ? "n/a" : str.toString().trim();
        System.out.printf("Highest activity: %s\n", highestActivity);

        str = new StringBuilder();
        boolean smaller = false;
        for (Courses course : Courses.values()) {
            if (course.isStarted()) {
                continue;
            }
            if (course.getTasksDone() < submissions) {
                str = new StringBuilder(course.getName() + " ");
                submissions = course.getTasksDone();
                smaller = true;
            } else if (course.getTasksDone() == submissions && smaller) {
                str.append(course.getName()).append(" ");
            }

        }
        String lowestActivity = str.toString().isEmpty() ? "n/a" : str.toString().trim();
        System.out.printf("Lowest activity: %s\n", lowestActivity);
    }

    /**
     * Print courses based on their difficulty based on average grade from submission.
     */
    private void printDifficulty() {
        double difficulty = -1;
        StringBuilder str = new StringBuilder();
        for (Courses course : Courses.values()) {
            if (course.isStarted()) {
                continue;
            }
            if (course.getAverageGrade() > difficulty) {
                str = new StringBuilder(course.getName() + " ");
                difficulty = course.getAverageGrade();
            } else if (course.getAverageGrade() == difficulty) {
                str.append(course.getName()).append(" ");
            }
        }
        String easiestCourse = str.toString().isEmpty() ? "n/a" : str.toString().trim();
        System.out.printf("Easiest course: %s\n", easiestCourse);

        str = new StringBuilder();
        boolean smaller = false;
        for (Courses course : Courses.values()) {
            if (course.isStarted()) {
                continue;
            }
            if (course.getAverageGrade() < difficulty) {
                str = new StringBuilder(course.getName() + " ");
                difficulty = course.getAverageGrade();
                smaller = true;
            } else if (course.getAverageGrade() == difficulty && smaller) {
                str.append(course.getName()).append(" ");
            }

        }
        String hardestCourse = str.toString().isEmpty() ? "n/a" : str.toString().trim();
        System.out.printf("Hardest course: %s\n", hardestCourse);
    }

}
