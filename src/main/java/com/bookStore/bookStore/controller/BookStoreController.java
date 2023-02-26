package com.bookStore.bookStore.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bookStore.bookStore.entity.Book;
import com.bookStore.bookStore.entity.MyBookList;
import com.bookStore.bookStore.services.BookService;
import com.bookStore.bookStore.services.MyBookListService;

@Controller
public class BookStoreController {
    
    @Autowired
    private BookService service;

    @Autowired
    private MyBookListService myBookService;

    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/add_book")
    public String addBook(){
        return "addBook";
    }

    @RequestMapping("/editBook/{id}")
    public String editBook(@PathVariable("id") int id, Model model){
        Book b = service.getBookById(id);
        model.addAttribute("book", b);
        return "editBook";
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
    public String myBook(Model model){
        List<MyBookList> list = myBookService.getAllBooks();
        model.addAttribute("book", list);
        return "myBook";
    }

    @PostMapping("/save")
    public String addBook(@ModelAttribute Book b){
        service.save(b);
        return "redirect:/library";
    }

    @RequestMapping("/mylist/{id}")
    public String getMyList(@PathVariable("id") int id){
        Book b = service.getBookById(id);
        MyBookList mb = new MyBookList(b.getId(), b.getName(), b.getAuthor(), b.getPrice());
        myBookService.saveMyBooks(mb);
        return "redirect:/my_book";
    }
}
