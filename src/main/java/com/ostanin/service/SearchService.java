package com.ostanin.service;

import com.ostanin.dto.Film;
import com.ostanin.repository.FilmJdbcRepository;
import com.ostanin.repository.IFilmJdbcRepository;
import com.ostanin.service.interfaces.ISearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService implements ISearchService {

    @Autowired
    IFilmJdbcRepository repository;

    public List<Film> searchFilm(String searchString, String parameter) {
        if (parameter.equals("1")) {
            return repository.searchByName(searchString);
        } else {
            return repository.searchByProducer(searchString);
        }
    }
}
