package com.bookStore.bookStore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookStoreController {
    

    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/add_book")
    public String addBook(){
        return "addBook";
    }

    @GetMapping("/library")
    public String library(){
        return "library";
    }
    @GetMapping("/my_book")
    public String myBook(){
        return "myBook";
    }
}
