package by.motolyha.mangaproject.model.dao;

import by.motolyha.mangaproject.exception.DaoException;
import by.motolyha.mangaproject.model.entity.User;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserDao {

    List<User> findAll() throws DaoException;

    void create(String login, String passwordHash, String description, String email, int idAvatarImage) throws DaoException;

    void update(User user) throws DaoException;

    Optional<User> findByLogin(String login) throws DaoException;

    Optional<User> findById(Integer id) throws DaoException;

    Optional<User> findByEmail(String email) throws DaoException;
}
