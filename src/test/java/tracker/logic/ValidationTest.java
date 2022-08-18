package tracker.logic;



import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import tracker.logic.Validation;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class ValidationTest {
    private static Validation validation;

    @BeforeAll
    public static void setUp(){
        validation = new Validation();
    }

    @ParameterizedTest
    @ValueSource(strings = { "Jim", "Joh's", "bad", "gad", "Al", "alloha", "a-Kp", "a-f'b"})
    public void validFirstNameTest(String input) {
        assertTrue(validation.firstNameValidation(input));
    }

    @ParameterizedTest
    @ValueSource(strings = { "Jim.", "табу", "-bad", "'gad", "a", "alloha-", "a-'", "a-'b"})
    public void invalidFirstNameTest(String input) {
        assertFalse(validation.firstNameValidation(input));
    }

    @ParameterizedTest
    @ValueSource(strings = { "Jim'O", "mama", "ba-d", "g'ad", "aa", "all oha", "a-b", "K'fdd L-K-L"})
    public void validLastNameTest(String input) {
        assertTrue(validation.lastNameValidation(input));
    }

    @ParameterizedTest
    @ValueSource(strings = { "Jim' O", "mama'", "'ba-d", "g''ad", "a--a", "all 'oha", "a'-b"})
    public void invalidLastNameTest(String input) {
        assertFalse(validation.lastNameValidation(input));
    }

    @ParameterizedTest
    @ValueSource(strings = { "maryj@google.com", "jc@google.it", "name@domain.com", "jane.doe@yahoo.com", "1@1.1"})
    public void validEmailTest(String input) {
        assertTrue(validation.emailValidation(input));
    }

    @ParameterizedTest
    @ValueSource(strings = { "anny.md@mail", "ma@ma1@2.3", "@df.new", "dsf.fddfdf.fd"})
    public void invalidEmailTest(String input) {
        assertFalse(validation.emailValidation(input));
    }

    @ParameterizedTest
    @MethodSource("provideValidStringArray")
    public void validFormat(String[] format) {
        assertTrue(validation.validatePointsFormat(format));
    }

    private static Stream<Arguments> provideValidStringArray() {
        return Stream.of(
                Arguments.of((Object) new String[]{"1000", "5", "4", "4", "4"}),
                Arguments.of((Object) new String[]{"1000", "5", "4", "4", "4"}),
                Arguments.of((Object) new String[]{"1000", "5", "4", "4", "4"}),
                Arguments.of((Object) new String[]{"1000", "5", "4", "4", "4"})

        );
    }

    @ParameterizedTest
    @MethodSource("provideInvalidStringArray")
    public void invalidFormat(String[] format) {
        assertFalse(validation.validatePointsFormat(format));
    }

    private static Stream<Arguments> provideInvalidStringArray() {
        return Stream.of(
                Arguments.of((Object) new String[]{"-1", "0", "d", "fd", "8"}),
                Arguments.of((Object) new String[]{"1000", "10", "10", "df", "4"}),
                Arguments.of((Object) new String[]{"1000", "-1", "4", "4", "4"}),
                Arguments.of((Object) new String[]{"blm", "5", "0", "56", "4"})

        );
    }

}