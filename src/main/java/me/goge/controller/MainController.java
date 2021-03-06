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
public class MainController {


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
        if (!result.hasErrors()) {
            return "ok";
        }
        String rst = "";
        for (Object object : result.getAllErrors()) {
            if (object instanceof FieldError) {
                FieldError fieldError = (FieldError) object;
                rst += fieldError.getField() + ":"
                        + fieldError.getCode() + "\n";

            }

            if (object instanceof ObjectError) {
                ObjectError objectError = (ObjectError) object;
                rst += objectError.getCode() + ":"
                        + objectError.getObjectName() + ": " + objectError.getDefaultMessage() + "\n";
            }
        }
        System.out.println(rst);
        return "error";
    }

    @RequestMapping("/")
    public String index() {
        System.out.println(bookService.getAllBooks());
        return "index";
    }
}
