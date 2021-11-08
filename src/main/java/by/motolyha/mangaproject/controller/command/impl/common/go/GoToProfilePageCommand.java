package by.motolyha.mangaproject.controller.command.impl.common.go;

import by.motolyha.mangaproject.controller.command.Command;
import by.motolyha.mangaproject.controller.command.Router;

import javax.servlet.http.HttpServletRequest;

import static by.motolyha.mangaproject.controller.command.Router.RouterType.*;
import static by.motolyha.mangaproject.controller.command.PagePath.*;
public class GoToProfilePageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        return new Router(PROFILE_PAGE,REDIRECT);
    }
}
