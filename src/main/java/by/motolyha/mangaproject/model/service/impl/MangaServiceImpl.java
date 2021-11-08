package by.motolyha.mangaproject.model.service.impl;

import by.motolyha.mangaproject.exception.DaoException;
import by.motolyha.mangaproject.exception.ServiceException;
import by.motolyha.mangaproject.model.dao.MangaDao;
import by.motolyha.mangaproject.model.dao.impl.DaoProvider;
import by.motolyha.mangaproject.model.dto.MangaBriefDto;
import by.motolyha.mangaproject.model.dto.MangaInfoDto;
import by.motolyha.mangaproject.model.entity.Manga;
import by.motolyha.mangaproject.model.service.MangaService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MangaServiceImpl implements MangaService {
    private static final Logger logger = LogManager.getLogger(MangaServiceImpl.class);
    private static final DaoProvider daoProvider = DaoProvider.getInstance();
    private static final MangaDao mangaDao = daoProvider.getMangaDao();

    protected MangaServiceImpl() {
    }

    @Override
    public List<MangaBriefDto> findAll(long offset, long count) throws ServiceException {
        List<Manga> mangasList;
        try {
            mangasList = mangaDao.findAll(offset, count);
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return convertTyMangaBriefDto(mangasList);
    }

    @Override
    public long countAll() throws ServiceException {
        long count;
        try {
            count = mangaDao.countAll();
        } catch (DaoException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return count;
    }

    @Override
    public Optional<Manga> findById(String mangaId) throws ServiceException {
        int id = Integer.parseInt(mangaId);
        if (id < 0) {
            logger.error("id cannot be below zero");
            throw new ServiceException("id cannot be below zero");
        }
        Optional<Manga> manga;
        try {
            manga = mangaDao.findById(id);
        } catch (DaoException exception) {
            logger.error(exception);
            throw new ServiceException(exception);
        }
        return manga;
    }

    private List<MangaBriefDto> convertTyMangaBriefDto(List<Manga> list) {
        if (list == null) {
            return null;
        }
        return list.stream().map(manga -> new MangaBriefDto(
                manga.getId(),
                manga.getName(),
                manga.getAvatar().getId(),
                manga.getPostDate(),
                manga.getMangaStatus()
        )).collect(Collectors.toList());
    }
}
