package me.goge.controller;

import me.goge.model.Book;
import me.goge.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller("/")
public class HelloWorld {

    @Resource
    private BookService bookService;


    @RequestMapping("/get")
    @ResponseBody
    public String get(){
        Book book = new Book();
        book.setPrice(123);
        book.setName("bok");
        book.setAuthor("wo");
        bookService.addBook(book);

        return "Get";
    }

    @RequestMapping("/")
    @ResponseBody
    public String index(){
        return "Hello";
    }
}
