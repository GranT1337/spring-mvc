package com.ostanin.service.interfaces;

import com.ostanin.dto.Film;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IFilmService {
    List<Film> findAll();
    Page<Film> findPaginated(Pageable pageable);

}
