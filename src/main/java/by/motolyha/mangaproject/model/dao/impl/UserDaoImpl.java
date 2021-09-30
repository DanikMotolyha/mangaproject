package by.motolyha.mangaproject.model.dao.impl;

import by.motolyha.mangaproject.exception.DaoException;
import by.motolyha.mangaproject.model.connection.ConnectionPool;
import by.motolyha.mangaproject.model.dao.UserDao;
import by.motolyha.mangaproject.model.entity.Role;
import by.motolyha.mangaproject.model.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static by.motolyha.mangaproject.model.dao.ColumnName.*;

class UserDaoImpl implements UserDao {

    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();

    /**
     * Query for database to create new user
     */
    private static final String CREATE_SQL = "INSERT INTO USER (login, password, description,email, role, avatar_src) VALUES (?,?,?,?,?,?)";

    private static final String UPDATE_DATA_SQL = "UPDATE USER SET login = ?, description = ?, email = ?, avatar_src = ? where id = ?";

    private static final String UPDATE_PASSWORD_SQL = "UPDATE USER SET password = ? where id = ?";

    private static final String FIND_ALL_SQL = "SELECT id, login, password, description, email, role, avatar_src FROM USER";

    private static final String FIND_BY_LOGIN_SQL = "SELECT id, login, password, description, email, role, avatar_src FROM USER WHERE LOGIN = ?";

    private static final String FIND_BY_EMAIL_SQL = "SELECT id, login, password, description, email, role, avatar_src FROM USER WHERE LOGIN = ?";

    private static final String FIND_BY_ID_SQL = "SELECT id, login, password, description, email, role, avatar_src FROM USER WHERE LOGIN = ?";

    /**
     * Protected constructor without parameters
     */
    protected UserDaoImpl() {
    }

    @Override
    public void create(String login, String passwordHash, String description, String email, String avatarSrc) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(CREATE_SQL)) {
            statement.setString(1, login);
            statement.setString(2, passwordHash);
            statement.setString(3, description);
            statement.setString(4, email);
            statement.setString(5, Role.USER.name());
            statement.setString(6, avatarSrc);
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException("error when create user", e);
        }
    }
    @Override
    public void update(String login, String description, String email, String avatarSrc, Integer id) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_DATA_SQL)) {
            statement.setString(1, login);
            statement.setString(2, description);
            statement.setString(3, email);
            statement.setString(4, avatarSrc);
            statement.setString(5, id.toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("error when update with user id " + id, e);
        }
    }

    @Override
    public void updatePassword(Integer id, String password) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_PASSWORD_SQL)) {
            statement.setString(1, password);
            statement.setString(2, id.toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            String message = "error when update password " + password +
                    "in user with id " + id;
            throw new DaoException(message, e);
        }
    }

    @Override
    public List<User> findAll() throws DaoException {
        var users = new ArrayList<User>();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_SQL)) {
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Role role = Role.valueOf(result.getString(USER_ROLE));
                var user = new User(
                        result.getInt(ID),
                        result.getString(LOGIN),
                        result.getString(PASSWORD),
                        result.getString(DESCRIPTION),
                        result.getString(EMAIL),
                        role,
                        result.getString(AVATAR_SRC)
                );
                users.add(user);
            }
        } catch (SQLException e) {
            throw new DaoException("error getting all users", e);
        }
        return users;
    }

    @Override
    public Optional<User> findByLogin(String login) throws DaoException {
        User user;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_LOGIN_SQL)) {
            statement.setString(1, login);
            user = getUser(statement);
        } catch (SQLException e) {
            throw new DaoException("error getting user by login", e);
        }
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> findById(Integer id) throws DaoException {
        User user;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            statement.setString(1, id.toString());
            user = getUser(statement);
        } catch (SQLException e) {
            throw new DaoException("error getting user by id", e);
        }
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> findByEmail(String email) throws DaoException {
        User user;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_EMAIL_SQL)) {
            statement.setString(1, email);
            user = getUser(statement);
        } catch (SQLException e) {
            throw new DaoException("error getting user by email", e);
        }
        return Optional.ofNullable(user);
    }

    private User getUser(PreparedStatement statement) throws SQLException {
        ResultSet result = statement.executeQuery();
        User user = null;
        while (result.next()) {
            Role role = Role.valueOf(result.getString(USER_ROLE));
            user = new User(
                    result.getInt(ID),
                    result.getString(LOGIN),
                    result.getString(PASSWORD),
                    result.getString(DESCRIPTION),
                    result.getString(EMAIL),
                    role,
                    result.getString(AVATAR_SRC)
            );
        }
        return user;
    }
}

