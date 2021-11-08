package by.motolyha.mangaproject.model.service;

import by.motolyha.mangaproject.exception.ServiceException;
import by.motolyha.mangaproject.model.dto.MangaBriefDto;
import by.motolyha.mangaproject.model.entity.Manga;

import java.util.List;
import java.util.Optional;

public interface MangaService {

    List<MangaBriefDto> findAll(long offset, long count) throws ServiceException;
    long countAll() throws ServiceException;
    Optional<Manga> findById(String id) throws ServiceException;
}
