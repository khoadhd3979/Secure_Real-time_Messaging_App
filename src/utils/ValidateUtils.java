package utils;

import java.util.regex.Pattern;

public class ValidateUtils {
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";

    public static boolean isValidEmail(String email) {
        if (email == null || email.isEmpty()) return false;
        return Pattern.matches(EMAIL_REGEX, email);
    }

    public static boolean isValidPassword(String password) {

        return password != null && password.length() >= 6;
    }
}