package com.ostanin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {



    @GetMapping(value = {"/", "/index"})
    public String index() {
        return "redirect:/home";
    }

    @GetMapping("/admin")
    public String admin() {
        return "/admin";
    }

    @GetMapping("/add")
    public String addBook(Model model) {
        return "/admin";
    }

    @RequestMapping(value = { "/addBook" }, method = RequestMethod.POST)
    public String savePerson(Model model) {

//        String name = bookForm.getName();
//        String author = bookForm.getAuthor();
//        int id = bookForm.getId();
//        int isbn = bookForm.getIsbn();
//
//        Book newBook = new Book(id, author, name, isbn);
//        bookRepository.addBook(newBook);

        return "redirect:/home";
    }

    @GetMapping("/user")
    public String user() {
        return "/user";
    }

    @GetMapping("/about")
    public String about() {
        return "/about";
    }

    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/login");
        return modelAndView;
    }

    @PostMapping
    public String loginPost() {
        return "redirect:/home";
    }

    @GetMapping("/403")
    public String error403() {
        return "/error/403";
    }


}