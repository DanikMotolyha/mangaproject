package by.motolyha.mangaproject.controller.command.impl.common;

import by.motolyha.mangaproject.controller.command.Command;
import by.motolyha.mangaproject.controller.command.RequestParameter;
import by.motolyha.mangaproject.controller.command.Router;
import by.motolyha.mangaproject.controller.command.RequestAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ChangeLocaleCommand implements Command {
    private static final String RUSSIAN_LOCALE = "RU";
    private static final String ENGLISH_LOCALE = "EN";
    private static final String ENGLISH_BUNDLE = "locale.en_EN";
    private static final String RUSSIAN_BUNDLE = "locale.ru_RU";


    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();

        String current_uri = request.getParameter(RequestParameter.CURRENT_URI);

        String locale = request.getParameter(RequestParameter.LOCALE);
        if(locale.equals(RUSSIAN_LOCALE)){
            session.setAttribute(RequestAttribute.CURRENT_LOCALE, ENGLISH_LOCALE);
            session.setAttribute(RequestAttribute.CURRENT_BUNDLE, ENGLISH_BUNDLE);
            session.setAttribute(RequestAttribute.SECOND_LOCALE, RUSSIAN_LOCALE);
        }else {
            session.setAttribute(RequestAttribute.CURRENT_LOCALE, RUSSIAN_LOCALE);
            session.setAttribute(RequestAttribute.CURRENT_BUNDLE, RUSSIAN_BUNDLE);
            session.setAttribute(RequestAttribute.SECOND_LOCALE, ENGLISH_LOCALE);
        }
        //todo main_page
        return new Router(current_uri, Router.RouterType.REDIRECT);
    }
}
