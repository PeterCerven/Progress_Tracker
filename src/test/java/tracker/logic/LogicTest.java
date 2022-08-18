package tracker.logic;


import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import tracker.students.Student;
import tracker.students.Students;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class LogicTest {
    private Logic logic;
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();


    @BeforeEach
    public void systemSetOut() {
        logic = new Logic();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @ParameterizedTest
    @MethodSource("provideStringArrayArgs")
    void add(String[] input) {
        assertTrue(logic.add(input));
    }

    private static Stream<Arguments> provideStringArrayArgs() {
        return Stream.of(
                Arguments.of((Object) new String[]{"John", "De-an", "nasdf.fdf@gmail.com"}),
                Arguments.of((Object) new String[]{"John", "De'an", "nasf@gmail.com"}),
                Arguments.of((Object) new String[]{"Joh'n", "Dean", "naf@gm.com"}),
                Arguments.of((Object) new String[]{"John", "Dean", "dsf", "na1f@gmail.com"})
        );
    }

    @ParameterizedTest
    @MethodSource("provideStudentInfo")
    void listStudents(ArrayList<Student> array) {
        Students students = logic.getStudents();
        StringBuilder str = new StringBuilder("Students:\r\n");
        for (Student student : array) {
            int index = students.add(student);
            if (index != -1) {
                str.append(index).append("\r\n");
            }
        }
        logic.listStudents();

        assertEquals(str.toString().trim(), outputStreamCaptor.toString().trim());


    }

    private static Stream<Arguments> provideStudentInfo() {
        return Stream.of(
                Arguments.of(new ArrayList<Student>(List.of(new Student("John", "De'an", "nadff@gmail.com"),
                        new Student("John", "De'an", "nas33f@gmail.com"),
                        new Student("John", "De'an", "nas22f@gmail.com")))),
                Arguments.of(new ArrayList<Student>(List.of(new Student("John", "De'an", "nasf@gmail.com"),
                        new Student("John", "De'an", "nasf@gmail.com"),
                        new Student("John", "De'an", "nasf@gmail.com"))))

        );
    }

}