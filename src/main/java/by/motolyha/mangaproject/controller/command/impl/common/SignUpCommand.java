package by.motolyha.mangaproject.controller.command.impl.common;

import by.motolyha.mangaproject.controller.command.*;
import by.motolyha.mangaproject.exception.ServiceException;
import by.motolyha.mangaproject.model.service.resultcode.ResultSignUp;
import by.motolyha.mangaproject.model.service.UserService;
import by.motolyha.mangaproject.model.service.impl.ServiceProvider;
import by.motolyha.mangaproject.util.PasswordEncryptor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static by.motolyha.mangaproject.controller.command.Router.RouterType.*;
import static by.motolyha.mangaproject.controller.command.PagePath.*;

public class SignUpCommand implements Command {
    private static final Logger logger = LogManager.getLogger(SignUpCommand.class);

    @Override
    public Router execute(HttpServletRequest request) {
        String login = request.getParameter(RequestParameter.LOGIN);
        String email = request.getParameter(RequestParameter.EMAIL);

        ServiceProvider provider = ServiceProvider.getInstance();
        UserService userService = provider.getUserService();
        String password = PasswordEncryptor.generateRandomPassword();
        ResultSignUp signUp;

        request.getSession().removeAttribute(RequestAttribute.DUPLICATE_EMAIL);
        request.getSession().removeAttribute(RequestAttribute.DUPLICATE_LOGIN);

        try {
            signUp = userService.signUp(login, email, password);
        } catch (ServiceException e) {
            logger.error(e);
            request.getSession().setAttribute(RequestAttribute.EXCEPTION, e);
            return new Router(ERROR_PAGE, REDIRECT);
        }
        switch (signUp) {
            case DUPLICATE_EMAIL: {
                request.getSession().setAttribute(RequestAttribute.DUPLICATE_EMAIL, true);
                return new Router(SIGN_UP_PAGE, REDIRECT);
            }
            case DUPLICATE_LOGIN: {
                request.getSession().setAttribute(RequestAttribute.DUPLICATE_LOGIN, true);
                return new Router(SIGN_UP_PAGE, REDIRECT);
            }
        }
        return new Router(SIGN_UP_SUCCESS_PAGE, REDIRECT);
    }
}
