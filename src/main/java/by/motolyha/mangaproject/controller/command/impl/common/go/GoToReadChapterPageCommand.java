package by.motolyha.mangaproject.controller.command.impl.common.go;

import by.motolyha.mangaproject.controller.command.Command;
import by.motolyha.mangaproject.controller.command.RequestAttribute;
import by.motolyha.mangaproject.controller.command.RequestParameter;
import by.motolyha.mangaproject.controller.command.Router;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static by.motolyha.mangaproject.controller.command.PagePath.ERROR_404_PAGE;
import static by.motolyha.mangaproject.controller.command.Router.RouterType.REDIRECT;

public class GoToReadChapterPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger(GoToReadChapterPageCommand.class);

    @Override
    public Router execute(HttpServletRequest request) {
        String id = request.getParameter(RequestParameter.ID);
        String page = request.getParameter(RequestParameter.CHAPTER);
        String imagePage = request.getParameter(RequestParameter.IMAGE_PAGE);
        //
        List<Object> chapterPages = null;//..
        //request.getSession().setAttribute(RequestAttribute);
        return new Router(ERROR_404_PAGE, REDIRECT);
    }
}
