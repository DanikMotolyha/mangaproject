package by.motolyha.mangaproject.controller.command;

import javax.servlet.http.Cookie;

public class Router {

    public enum RouterType {
        FORWARD, REDIRECT
    }

    private final String pagePath;
    private final RouterType routerType;
    private Cookie[] cookies;

    public Router(String pagePath, RouterType routerType) {
        this.pagePath = pagePath;
        this.routerType = routerType;
    }
    public Router(String pagePath, RouterType routerType, Cookie ... cookies) {
        this.pagePath = pagePath;
        this.routerType = routerType;
        this.cookies = cookies;
    }

    public String getPagePath() {
        return pagePath;
    }

    public RouterType getRouterType() {
        return routerType;
    }

    public Cookie[] getCookies() {
        return cookies;
    }
}
