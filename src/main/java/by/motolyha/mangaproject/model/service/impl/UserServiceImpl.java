package by.motolyha.mangaproject.model.service.impl;

import by.motolyha.mangaproject.exception.DaoException;
import by.motolyha.mangaproject.exception.ServiceException;
import by.motolyha.mangaproject.model.dao.AvatarDao;
import by.motolyha.mangaproject.model.dao.impl.AvatarDaoImpl;
import by.motolyha.mangaproject.model.dao.impl.DaoProvider;
import by.motolyha.mangaproject.model.dao.UserDao;
import by.motolyha.mangaproject.model.entity.Avatar;
import by.motolyha.mangaproject.model.service.resultcode.ResultForgotPassword;
import by.motolyha.mangaproject.model.service.resultcode.ResultSignUp;
import by.motolyha.mangaproject.model.entity.User;
import by.motolyha.mangaproject.model.service.UserService;
import by.motolyha.mangaproject.util.MailSender;
import by.motolyha.mangaproject.util.PasswordEncryptor;
import by.motolyha.mangaproject.validator.UserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private static final Logger logger = LogManager.getRootLogger();
    private static final DaoProvider daoProvider = DaoProvider.getInstance();
    private static final UserDao userDao = daoProvider.getUserDao();

    private static final UserValidator validator = new UserValidator();
    private static final MailSender sender = new MailSender();
    private static final int DEFAULT_USER_AVATAR_INDEX = 1;

    protected UserServiceImpl() {
    }

    /**
     * вход логин пароль ввдод - юзер выход
     */
    @Override
    public Optional<User> signIn(String login, String password) throws ServiceException {
        if (!validator.loginValidate(login)
                || !validator.passwordValidate(password)) {
            return Optional.empty();
        }
        Optional<User> user;
        try {
            user = userDao.findByLogin(login);
        } catch (DaoException exception) {
            logger.error(exception.getMessage());
            throw new ServiceException(exception);
        }
        if (user.isEmpty()) {
            return Optional.empty();
        }
        PasswordEncryptor encryptor = PasswordEncryptor.getInstance();
        return encryptor.checkHash(password, user.get().getPassword())
                ? user : Optional.empty();
    }

    @Override
    public ResultSignUp signUp(String login, String email, String password) throws ServiceException {
        if (!validator.emailValidate(email)
                && !validator.loginValidate(login)) {
            return ResultSignUp.INCORRECT_INPUT;
        }
        Optional<User> user;
        try {
            user = userDao.findByLogin(login);
            if (user.isPresent()) {
                return ResultSignUp.DUPLICATE_LOGIN;
            }
            user = userDao.findByEmail(email);
            if (user.isPresent()) {
                return ResultSignUp.DUPLICATE_EMAIL;
            }
            PasswordEncryptor encryptor = PasswordEncryptor.getInstance();
            String hashPassword = encryptor.getHash(password);
            String description = "";

            userDao.create(
                    login,
                    hashPassword,
                    description,
                    email,
                    DEFAULT_USER_AVATAR_INDEX
            );
            sender.sendNewPasswordToMail(login, password, email);
            return ResultSignUp.SUCCESS;
        } catch (DaoException exception) {
            logger.error(exception.getMessage());
            throw new ServiceException(exception);
        }
    }

    @Override
    public ResultForgotPassword forgotPassword(String email) throws ServiceException {
        if (!validator.emailValidate(email)) {
            throw new ServiceException("invalid email");
        }
        try {
            Optional<User> userOptional = userDao.findByEmail(email);
            if (userOptional.isEmpty()) {
                return ResultForgotPassword.USER_DOES_NOT_EXIST;
            }
            User user = userOptional.get();
            LocalDate now = LocalDate.now();
            LocalDate resendPasswordDate = user.getResendPasswordDate();
            long timeDifference = Duration.between(resendPasswordDate.atStartOfDay(), now.atStartOfDay()).toDays();
            if (timeDifference <= 3) {
                return ResultForgotPassword.DENIED;
            } else {
                String password = PasswordEncryptor.generateRandomPassword();
                PasswordEncryptor encryptor = PasswordEncryptor.getInstance();
                String passwordHash = encryptor.getHash(password);
                user.setPassword(passwordHash);
                user.setResendPasswordDate(LocalDate.now());
                userDao.update(user);
                sender.sendNewPasswordToMail(user.getLogin(), password, email);
            }
        } catch (DaoException exception) {
            logger.error(exception.getMessage());
            throw new ServiceException(exception);
        }
        return ResultForgotPassword.SUCCESS;
    }

    @Override
    public void updateUserData(User newUserData) throws ServiceException {
        if (!validator.emailValidate(newUserData.getEmail())
                || !validator.loginValidate(newUserData.getLogin())) {
            throw new ServiceException("incorrect data");
        }
        try {
            userDao.update(newUserData);
        } catch (DaoException exception) {
            logger.error(exception.getMessage());
            throw new ServiceException(exception);
        }
    }

}
