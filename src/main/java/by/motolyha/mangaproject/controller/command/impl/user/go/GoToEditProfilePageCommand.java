package by.motolyha.mangaproject.controller.command.impl.user.go;

import by.motolyha.mangaproject.controller.command.Command;
import by.motolyha.mangaproject.controller.command.Router;

import javax.servlet.http.HttpServletRequest;

import static by.motolyha.mangaproject.controller.command.PagePath.EDIT_PROFILE_PAGE;
import static by.motolyha.mangaproject.controller.command.Router.RouterType.REDIRECT;

public class GoToEditProfilePageCommand  implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        return new Router(EDIT_PROFILE_PAGE, REDIRECT);
    }
}
