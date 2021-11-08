package by.motolyha.mangaproject.controller.imagecontroller;

import by.motolyha.mangaproject.controller.command.PagePath;
import by.motolyha.mangaproject.controller.command.RequestParameter;
import by.motolyha.mangaproject.controller.command.Router;
import by.motolyha.mangaproject.model.service.UserService;
import by.motolyha.mangaproject.model.service.impl.ServiceProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ResourceBundle;


@WebServlet(urlPatterns = "/image", loadOnStartup = 1)
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 25)
public class ImageServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(ImageServlet.class);


    private static final String CONTENT_TYPE = "image/jpeg";
    private static final String BUNDLE_NAME = "path";
    private static final String FILE_TYPE = ".jpg";
    private static final String PATH_AVATAR = "path.avatar";


    private static final CommandImageProvider COMMAND_PROVIDER = CommandImageProvider.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String commandName = req.getParameter(RequestParameter.COMMAND);
        Command command = COMMAND_PROVIDER.getCommand(commandName);
        CommandResult result = command.upload(req);
        //resp.sendRedirect(path);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String commandName = req.getParameter(RequestParameter.COMMAND);
        Command command = COMMAND_PROVIDER.getCommand(commandName);
        CommandResult result = command.unload(req);

        if (result.getPage() != null) {
            resp.sendRedirect(result.getPage());
        } else if (result.getImage() != null) {
            byte[] bytes = result.getImage();
            resp.setContentType(CONTENT_TYPE);
            resp.setContentLength(bytes.length);
            resp.getOutputStream().write(bytes);
        } else {
            resp.sendRedirect(PagePath.ERROR_PAGE);
        }
    }

    private void uploadUserImage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter(RequestParameter.ID);
        InputStream inputStream = request.getPart(RequestParameter.AVATAR).getInputStream();
        byte[] buffer = new byte[inputStream.available()];
        if (buffer.length > 1000000) {
            //todo!!!
            /*request.setAttribute(RequestAttribute.MAX_SIZE, true);
            RequestDispatcher dispatcher = request.getRequestDispatcher(router.getPagePath());
            dispatcher.forward(request, response);*/
        }
        inputStream.read(buffer);
        final String path = ResourceBundle.getBundle(BUNDLE_NAME).getString(PATH_AVATAR);
        File imageFile = new File(path + userId + FILE_TYPE);
        if (!imageFile.exists()) {
            imageFile.createNewFile();
        }
        OutputStream outStream;
        outStream = new FileOutputStream(imageFile);
        outStream.write(buffer);
        outStream.close();


        //выгрузка
/*
        String type = req.getParameter(RequestParameter.UPLOADER_TYPE);
        String id = req.getParameter(RequestParameter.ID);
        String src = "";
        PagePath pagePath;
        try {
            if (MANGA_IMAGE.equals(type)) {
                //todo
            } else if (USER_IMAGE.equals(type)) {
                UserService userService = ServiceProvider.getInstance().getUserService();
                src = userService.findAvatarStrById(id);
            } else {
                throw new ServletException("incorrect uploader_type");
            }

            if(src.equals("")){
                throw new ServletException("cannot Find");
            }
            StringBuilder path = new StringBuilder()
                    .append(ABSOLUTE_PATH)
                    .append(src);
            byte[] imageBytes = null;
            imageBytes = Files.readAllBytes(Paths.get(path.toString()));

            resp.setContentType(CONTENT_TYPE);
            resp.setContentLength(imageBytes.length);
            resp.getOutputStream().write(imageBytes);

        } catch (Exception e) {
            logger.error(e.getMessage());
            req.getSession().setAttribute(RequestAttribute.EXCEPTION, e);
            resp.sendRedirect(PagePath.ERROR_PAGE);
        }
        */
    }
}
