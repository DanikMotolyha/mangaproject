package by.motolyha.mangaproject.controller.imagecontroller;

import by.motolyha.mangaproject.controller.command.Router;

import javax.servlet.http.HttpServletRequest;

public interface Command {

    CommandResult unload(HttpServletRequest request);

    CommandResult upload(HttpServletRequest request);
}
