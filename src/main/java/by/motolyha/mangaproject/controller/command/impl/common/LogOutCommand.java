package by.motolyha.mangaproject.controller.command.impl.common;

import by.motolyha.mangaproject.controller.command.Command;
import by.motolyha.mangaproject.controller.command.Router;

import javax.servlet.http.HttpServletRequest;

import static by.motolyha.mangaproject.controller.command.Router.RouterType.*;
import static by.motolyha.mangaproject.controller.command.PagePath.*;

public class LogOutCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        request.getSession(false).invalidate();
        return new Router(INDEX_PAGE, REDIRECT);
    }
}
