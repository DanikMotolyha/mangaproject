package by.motolyha.mangaproject.model.service;

import by.motolyha.mangaproject.exception.DaoException;
import by.motolyha.mangaproject.exception.ServiceException;
import by.motolyha.mangaproject.model.entity.Avatar;

import java.util.Optional;

public interface AvatarService {

    Optional<Avatar> findById(String id) throws ServiceException;

    //Optional<Avatar> findBySrc(String src) throws ServiceException;

    //void create(String src) throws ServiceException;
}
