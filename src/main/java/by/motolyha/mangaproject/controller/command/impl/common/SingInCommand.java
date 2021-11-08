package by.motolyha.mangaproject.controller.command.impl.common;

import by.motolyha.mangaproject.controller.command.*;
import by.motolyha.mangaproject.exception.ServiceException;
import by.motolyha.mangaproject.model.dto.SessionUser;
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


        request.getSession().removeAttribute(RequestAttribute.IS_SIGN_IN_DATA_INVALID);

        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        UserService service = serviceProvider.getUserService();
        HttpSession session = request.getSession();
        try {
            Optional<User> user = service.signIn(login, password);
            if (user.isPresent()) {
                User dataUser = user.get();
                SessionUser currentUser = new SessionUser();

                currentUser.setId(dataUser.getId());
                currentUser.setLogin(dataUser.getLogin());
                currentUser.setDescription(dataUser.getDescription());
                currentUser.setEmail(dataUser.getEmail());
                currentUser.setIdAvatar(dataUser.getIdAvatar());
                currentUser.setRole(dataUser.getRole());
                currentUser.setResendPasswordDate(dataUser.getResendPasswordDate());

                session.setAttribute(RequestAttribute.USER, currentUser);
                router = new Router(HOME_PAGE, REDIRECT);
            } else {
                request.getSession().setAttribute(RequestAttribute.IS_SIGN_IN_DATA_INVALID, true);
                router = new Router(SIGN_IN_PAGE, REDIRECT);
            }
        } catch (ServiceException e) {
            logger.error(e);
            session.setAttribute(RequestAttribute.EXCEPTION, e);
            router = new Router(ERROR_PAGE, REDIRECT);
        }
        return router;
    }
}
