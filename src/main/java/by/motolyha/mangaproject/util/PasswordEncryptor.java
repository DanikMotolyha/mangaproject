package by.motolyha.mangaproject.util;

import org.apache.commons.lang3.RandomStringUtils;
import org.mindrot.jbcrypt.BCrypt;

public class PasswordEncryptor {
    private static final PasswordEncryptor instance = new PasswordEncryptor();

    private PasswordEncryptor() {
    }

    public static PasswordEncryptor getInstance() {
        return instance;
    }

    public boolean checkHash(String password, String hash) {
        return BCrypt.checkpw(password, hash);
    }

    public String getHash(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }
    //todo удалить эту надпись - чисто хэша 60 символов
    public static String generateRandomPassword() {
        return RandomStringUtils.randomAlphanumeric(12).toUpperCase();
    }
}
