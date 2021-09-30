package by.motolyha.mangaproject.controller.command.impl;

import by.motolyha.mangaproject.controller.command.*;
import by.motolyha.mangaproject.exception.ServiceException;
import by.motolyha.mangaproject.model.dto.SignInData;
import by.motolyha.mangaproject.model.entity.User;
import by.motolyha.mangaproject.model.service.UserService;
import by.motolyha.mangaproject.model.service.impl.ServiceProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

import static by.motolyha.mangaproject.controller.command.Router.RouterType.*;
import static by.motolyha.mangaproject.controller.command.PagePath.*;

public class SingInCommand implements Command {
    private static final Logger logger = LogManager.getRootLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        String login = request.getParameter(RequestParameter.LOGIN);
        String password = request.getParameter(RequestParameter.PASSWORD);

        final ServiceProvider serviceProvider = ServiceProvider.getInstance();
        final UserService service = serviceProvider.getUserService();
        HttpSession session = request.getSession();
        try {
            Optional<User> user = service.signIn(login, password);
            if (user.isPresent()) {
                User currentUser = user.get();
                session.setAttribute(RequestAttribute.USER, currentUser);
                router = new Router(MAIN_PAGE, REDIRECT);
            } else {
                request.setAttribute(RequestAttribute.IS_SIGN_IN_DATA_INVALID, true);
                router = new Router(SIGN_IN_PAGE, FORWARD);
            }
        } catch (ServiceException e) {
            logger.error(e);
            session.setAttribute(RequestAttribute.EXCEPTION, e);
            router = new Router(ERROR_PAGE, REDIRECT);
        }
        return router;
    }
}
