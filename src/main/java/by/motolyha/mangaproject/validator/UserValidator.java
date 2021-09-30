package by.motolyha.mangaproject.validator;

public class UserValidator {
    private static final String EMAIL_PATTERN =
            "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";

    private static final String LOGIN_AND_PASSWORD_PATTERN =
            "^[a-zA-Z0-9_]{5,16}$";


    public boolean passwordValidate(String password) {
        return password.matches(LOGIN_AND_PASSWORD_PATTERN);
    }

    public boolean loginValidate(String login) {
        return login.matches(LOGIN_AND_PASSWORD_PATTERN);
    }

    public boolean emailValidate(String email) {
        return email.matches(EMAIL_PATTERN);
    }
}
