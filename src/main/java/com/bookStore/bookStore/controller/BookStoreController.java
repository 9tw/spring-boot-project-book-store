package com.bookStore.bookStore.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bookStore.bookStore.entity.Book;
import com.bookStore.bookStore.services.BookService;

@Controller
public class BookStoreController {
    
    @Autowired
    private BookService service;

    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/add_book")
    public String addBook(){
        return "addBook";
    }

    @GetMapping("/library")
    public ModelAndView library(){
        List<Book> list=service.getAllBook();
        ModelAndView m = new ModelAndView();
        m.setViewName("library");
        m.addObject("book", list);
        return new ModelAndView("library", "book", list);
    }

    @GetMapping("/my_book")
    public String myBook(){
        return "myBook";
    }

    @PostMapping("/save")
    public String addBook(@ModelAttribute Book b){
        service.save(b);
        return "redirect:/library";
    }
}
