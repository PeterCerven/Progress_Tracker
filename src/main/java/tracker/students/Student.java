package tracker.students;

import tracker.courses.Courses;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Student {
    private String firstName;
    private String lastName;
    private final String email;
    private int id;
    private int javaPoints;
    private int DSAPoints;
    private int databasesPoints;
    private int springPoints;
    private boolean javaNotified;
    private boolean DSANotified;
    private boolean databasesNotified;
    private boolean springNotified;


    public Student(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }



    public void setCertified(Courses course) {
        switch (course) {
            case JAVA -> javaNotified = true;
            case DSA -> DSANotified = true;
            case DATABASES -> databasesNotified = true;
            case SPRING -> springNotified = true;
        }
    }

    public boolean getCertified(Courses course) {
        return switch (course) {
            case JAVA -> javaNotified;
            case DSA -> DSANotified;
            case DATABASES -> databasesNotified;
            case SPRING -> springNotified;
        };
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getPoints(Courses course) {
        return switch (course) {
            case JAVA -> getJavaPoints();
            case DSA -> getDSAPoints();
            case DATABASES -> getDatabasesPoints();
            case SPRING -> getSpringPoints();
            default -> -1;
        };
    }

    public double getCompletion(Courses course) {
        double number = 0;
        switch (course) {
            case JAVA:
                number = 100.0 * getJavaPoints() / Courses.JAVA.getCompletion();
                break;
            case DSA:
                number = 100.0 * getDSAPoints() / Courses.DSA.getCompletion();
                break;
            case DATABASES:
                number = 100.0 * getDatabasesPoints() / Courses.DATABASES.getCompletion();
                break;
            case SPRING:
                number = 100.0 * getSpringPoints() / Courses.SPRING.getCompletion();
                break;
            default:
                return -1;
        }
        return new BigDecimal(number).setScale(1, RoundingMode.HALF_UP).doubleValue();
    }

    public int getJavaPoints() {
        return javaPoints;
    }

    public void setJavaPoints(int javaPoints) {
        this.javaPoints = javaPoints;
    }

    public int getDSAPoints() {
        return DSAPoints;
    }

    public void setDSAPoints(int DSAPoints) {
        this.DSAPoints = DSAPoints;
    }

    public int getDatabasesPoints() {
        return databasesPoints;
    }

    public void setDatabasesPoints(int databasesPoints) {
        this.databasesPoints = databasesPoints;
    }

    public int getSpringPoints() {
        return springPoints;
    }

    public void setSpringPoints(int springPoints) {
        this.springPoints = springPoints;
    }

    public String getEmail() {
        return email;
    }

    public void updateStudent(int javaPoints, int DSAPoints, int databasesPoints, int springPoints) {
        setJavaPoints(getJavaPoints() + javaPoints);
        setDSAPoints(getDSAPoints() + DSAPoints);
        setDatabasesPoints(getDatabasesPoints() + databasesPoints);
        setSpringPoints(getSpringPoints() + springPoints);
    }


    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    @Override
    public String toString() {
        return " points: Java=" + javaPoints + "; DSA=" + DSAPoints + "; Databases=" + databasesPoints
                + "; Spring=" + springPoints;
    }

}
