package by.motolyha.mangaproject.model.dao.impl;

import by.motolyha.mangaproject.model.dao.AvatarDao;
import by.motolyha.mangaproject.model.dao.MangaDao;
import by.motolyha.mangaproject.model.dao.UserDao;

public class DaoProvider {
    private static final DaoProvider instance = new DaoProvider();

    private static final UserDao userDao = new UserDaoImpl();
    private static final MangaDao mangaDao = new MangaDaoImpl();
    private static final AvatarDao avatarDao = new AvatarDaoImpl();

    private DaoProvider() {
    }

    public static DaoProvider getInstance() {
        return instance;
    }

    public UserDao getUserDao() {
        return userDao;
    }
    public MangaDao getMangaDao() {
        return mangaDao;
    }
    public AvatarDao getAvatarDao() {
        return avatarDao;
    }
}
