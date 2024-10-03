package com.henry.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class EntryPointController {

    @GetMapping
    public String redirect() {
        return "redirect:https://www.youtube.com/watch?v=LvSuEqpq_TM";
    }
}
