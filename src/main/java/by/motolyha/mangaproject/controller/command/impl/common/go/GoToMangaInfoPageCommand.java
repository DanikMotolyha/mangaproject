package by.motolyha.mangaproject.controller.command.impl.common.go;

import by.motolyha.mangaproject.controller.command.Command;
import by.motolyha.mangaproject.controller.command.RequestAttribute;
import by.motolyha.mangaproject.controller.command.RequestParameter;
import by.motolyha.mangaproject.controller.command.Router;
import by.motolyha.mangaproject.exception.ServiceException;
import by.motolyha.mangaproject.model.dto.MangaInfoDto;
import by.motolyha.mangaproject.model.entity.Manga;
import by.motolyha.mangaproject.model.service.MangaService;
import by.motolyha.mangaproject.model.service.impl.ServiceProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import java.util.Optional;

import static by.motolyha.mangaproject.controller.command.PagePath.*;
import static by.motolyha.mangaproject.controller.command.Router.RouterType.REDIRECT;

public class GoToMangaInfoPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger(GoToMangaInfoPageCommand.class);

    @Override
    public Router execute(HttpServletRequest request) {
        String id = request.getParameter(RequestParameter.ID);
        ServiceProvider provider = ServiceProvider.getInstance();
        MangaService mangaService = provider.getMangaService();
        Optional<Manga> manga;
        try {
            manga = mangaService.findById(id);
        } catch (ServiceException e) {
            logger.error(e);
            request.getSession().setAttribute(RequestAttribute.EXCEPTION, e);
            return new Router(ERROR_PAGE, REDIRECT);
        }
        if(manga.isEmpty()){
            request.getSession().setAttribute(RequestAttribute.EXCEPTION, "cannot find");
            return new Router(ERROR_404_PAGE, REDIRECT);
        }

        request.getSession().setAttribute(RequestAttribute.MANGA_DATA, manga.get());
        return new Router(MANGA_INFO_PAGE, REDIRECT);
    }
}

