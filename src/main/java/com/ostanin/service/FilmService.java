package com.ostanin.service;

import com.ostanin.dto.Film;
import com.ostanin.repository.FilmJdbcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class FilmService {
    @Autowired
    FilmJdbcRepository repository;

    public List<Film> findAll() {
        return repository.findAll();
    }


    public Page<Film> findPaginated(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Film> list;
        List<Film> films = findAll();

        if (films.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, films.size());
            list = films.subList(startItem, toIndex);
        }

        Page<Film> filmPage
                = new PageImpl<Film>(list, PageRequest.of(currentPage, pageSize), films.size());

        return filmPage;
    }

}
