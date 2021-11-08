package by.motolyha.mangaproject.controller.listener;


import by.motolyha.mangaproject.controller.command.RequestAttribute;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionListener implements HttpSessionListener {
    private static final String DEFAULT_LOCALE = "RU";
    private static final String SECOND_LOCALE = "EN";
    private static final String DEFAULT_BUNDLE = "locale.ru_RU";

    @Override
    public void sessionCreated(HttpSessionEvent sessionEvent) {
        HttpSession session = sessionEvent.getSession();
        session.setAttribute(RequestAttribute.CURRENT_LOCALE, DEFAULT_LOCALE);
        session.setAttribute(RequestAttribute.SECOND_LOCALE, SECOND_LOCALE);
        session.setAttribute(RequestAttribute.CURRENT_BUNDLE, DEFAULT_BUNDLE);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent sessionEvent) {
        HttpSessionListener.super.sessionDestroyed(sessionEvent);
    }
}
