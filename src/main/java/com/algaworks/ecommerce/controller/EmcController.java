package com.algaworks.ecommerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmcController {

    @GetMapping
    public String index() {
        return "index";
    }
}
