package by.motolyha.mangaproject.controller.command.impl;

import by.motolyha.mangaproject.controller.command.*;
import by.motolyha.mangaproject.exception.DaoException;
import by.motolyha.mangaproject.exception.ServiceException;
import by.motolyha.mangaproject.model.dao.UserDao;
import by.motolyha.mangaproject.model.dao.impl.DaoProvider;
import by.motolyha.mangaproject.model.dto.SignUpData;
import by.motolyha.mangaproject.model.entity.ResultSignUp;
import by.motolyha.mangaproject.model.service.UserService;
import by.motolyha.mangaproject.model.service.impl.ServiceProvider;
import by.motolyha.mangaproject.util.PasswordEncryptor;
import by.motolyha.mangaproject.validator.UserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static by.motolyha.mangaproject.controller.command.Router.RouterType.*;
import static by.motolyha.mangaproject.controller.command.PagePath.*;

public class SignUpCommand implements Command {
    private static final Logger logger = LogManager.getRootLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        UserValidator validator = new UserValidator();
        String login = request.getParameter(RequestParameter.LOGIN);
        String email = request.getParameter(RequestParameter.EMAIL);
        if (!validator.emailValidate(email)
                || !validator.loginValidate(login)) {
            //todo setAttribute(invalidData)
            // !!! можно и не делать тк на фронте есть сообщ валидации (не пропустит)
            return new Router(SIGN_UP_PAGE, FORWARD);
        }

        final ServiceProvider provider = ServiceProvider.getInstance();
        final UserService userService = provider.getUserService();
        String password = PasswordEncryptor.generateRandomPassword();
        System.out.println("password :" + password);
        ResultSignUp signUp;


        try {
            signUp = userService.signUp(login, email, password);
        } catch (ServiceException e) {
            logger.error(e);
            request.getSession().setAttribute(RequestAttribute.EXCEPTION, e);
            return new Router(ERROR_PAGE, REDIRECT);
        }
        switch (signUp) {
            case DUPLICATE_EMAIL: {
                request.setAttribute(RequestAttribute.DUPLICATE_EMAIL, true);
                return new Router(SIGN_UP_PAGE, FORWARD);
            }
            case DUPLICATE_LOGIN: {
                request.setAttribute(RequestAttribute.DUPLICATE_LOGIN, true);
                return new Router(SIGN_UP_PAGE, FORWARD);
            }
        }
        return new Router(SIGN_UP_SUCCESS_PAGE, FORWARD);
    }
}
