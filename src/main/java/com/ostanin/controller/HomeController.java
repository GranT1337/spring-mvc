package com.ostanin.controller;

import com.ostanin.dto.Film;
import com.ostanin.repository.FilmJdbcRepository;
import com.ostanin.service.FilmService;
import com.ostanin.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class HomeController {

    @Autowired
    SearchService searchService;

    @Autowired
    FilmService filmService;

    @Autowired
    FilmJdbcRepository filmJdbcRepository;

    @GetMapping("/home")
    public String home(Model model, @RequestParam("page") Optional<Integer> page,
                       @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        Page<Film> filmPage = filmService.findPaginated(PageRequest.of(currentPage - 1, pageSize));

        model.addAttribute("filmPage", filmPage);

        int totalPages = filmPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        model.addAttribute("ALL_FILMS", filmService.findAll());
        return "/home";
    }

    @GetMapping("/searchFilm")
    public String searchFilm(Model model, @RequestParam(required=false, value="q") String searchForm, @RequestParam(required=false, value="options") String parameter) {
        model.addAttribute("filmList", searchService.searchFilm(searchForm, parameter));

        return "/searchFilm";
    }

    @RequestMapping(value = { "/addFilm" }, method = RequestMethod.POST)
    public String addFilm(Model model, @RequestParam("id") long id, @RequestParam("title") String title, @RequestParam("producer") String producer, @RequestParam("points") double points) {
        Film newFilm = new Film(id, title, producer, points);
        filmJdbcRepository.addFilm(newFilm);
        return "redirect:/home";
    }

    @GetMapping("/editFilm")
    @ResponseBody
    public boolean refreshFilm(Film film) {
        return filmJdbcRepository.refreshFilm(film);
    }

    @GetMapping("/deleteFilm")
    @ResponseBody
    public boolean deleteFilm(long id) {
        return filmJdbcRepository.deleteFilm(id);
    }



}
