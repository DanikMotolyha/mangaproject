package by.motolyha.mangaproject.controller.command.impl.user;

import by.motolyha.mangaproject.controller.command.Command;
import by.motolyha.mangaproject.controller.command.RequestAttribute;
import by.motolyha.mangaproject.controller.command.RequestParameter;
import by.motolyha.mangaproject.controller.command.Router;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.*;
import java.util.ResourceBundle;

import static by.motolyha.mangaproject.controller.command.PagePath.EDIT_PROFILE_PAGE;
import static by.motolyha.mangaproject.controller.command.PagePath.ERROR_PAGE;
import static by.motolyha.mangaproject.controller.command.Router.RouterType.REDIRECT;

public class UpdateUserProfileDataCommand implements Command {
    private static final Logger logger = LogManager.getRootLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;

        InputStream inputStream;
        OutputStream outStream;
        HttpSession session = request.getSession();
        try {
            String login = request.getParameter(RequestParameter.DESCRIPTION);
            inputStream = request.getPart(RequestParameter.AVATAR).getInputStream();
            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
            final String path = ResourceBundle.getBundle("path").getString("path.image");
            File imageFile = new File(path + "11");
            if (!imageFile.exists()) {
                imageFile.createNewFile();
            }
            outStream = new FileOutputStream(imageFile);
            outStream.write(buffer);
            outStream.close();
        }catch (IOException | ServletException e){
            session.setAttribute(RequestAttribute.EXCEPTION, e);
            return new Router(ERROR_PAGE, REDIRECT);
        }
        return new Router(EDIT_PROFILE_PAGE, REDIRECT);
    }
}
