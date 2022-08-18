package tracker.courses;

import tracker.students.Student;

/**
 * Information about all 4 Courses
 */
public enum Courses {
    JAVA("Java", 600, 0, 0, 0, 0, null, false),
    DSA("DSA", 400, 0, 0, 0, 0, null, false),
    DATABASES("Databases", 480, 0, 0, 0, 0, null, false),
    SPRING("Spring", 550, 0, 0, 0, 0, null, false);

    private final String name;
    private final int completion;
    private int enrolledStudents;
    private int tasksDone;
    private double averageGrade;
    private int totalGrades;
    private Student student;
    private boolean started;

    Courses(String name,int completion, int enrolledStudents, int tasksDone, double averageGrade,
            int totalGrades, Student student, boolean started) {
        this.name = name;
        this.completion = completion;
        this.enrolledStudents = enrolledStudents;
        this.averageGrade = averageGrade;
        this.tasksDone = tasksDone;
        this.totalGrades = totalGrades;
        this.student = student;
        this.started = started;
    }

    public String getName() {
        return name;
    }

    public void addStudent(double grade, Student student) {
        if (!this.started) {
            setStarted();
        }
        if (grade != 0) {
            enrolledStudents++;
            updateCourse(grade, student);
        }
    }

    public void setStarted() {
        this.started = true;
    }

    public void updateCourse(double grade ,Student student) {
        totalGrades += grade;
        tasksDone++;
        averageGrade = 1.0 * totalGrades / tasksDone;
        if (this.student == null || this.student.getPoints(this) < student.getPoints(this)) {
            this.student = student;
        }
    }

    public boolean isStarted() {
        return !started;
    }

    public int getEnrolledStudents() {
        return enrolledStudents;
    }

    public int getCompletion() {
        return completion;
    }

    public int getTasksDone() {
        return tasksDone;
    }

    public double getAverageGrade() {
        return averageGrade;
    }


}
