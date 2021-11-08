package by.motolyha.mangaproject.controller.filter;



import by.motolyha.mangaproject.controller.command.PagePath;
import by.motolyha.mangaproject.controller.command.RequestAttribute;
import by.motolyha.mangaproject.model.dto.SessionUser;
import by.motolyha.mangaproject.model.entity.Role;
import by.motolyha.mangaproject.model.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/pages/*"})
public class SecurityFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String prefix = httpServletRequest.getRequestURI().split("/")[2];
        HttpSession session = httpServletRequest.getSession();
        SessionUser user = (SessionUser) session.getAttribute(RequestAttribute.USER);
        Role role;

        if (user == null) {
            role = Role.GHOST;
        } else {
            role = user.getRole();
        }


        switch (role){
            case USER:{
                if(prefix.equals("admin")){
                    httpServletRequest.getRequestDispatcher(PagePath.ERROR_403_PAGE).forward(request, response);
                }
                break;
            }
            case GHOST:{
                if(prefix.equals("user")){
                    httpServletRequest.getRequestDispatcher(PagePath.ERROR_403_PAGE).forward(request, response);
                }
                break;
            }
        }

        filterChain.doFilter(request, response);
    }
    @Override
    public void destroy() {
    }
}
