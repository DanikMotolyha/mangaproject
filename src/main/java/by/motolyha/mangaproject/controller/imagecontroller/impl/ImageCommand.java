package by.motolyha.mangaproject.controller.imagecontroller.impl;

import by.motolyha.mangaproject.controller.command.PagePath;
import by.motolyha.mangaproject.controller.command.RequestAttribute;
import by.motolyha.mangaproject.controller.command.RequestParameter;
import by.motolyha.mangaproject.controller.command.Router;
import by.motolyha.mangaproject.controller.imagecontroller.Command;
import by.motolyha.mangaproject.controller.imagecontroller.CommandResult;
import by.motolyha.mangaproject.exception.ServiceException;
import by.motolyha.mangaproject.model.entity.Avatar;
import by.motolyha.mangaproject.model.service.AvatarService;
import by.motolyha.mangaproject.model.service.impl.ServiceProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

public class ImageCommand implements Command {
    private static final Logger logger = LogManager.getLogger(ImageCommand.class);
    private static final String ABSOLUTE_PATH = "d:/data/mangaproject/";

    @Override
    public CommandResult unload(HttpServletRequest request) {
        String id = request.getParameter(RequestParameter.ID);
        String src;

        ServiceProvider provider = ServiceProvider.getInstance();
        AvatarService avatarService = provider.getAvatarService();
        byte[] imageBytes = null;
        try {
            Optional<Avatar> avatar = avatarService.findById(id);
            if(avatar.isEmpty()){
                request.getSession().setAttribute(RequestAttribute.EXCEPTION, "cannot find ");
                return new CommandResult(PagePath.ERROR_404_PAGE);
            }
            src = avatar.get().getSrc();
            String path = ABSOLUTE_PATH + src;
            imageBytes = Files.readAllBytes(Paths.get(path));
        } catch (ServiceException | IOException exception) {
            logger.error(exception.getMessage());
            request.getSession().setAttribute(RequestAttribute.EXCEPTION, exception);
            return new CommandResult(PagePath.ERROR_PAGE);
        }
        return new CommandResult(imageBytes);
    }

    @Override
    public CommandResult upload(HttpServletRequest request) {
        return null;
    }
}
