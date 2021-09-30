package by.motolyha.mangaproject.model.service.impl;

import by.motolyha.mangaproject.model.service.UserService;

public class ServiceProvider {
    private static final ServiceProvider instance = new ServiceProvider();

    private final UserService userService = new UserServiceImpl();

    private ServiceProvider() {
    }

    public static ServiceProvider getInstance() {
        return instance;
    }

    public UserService getUserService(){
        return userService;
    }
}
