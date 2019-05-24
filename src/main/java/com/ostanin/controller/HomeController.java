package com.ostanin.controller;

import com.ostanin.dto.Search;
import com.ostanin.repository.FilmJdbcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class HomeController {

    @Autowired
    FilmJdbcRepository repository;

    @GetMapping("/home")
    public String home(Model model, @RequestParam("page") Optional<Integer> page,
                       @RequestParam("size") Optional<Integer> size) {
        model.addAttribute("ALL_FILMS", repository.findAll());
        return "/home";
    }

    @RequestMapping(value = { "/searchFilm" }, method = RequestMethod.GET)
    public String searchFilm(Model model, @ModelAttribute("searchForm") Search searchForm) {
        String resultLine = searchForm.getLine();
        model.addAttribute("filmList", repository.searchByName(resultLine));
        return "/searchFilm";
    }
}
