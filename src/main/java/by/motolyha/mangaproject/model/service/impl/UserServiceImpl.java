package by.motolyha.mangaproject.model.service.impl;

import by.motolyha.mangaproject.exception.DaoException;
import by.motolyha.mangaproject.exception.ServiceException;
import by.motolyha.mangaproject.model.dao.impl.DaoProvider;
import by.motolyha.mangaproject.model.dao.UserDao;
import by.motolyha.mangaproject.model.dto.SignInData;
import by.motolyha.mangaproject.model.dto.SignUpData;
import by.motolyha.mangaproject.model.entity.ResultSignUp;
import by.motolyha.mangaproject.model.entity.User;
import by.motolyha.mangaproject.model.service.UserService;
import by.motolyha.mangaproject.util.PasswordEncryptor;
import by.motolyha.mangaproject.validator.UserValidator;

import java.util.Optional;

public class UserServiceImpl implements UserService {
    private static final DaoProvider daoProvider = DaoProvider.getInstance();
    private static final UserDao userDao = daoProvider.getUserDao();
    private static final UserValidator validator = new UserValidator();

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
            String avatarSrc = "";
            userDao.create(
                    login,
                    hashPassword,
                    description,
                    email,
                    avatarSrc
            );
            return ResultSignUp.SUCCESS;
        } catch (DaoException exception) {
            throw new ServiceException(exception);
        }
    }

    @Override
    public boolean forgotPassword(String email) throws ServiceException {
        if (!validator.emailValidate(email)) {
            return false;
        }
        //todo сделать перессылку на почту токена с ссылкой на команду создания нового пароля (
        // ссылку создавать с токеном в котором старый пароль /** что бы второй раз она не сработала, тк будет уже новый сгенерированный)
        return false;
    }

    @Override
    public void updateUserData(Integer idUser, User newUserData) throws ServiceException {
        if (!validator.emailValidate(newUserData.getEmail())
                || !validator.loginValidate(newUserData.getLogin())) {
            throw new ServiceException("incorrect data");
        }
        try {
            userDao.update(
                    newUserData.getLogin(),
                    newUserData.getDescription(),
                    newUserData.getEmail(),
                    newUserData.getAvatarSrc(),
                    idUser);
        } catch (DaoException exception) {
            throw new ServiceException(exception);
        }
    }

    @Override
    public void updateUserPassword(Integer idUser, String password) throws ServiceException {
        if (!validator.passwordValidate(password)) {
            throw new ServiceException("incorrect password");
        }
        String hash = PasswordEncryptor.getInstance().getHash(password);
        try {
            userDao.updatePassword(idUser, hash);
        } catch (DaoException exception) {
            throw new ServiceException(exception);
        }
    }

}
