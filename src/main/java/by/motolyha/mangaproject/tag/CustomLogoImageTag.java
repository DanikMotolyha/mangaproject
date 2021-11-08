package by.motolyha.mangaproject.tag;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class CustomLogoImageTag extends TagSupport {
    private static final Logger logger = LogManager.getLogger(CustomLogoImageTag.class);

    @Override
    public int doStartTag() {
        try {
            JspWriter out = pageContext.getOut();
            out.write("<a href=\"/Controller?command=go_to_home_page\"><img class=\"img-responsive\" src=\"/static/logo.png\"></a>");
        } catch (IOException e) {
            logger.error("Error while writing to out stream for tag", e);
        }
        return SKIP_BODY;
    }
}