package by.motolyha.mangaproject.controller.command;

public final class PagePath {


    /**
     * errors pages
     */
    public static final String ERROR_PAGE = "pages/errors/error.jsp";
    public static final String ERROR_404_PAGE = "pages/errors/404.jsp";
    public static final String ERROR_403_PAGE = "/pages/errors/403.jsp";


    public static final String HOME_PAGE = "/pages/home.jsp";


    public static final String INDEX_PAGE = "/index.jsp";


    /**
     * users pages
     */
    public static final String PROFILE_PAGE = "pages/user/profile.jsp";
    public static final String EDIT_PROFILE_PAGE = "pages/user/editProfile.jsp";

    /**
    * common pages
    */

    //auth pages
    public static final String SIGN_IN_PAGE = "pages/auth/signIn.jsp";
    public static final String RESET_PASSWORD_PAGE = "pages/auth/resetPassword.jsp";
    public static final String RESET_PASSWORD_SUCCESS_PAGE = "pages/auth/resetPasswordSuccess.jsp";
    public static final String SIGN_UP_PAGE = "pages/auth/signUp.jsp";
    public static final String SIGN_UP_SUCCESS_PAGE = "pages/auth/signUpSuccess.jsp";
    //manga pages
    public static final String MANGA_INFO_PAGE = "pages/mangaInfo.jsp";

    private PagePath() {
    }
}
