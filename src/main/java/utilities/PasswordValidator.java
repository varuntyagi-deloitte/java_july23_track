package utilities;

public class PasswordValidator {
    public static boolean isPasswordValid(String password) {
        if (password == null) {
            return false;
        }

        // Checks minimum length
        if (!((password.length() >=5)&&(password.length()<=10))) {
            return false;
        }

        // Checks for at least one uppercase letter
        if (!password.matches(".*[A-Z].*")) {
            return false;
        }

        // Checks for at least one lowercase letter
        if (!password.matches(".*[a-z].*")) {
            return false;
        }

        // Checks for at least one digit
        if (!password.matches(".*\\d.*")) {
            return false;
        }

        // Checks for at least one special character
        if (!password.matches(".*[@#$%^&+=].*")) {
            return false;
        }

        return true;
    }
}
