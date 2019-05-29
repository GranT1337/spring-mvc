package com.ostanin.controller;

import com.ostanin.Exception.NotNormalAddFilmException;
import com.ostanin.dto.Film;
import com.ostanin.repository.FilmJdbcRepository;
import com.ostanin.repository.IFilmJdbcRepository;
import com.ostanin.service.FilmService;
import com.ostanin.service.PageService;
import com.ostanin.service.SearchService;
import com.ostanin.service.interfaces.IFilmService;
import com.ostanin.service.interfaces.IPageService;
import com.ostanin.service.interfaces.ISearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class HomeController {

    @Autowired
    ISearchService searchService;

    @Autowired
    IFilmService filmService;

    @Autowired
    IFilmJdbcRepository filmJdbcRepository;

    @Autowired
    IPageService pageService;

    @GetMapping("/home")
    public String home(Model model, @RequestParam("page") Optional<Integer> page,
                       @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);
        pageService.getPaginatedModel(currentPage,pageSize,model);
        model.addAttribute("ALL_FILMS", filmService.findAll());

        return "/home";
    }

    @GetMapping("/searchFilm")
    public String searchFilm(Model model, @RequestParam(required=false, value="q") String searchForm, @RequestParam(required=false, value="options") String parameter) {
        model.addAttribute("filmList", searchService.searchFilm(searchForm, parameter));

        return "/searchFilm";
    }

    @PostMapping("/addFilm")
    public ModelAndView addFilm(@Valid Film newFilm, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView();
        if (result.hasErrors()) {
            modelAndView.addObject("errors", result.getAllErrors());
            modelAndView.setViewName("error");
        } else {
            try {
                filmJdbcRepository.addFilm(newFilm);
                modelAndView.setViewName("redirect:/home");
                return modelAndView;
            } catch (NotNormalAddFilmException e) {
                modelAndView.addObject("errors", e.getMessage());
                modelAndView.setViewName("error");
            }
        }
        return modelAndView;
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
