package me.goge.controller;

import me.goge.model.Book;
import me.goge.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@Controller("/")
public class HelloWorld {

    @Resource
    private BookService bookService;


    @RequestMapping("/get")
    @ResponseBody
    public Book get(@RequestParam int id) {
        return bookService.getBookById(id);
    }

    @RequestMapping("/getAll")
    @ResponseBody
    public List getAll() {
        return bookService.getAllBooks();
    }


    @RequestMapping("/delete")
    @ResponseBody
    public String delete(@RequestParam int id) {
        bookService.deleteBook(id);
        return "delete";
    }

    @RequestMapping("/deleteCache")
    @ResponseBody
    public String deleteCache() {
        bookService.deleteAllCache();
        return "delete all cache";
    }

    @RequestMapping("/add")
    @ResponseBody
    public String add() {
        Book book = new Book();
        book.setPrice(100);
        book.setName("bok");
        book.setAuthor("wo");
        bookService.addBook(book);
        return "add";
    }

    @RequestMapping("/valid")
    @ResponseBody
    public String valid(@Valid Book book, BindingResult result) {
        System.out.println(result.getAllErrors());
        for (Object object : result.getAllErrors()) {
            if (object instanceof FieldError) {
                FieldError fieldError = (FieldError) object;

                System.out.println(fieldError.getField() + ":"
                        + fieldError.getCode());

            }

            if (object instanceof ObjectError) {
                ObjectError objectError = (ObjectError) object;
                System.out.println(objectError.getCode() + ":"
                        + objectError.getObjectName() +": "+objectError.getDefaultMessage());
            }
        }
        return result.getAllErrors().toString();
    }

    @RequestMapping("/")
    public String index() {
        return "index";
    }
}
