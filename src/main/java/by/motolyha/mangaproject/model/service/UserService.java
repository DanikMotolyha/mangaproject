package by.motolyha.mangaproject.model.service;

import by.motolyha.mangaproject.exception.ServiceException;
import by.motolyha.mangaproject.model.entity.ResultSignUp;
import by.motolyha.mangaproject.model.entity.User;

import java.util.Optional;

public interface UserService {
    Optional<User> signIn(String login, String password) throws ServiceException;
    ResultSignUp signUp(String login, String email, String password) throws ServiceException;
    boolean forgotPassword(String email) throws ServiceException;
    void updateUserData(Integer idUser, User newUserData) throws ServiceException;
    void updateUserPassword(Integer idUser, String Password) throws ServiceException;
}
