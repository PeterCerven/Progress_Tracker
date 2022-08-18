package tracker.logic;

import java.util.Arrays;

public class Validation {

    public boolean validatePerson(String firstName, String lastName, String email) {
        return emailValidation(email) && firstNameValidation(firstName) && lastNameValidation(lastName);
    }

    public boolean firstNameValidation(String firstName) {
        String regex = "^[a-zA-Z]([-']?[a-zA-Z])+$";
        if (firstName.matches(regex)) {
            return true;
        }
        System.out.println("Incorrect first name.");
        return false;
    }

    public boolean lastNameValidation(String lastName) {
        String regex = "^[a-zA-Z]([-']?[a-zA-Z](\\s[a-zA-Z])?)+$";
        if (lastName.matches(regex)) {
            return true;
        }
        System.out.println("Incorrect last name.");
        return false;
    }

    public boolean emailValidation(String email) {
        String regex = "^\\w+(\\.\\w+)?@\\w+\\.\\w+$";
        if (email.matches(regex)) {
            return true;
        }
        System.out.println("Incorrect email.");
        return false;
    }

    public boolean validatePointsFormat(String[] format) {
        if (format.length != 5) {
            System.out.println("Incorrect points format.");
            return false;
        }
        try {
            if (Arrays.stream(format).mapToInt(Integer::parseInt).anyMatch(i -> i < 0)) {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException nfe) {
            System.out.println("Incorrect points format.");
            return false;
        }
        return true;
    }
}
