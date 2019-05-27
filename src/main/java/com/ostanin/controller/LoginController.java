package com.ostanin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    @GetMapping("/login/international")
    public String getInternationalPage() {
        return "redirect:/login";
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