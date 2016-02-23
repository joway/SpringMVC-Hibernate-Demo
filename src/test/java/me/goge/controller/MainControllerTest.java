package me.goge.controller;


import common.AbstractContextControllerTests;
import me.goge.model.Book;
import me.goge.service.BookService;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.Resource;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * @Author joway
 * @Email joway.w@gmail.com
 * @Date 16/2/22.
 */
//@FixMethodOrder(MethodSorters.NAME_ASCENDING) 注解能使得按方法名字典序来决定执行顺序
// RunWith 要在最后执行, 紧靠类

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)
public class MainControllerTest extends AbstractContextControllerTests {

    private MockMvc mockMvc;

    public static int temp_id = 0;

    @Resource
    private BookService bookService;

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(this.wac).alwaysExpect(status().isOk()).build();
    }


    @Test
    public void testA_Get() throws Exception {
        this.mockMvc.perform(get("/get").param("id", "1"))
                .andExpect(content().string("{\"id\":1,\"name\":\"dao\",\"price\":100.0,\"author\":\"joway\"}"));
    }

    @Test
    public void testB_GetAll() throws Exception {
        this.mockMvc.perform(get("/getAll"))
                .andExpect(content().string("[{\"id\":1,\"name\":\"dao\",\"price\":100.0,\"author\":\"joway\"}]"));
    }

    @Test
    public void testC_Add() throws Exception {
        this.mockMvc.perform(get("/add"))
                .andExpect(content().string("add"));
        List<Book> list = bookService.getAllBooks();
        System.out.println(list.get(0).getName());
        assertTrue(list.size() >= 2);
        Book bok = list.get(list.size() - 1);
        assertEquals(bok.getName(), "bok");
        temp_id = bok.getId();
        System.out.println(temp_id);
    }


    @Test
    public void testD_Delete() throws Exception {
        this.mockMvc.perform(get("/delete").param("id", String.valueOf(temp_id)))
                .andExpect(content().string("delete"));
        List<Book> list = bookService.getAllBooks();
        assertTrue(list.size() == 1);
    }

    @Test
    public void testE_DeleteCache() throws Exception {
        this.mockMvc.perform(get("/deleteCache"))
                .andExpect(content().string("delete all cache"));
    }


    @Test
    public void testF_Valid() throws Exception {
        this.mockMvc.perform(get("/valid").param("price","5"))
                .andExpect(content().string("error"));
        this.mockMvc.perform(get("/valid").param("price","15"))
                .andExpect(content().string("ok"));
    }
}