package by.motolyha.mangaproject.model.service;

import by.motolyha.mangaproject.exception.ServiceException;
import by.motolyha.mangaproject.model.service.resultcode.ResultForgotPassword;
import by.motolyha.mangaproject.model.service.resultcode.ResultSignUp;
import by.motolyha.mangaproject.model.entity.User;

import java.util.Optional;

public interface UserService {
    Optional<User> signIn(String login, String password) throws ServiceException;
    ResultSignUp signUp(String login, String email, String password) throws ServiceException;
    ResultForgotPassword forgotPassword(String email) throws ServiceException;
    void updateUserData(User newUserData) throws ServiceException;
}
