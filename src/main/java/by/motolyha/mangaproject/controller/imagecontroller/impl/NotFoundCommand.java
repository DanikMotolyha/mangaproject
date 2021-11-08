package by.motolyha.mangaproject.controller.imagecontroller.impl;

import by.motolyha.mangaproject.controller.command.PagePath;
import by.motolyha.mangaproject.controller.command.RequestAttribute;
import by.motolyha.mangaproject.controller.imagecontroller.Command;
import by.motolyha.mangaproject.controller.imagecontroller.CommandResult;

import javax.servlet.http.HttpServletRequest;

public class NotFoundCommand implements Command {
    @Override
    public CommandResult unload(HttpServletRequest request) {
        request.getSession().setAttribute(RequestAttribute.EXCEPTION, "not found command");
        return new CommandResult(PagePath.ERROR_PAGE);
    }

    @Override
    public CommandResult upload(HttpServletRequest request) {
        request.getSession().setAttribute(RequestAttribute.EXCEPTION, "not found command");
        return new CommandResult(PagePath.ERROR_PAGE);
    }
}
