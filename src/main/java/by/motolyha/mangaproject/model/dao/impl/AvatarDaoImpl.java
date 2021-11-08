package by.motolyha.mangaproject.model.dao.impl;

import by.motolyha.mangaproject.exception.DaoException;
import by.motolyha.mangaproject.model.connection.ConnectionPool;
import by.motolyha.mangaproject.model.dao.AvatarDao;
import by.motolyha.mangaproject.model.entity.Avatar;
import by.motolyha.mangaproject.model.entity.Role;

import java.sql.*;
import java.time.LocalDate;
import java.util.Optional;

import static by.motolyha.mangaproject.model.dao.ColumnName.*;

public class AvatarDaoImpl implements AvatarDao {
    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static final String CREATE_SQL = "INSERT INTO USER (src) VALUES (?)";

    private static final String FIND_BY_ID_SQL = "SELECT * FROM AVATAR WHERE ID = ?";

    private static final String FIND_BY_SRC_SQL = "SELECT * FROM AVATAR WHERE SRC = ?";

    protected AvatarDaoImpl() {
    }

    @Override
    public Optional<Avatar> findById(Integer id) throws DaoException {
        Avatar avatar = null;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            statement.setString(1, id.toString());
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                avatar = new Avatar();
                avatar.setId(result.getInt(ID));
                avatar.setSrc(result.getString(SRC));
            }
        } catch (SQLException e) {
            throw new DaoException("error getting user by id", e);
        }
        return Optional.ofNullable(avatar);
    }

    @Override
    public Optional<Avatar> findBySrc(String src) throws DaoException {
        Avatar avatar = null;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_SRC_SQL)) {
            statement.setString(1, src);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                avatar = new Avatar();
                avatar.setId(result.getInt(ID));
                avatar.setSrc(result.getString(SRC));
            }
        } catch (SQLException e) {
            throw new DaoException("error getting user by id", e);
        }
        return Optional.ofNullable(avatar);
    }

    @Override
    public void create(String src) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(CREATE_SQL)) {
            statement.setString(1, src);
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException("error when create avatar", e);
        }
    }
}
