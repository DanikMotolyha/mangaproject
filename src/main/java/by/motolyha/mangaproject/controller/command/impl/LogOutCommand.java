package by.motolyha.mangaproject.controller.command.impl;

import by.motolyha.mangaproject.controller.command.Command;
import by.motolyha.mangaproject.controller.command.RequestAttribute;
import by.motolyha.mangaproject.controller.command.Router;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static by.motolyha.mangaproject.controller.command.Router.RouterType.*;
import static by.motolyha.mangaproject.controller.command.PagePath.*;

public class LogOutCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session != null) {
            session.setAttribute(RequestAttribute.USER, null);
        }
        return new Router(MAIN_PAGE, REDIRECT);
    }
}
