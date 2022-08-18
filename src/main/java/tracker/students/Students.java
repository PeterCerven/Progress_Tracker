package tracker.students;

import tracker.courses.Courses;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;


public class Students {
    private final Map<Integer, Student> students;
    private int index;
    private final ArrayList<String> emails;

    public Students() {
        this.students = new LinkedHashMap<>();
        this.emails = new ArrayList<>();
    }

    public int add(Student student) {
        if (emails.contains(student.getEmail())) {
            return -1;
        }
        student.setId(index);
        emails.add(student.getEmail());
        students.put(index, student);
        return index++;
    }

    public Map<Integer, Student> getStudents() {
        return students;
    }

    public void updatePoints(int id, int JavaPoints, int DSAPoints, int DatabasesPoints, int SpringPoints) {
        Student student = students.get(id);
        student.updateStudent(JavaPoints, DSAPoints, DatabasesPoints, SpringPoints);
        Courses.JAVA.addStudent(JavaPoints, student);
        Courses.DSA.addStudent(DSAPoints, student);
        Courses.DATABASES.addStudent(DatabasesPoints, student);
        Courses.SPRING.addStudent(SpringPoints, student);
    }
}
