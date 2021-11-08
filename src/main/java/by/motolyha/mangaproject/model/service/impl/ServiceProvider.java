package by.motolyha.mangaproject.model.service.impl;

import by.motolyha.mangaproject.model.service.AvatarService;
import by.motolyha.mangaproject.model.service.MangaService;
import by.motolyha.mangaproject.model.service.UserService;

public class ServiceProvider {
    private static final ServiceProvider instance = new ServiceProvider();

    private final UserService userService = new UserServiceImpl();
    private final MangaService mangaService = new MangaServiceImpl();
    private final AvatarService avatarService = new AvatarServiceImpl();

    private ServiceProvider() {
    }

    public static ServiceProvider getInstance() {
        return instance;
    }

    public UserService getUserService() {
        return userService;
    }

    public MangaService getMangaService() {
        return mangaService;
    }
    public AvatarService getAvatarService() {
        return avatarService;
    }
}
