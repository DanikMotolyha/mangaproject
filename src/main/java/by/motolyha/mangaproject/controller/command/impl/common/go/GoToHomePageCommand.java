package by.motolyha.mangaproject.controller.command.impl.common.go;

import by.motolyha.mangaproject.controller.command.*;
import by.motolyha.mangaproject.exception.ServiceException;
import by.motolyha.mangaproject.model.dto.MangaBriefDto;
import by.motolyha.mangaproject.model.service.MangaService;
import by.motolyha.mangaproject.model.service.impl.ServiceProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.List;

import static by.motolyha.mangaproject.controller.command.Router.RouterType.*;
import static by.motolyha.mangaproject.controller.command.PagePath.*;

public class GoToHomePageCommand implements Command {
    private static final Logger logger = LogManager.getLogger(GoToHomePageCommand.class);
    private static final int COUNT_MANGA_ON_PAGE = 16;

    @Override
    public Router execute(HttpServletRequest request) {

        String currentPage = request.getParameter(RequestParameter.PAGE);
        int page = 0;
        if (currentPage == null) {
            page = findCurrentPageInCookie(request);
        } else {
            try {
                page = Integer.parseInt(currentPage);
            } catch (Exception exception) {
                logger.error("cant parse value of page to int" + currentPage);
            }
        }

        MangaService mangaService = ServiceProvider.getInstance().getMangaService();
        List<MangaBriefDto> mangaList = null;
        HttpSession session = request.getSession();
        long count;
        try {
            mangaList = mangaService.findAll(page * 16L, COUNT_MANGA_ON_PAGE);
            count = mangaService.countAll();
        } catch (ServiceException exception) {
            logger.error(exception);
            session.setAttribute(RequestAttribute.EXCEPTION, exception);
            return new Router(ERROR_PAGE, REDIRECT);
        }
        int pages = (int) Math.ceil(count * 1.0 / COUNT_MANGA_ON_PAGE);
        session.setAttribute(RequestAttribute.MAX_PAGES, pages - 1);
        session.setAttribute(RequestAttribute.CURRENT_PAGE, page);
        session.setAttribute(RequestAttribute.MANGA_LIST_DATA, mangaList);
        Cookie cookie = new Cookie(CookieName.CURRENT_PAGE, String.valueOf(page));
        cookie.setMaxAge(24 * 60 * 60);
        return new Router(HOME_PAGE, REDIRECT, cookie);
    }

    private int findCurrentPageInCookie(HttpServletRequest req) {
        Cookie[] cookies = req.getCookies();
        Cookie pageNumberCookie = null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(CookieName.CURRENT_PAGE)) {
                pageNumberCookie = cookie;
                break;
            }
        }

        int currentPage = 0;
        if (pageNumberCookie != null) {
            try {
                currentPage = Integer.parseInt(pageNumberCookie.getValue());
            } catch (NumberFormatException exception) {
                logger.error("cannot parse cookie:pageNumberCookie to int, cookie value:" + pageNumberCookie.getValue());
            }
        }
        return currentPage;
    }
}
