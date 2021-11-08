package by.motolyha.mangaproject.model.dao;

import by.motolyha.mangaproject.exception.DaoException;
import by.motolyha.mangaproject.model.entity.Avatar;
import by.motolyha.mangaproject.model.entity.User;

import java.util.Optional;

public interface AvatarDao {

    Optional<Avatar> findById(Integer id) throws DaoException;

    Optional<Avatar> findBySrc(String src) throws DaoException;

    void create(String src) throws DaoException;

}
