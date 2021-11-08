package by.motolyha.mangaproject.model.dao.impl;

import by.motolyha.mangaproject.exception.DaoException;
import by.motolyha.mangaproject.model.connection.ConnectionPool;
import by.motolyha.mangaproject.model.dao.MangaDao;
import by.motolyha.mangaproject.model.entity.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static by.motolyha.mangaproject.model.dao.ColumnName.*;

public class MangaDaoImpl implements MangaDao {
    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static final String FIND_ALL_WITH_OFFSET_SQL =
            "SELECT m.id, m.name, m.description, m.post_date, m.status, a.id as `id_avatar`, a.src FROM " +
            "MANGA m JOIN AVATAR a ON m.ID_AVATAR=a.id " +
            "LIMIT ? OFFSET ?";

    private static final String COUNT_ALL_SQL = "SELECT COUNT(*)  AS `ALL` FROM MANGA";

    private static final String FIND_BY_ID_SQL =
            "SELECT m.id, m.name, m.description, m.post_date, m.status, a.id as `id_avatar`, a.src FROM " +
            "MANGA m JOIN AVATAR a ON m.ID_AVATAR=a.id " +
            "WHERE m.id = ?";

    protected MangaDaoImpl() {
    }

    @Override
    public List<Manga> findAll(long offset, long count) throws DaoException {
        var mangaList = new ArrayList<Manga>();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_WITH_OFFSET_SQL)) {
            statement.setLong(1, count);
            statement.setLong(2, offset);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Manga manga = new Manga();
                manga.setId(result.getInt(ID));
                manga.setName(result.getString(NAME));
                manga.setDescription(result.getString(DESCRIPTION));

                Date date = result.getDate(POST_DATE);
                LocalDate currentDate = date.toLocalDate();
                manga.setPostDate(currentDate);
                Optional<MangaStatus> status = Arrays.stream(MangaStatus.values()).filter(elem -> {
                    try {
                        return elem.getName().equals(result.getString(STATUS));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    return false;
                }).findFirst();
                if (status.isEmpty()) {
                    throw new DaoException("cannot resolve book status");
                }
                manga.setMangaStatus(status.get());

                Avatar avatar = new Avatar();
                avatar.setId(result.getInt(ID_AVATAR));
                avatar.setSrc(result.getString(SRC));
                manga.setAvatar(avatar);

                mangaList.add(manga);
            }
        } catch (SQLException e) {
            throw new DaoException("error getting all manga", e);
        }
        return mangaList;
    }

    @Override
    public long countAll() throws DaoException {
        long count = 0;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(COUNT_ALL_SQL)) {
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                count = result.getLong(ALL);
            }
        } catch (SQLException e) {
            throw new DaoException("error getting all manga", e);
        }
        return count;
    }

    @Override
    public Optional<Manga> findById(Integer id) throws DaoException {
        Manga manga = null;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            statement.setString(1, id.toString());
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                manga = new Manga();
                manga.setId(result.getInt(ID));
                manga.setName(result.getString(NAME));
                manga.setDescription(result.getString(DESCRIPTION));

                MangaStatus status = MangaStatus.valueOfName(result.getString(STATUS));
                Date date = result.getDate(POST_DATE);
                LocalDate localDate = date.toLocalDate();
                manga.setPostDate(localDate);
                manga.setMangaStatus(status);

                Avatar avatar = new Avatar();
                avatar.setId(result.getInt(ID_AVATAR));
                avatar.setSrc(result.getString(SRC));
                manga.setAvatar(avatar);

            }
        } catch (SQLException e) {
            throw new DaoException("error getting manga by id", e);
        }
        return Optional.ofNullable(manga);
    }
}
