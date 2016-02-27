package me.goge.dao.common.redis;

import common.RedisConfiguration;
import me.goge.model.Book;
import me.goge.service.redis.BookRedisService;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.assertEquals;

/**
 * @Author joway
 * @Email joway.w@gmail.com
 * @Date 16/2/24.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)
public class AbstractRedisDaoTest extends RedisConfiguration {

    @Resource
    private BookRedisService bookRedisService;

    private String KEY = "list";

    public static Book temp_book;


    @Before
    public void setup() throws Exception {
        Book book = new Book();
        book.setPrice(100);
        book.setName("dao");
        book.setAuthor("joway");
        temp_book = book;
    }

    @Test
    public void testA_push() throws Exception {
        bookRedisService.list_push(KEY, temp_book);
        assertEquals( bookRedisService.list_length(KEY), (Long) 1L);
    }

    @Test
    public void testB_pop() throws Exception {
        bookRedisService.list_pop(KEY);
        assertEquals( bookRedisService.list_length(KEY), (Long) 0L);
    }

    @Test
    public void testC_in() throws Exception {
        bookRedisService.list_inQueue(KEY, temp_book);
        assertEquals( bookRedisService.list_length(KEY), (Long) 1L);
    }

    @Test
    public void testD_out() throws Exception {
        bookRedisService.list_outQueue(KEY);
        assertEquals( bookRedisService.list_length(KEY), (Long) 0L);
    }
}