package by.motolyha.mangaproject.controller.command;

public final class PagePath {


    /**
     * errors pages
     */
    public static final String ERROR_PAGE = "pages/errors/error.jsp";
    public static final String ERROR_404_PAGE = "pages/errors/404.jsp";
    public static final String ERROR_403_PAGE = "pages/errors/403.jsp";



    public static final String MAIN_PAGE = "pages/main.jsp";


    /**
     * users pages
     */
    public static final String PROFILE_PAGE = "pages/user/profile.jsp";



    /**
     * auth pages
     */
    public static final String SIGN_IN_PAGE = "pages/auth/signIn.jsp";
    public static final String SIGN_UP_PAGE = "pages/auth/signUp.jsp";
    public static final String SIGN_UP_SUCCESS_PAGE = "pages/auth/signUpSuccess.jsp";

    private PagePath() {
    }
}
