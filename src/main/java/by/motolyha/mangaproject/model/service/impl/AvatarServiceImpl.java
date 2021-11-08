package by.motolyha.mangaproject.model.service.impl;

import by.motolyha.mangaproject.exception.DaoException;
import by.motolyha.mangaproject.exception.ServiceException;
import by.motolyha.mangaproject.model.dao.AvatarDao;
import by.motolyha.mangaproject.model.dao.impl.DaoProvider;
import by.motolyha.mangaproject.model.entity.Avatar;
import by.motolyha.mangaproject.model.service.AvatarService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class AvatarServiceImpl implements AvatarService {
    private static final Logger logger = LogManager.getLogger(AvatarServiceImpl.class);
    private static final DaoProvider daoProvider = DaoProvider.getInstance();
    private static final AvatarDao avatarDao = daoProvider.getAvatarDao();

    @Override
    public Optional<Avatar> findById(String idAvatar) throws ServiceException {
        int id = Integer.parseInt(idAvatar);
        if(id < 0){
            logger.error("id cannot be below zero");
            throw new ServiceException("id cannot be below zero");
        }
        Optional<Avatar> avatar;
        try {
            avatar = avatarDao.findById(id);
        } catch (DaoException exception){
            logger.error(exception);
            throw new ServiceException(exception);
        }
        return avatar;
    }
}
