package by.motolyha.mangaproject.model.dao.impl;

import by.motolyha.mangaproject.model.dao.UserDao;

public class DaoProvider {
    private static final DaoProvider instance = new DaoProvider();

    private static final UserDao userDao = new UserDaoImpl();

    private DaoProvider() {
    }

    public static DaoProvider getInstance() {
        return instance;
    }

    public UserDao getUserDao() {
        return userDao;
    }
}
