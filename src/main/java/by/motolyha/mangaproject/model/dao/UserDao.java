package by.motolyha.mangaproject.model.dao;

import by.motolyha.mangaproject.exception.DaoException;
import by.motolyha.mangaproject.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    List<User> findAll() throws DaoException;

    void create(String login, String passwordHash, String description, String email, String avatarSrc) throws DaoException;

    void update(String login, String description, String email, String avatarSrc, Integer id) throws DaoException;

    void updatePassword(Integer id, String passwordHash) throws DaoException;

    Optional<User> findByLogin(String login) throws DaoException;

    Optional<User> findById(Integer id) throws DaoException;

    Optional<User> findByEmail(String email) throws DaoException;
}
