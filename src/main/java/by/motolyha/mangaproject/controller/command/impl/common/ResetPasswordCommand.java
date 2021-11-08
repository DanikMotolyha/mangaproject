package by.motolyha.mangaproject.controller.command.impl.common;

import by.motolyha.mangaproject.controller.command.Command;
import by.motolyha.mangaproject.controller.command.RequestAttribute;
import by.motolyha.mangaproject.controller.command.RequestParameter;
import by.motolyha.mangaproject.controller.command.Router;
import by.motolyha.mangaproject.exception.ServiceException;
import by.motolyha.mangaproject.model.service.resultcode.ResultForgotPassword;
import by.motolyha.mangaproject.model.service.UserService;
import by.motolyha.mangaproject.model.service.impl.ServiceProvider;

import javax.servlet.http.HttpServletRequest;

import static by.motolyha.mangaproject.controller.command.RequestAttribute.DENIED_RESET_PASSWORD;
import static by.motolyha.mangaproject.controller.command.RequestAttribute.USER_DOES_NOT_EXIST;
import static by.motolyha.mangaproject.controller.command.Router.RouterType.*;
import static by.motolyha.mangaproject.controller.command.PagePath.*;

public class ResetPasswordCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        String email = request.getParameter(RequestParameter.EMAIL);

        UserService userService = ServiceProvider.getInstance().getUserService();
        ResultForgotPassword resultForgotPassword;
        try {
            resultForgotPassword = userService.forgotPassword(email);
        } catch (ServiceException exception) {
            request.getSession().setAttribute(RequestAttribute.EXCEPTION, exception);
            return new Router(ERROR_PAGE, REDIRECT);
        }
        Router router;
        switch (resultForgotPassword) {
            case DENIED: {
                request.getSession().setAttribute(DENIED_RESET_PASSWORD, true);
                router = new Router(RESET_PASSWORD_PAGE, REDIRECT);
                break;
            }
            case USER_DOES_NOT_EXIST: {
                request.getSession().setAttribute(USER_DOES_NOT_EXIST, true);
                router = new Router(RESET_PASSWORD_PAGE, REDIRECT);
                break;
            }
            case SUCCESS: {
                router = new Router(RESET_PASSWORD_SUCCESS_PAGE, REDIRECT);
                break;
            }
            default: {
                request.getSession().setAttribute(RequestAttribute.EXCEPTION, new NullPointerException());
                return new Router(ERROR_PAGE, REDIRECT);
            }
        }
        return router;
    }
}
