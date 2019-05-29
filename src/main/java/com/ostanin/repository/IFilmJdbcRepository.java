package com.ostanin.repository;

import com.ostanin.dto.Film;

import java.util.List;

public interface IFilmJdbcRepository {
    List<Film> findAll();
    List<Film> searchByName(String searchString);
    List<Film> searchByProducer(String searchString);
    void addFilm(Film film);
    boolean refreshFilm(Film film);
    boolean deleteFilm(long id);

}
