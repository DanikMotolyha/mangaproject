package by.motolyha.mangaproject.model.dao;

import by.motolyha.mangaproject.exception.DaoException;
import by.motolyha.mangaproject.exception.ServiceException;
import by.motolyha.mangaproject.model.entity.Manga;
import by.motolyha.mangaproject.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface MangaDao {

    List<Manga> findAll(long offset, long count) throws DaoException;
    long countAll() throws DaoException;
    Optional<Manga> findById(Integer id) throws DaoException;
}
