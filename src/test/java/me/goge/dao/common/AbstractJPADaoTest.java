package me.goge.dao.common;

import common.AbstractContextControllerTests;
import me.goge.model.Book;
import me.goge.service.BookService;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @Author joway
 * @Email joway.w@gmail.com
 * @Date 16/2/23.
 */
@SuppressWarnings("unchecked")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)
public class AbstractJPADaoTest extends AbstractContextControllerTests {

    @Resource
    BookService bookService;

    public static Book temp_book;
    public static int temp_id;

    @Before
    public void setup() throws Exception {
        Book book = new Book();
        book.setPrice(100);
        book.setName("dao");
        book.setAuthor("joway");
        temp_book = book;
    }


    @Test
    public void testA_Insert() throws Exception {
        bookService.addBook(temp_book);
    }

    @Test
    public void testB_Update() throws Exception {
        List<Book> list = bookService.getAllBooks();
        Book bok = list.get(list.size() - 1);
        temp_id = bok.getId();
        temp_book.setId(bok.getId());

        String tmp = temp_book.getName();
        temp_book.setAuthor("update");
        bookService.updateBook(temp_book);
        assertEquals(bookService.getBookById(temp_book.getId()).getAuthor(), "update");
        temp_book.setAuthor(tmp);
    }

    @Test
    public void testC_DeleteById() throws Exception {
        bookService.deleteBook(temp_id);
    }

    @Test
    public void testD_SearchById() throws Exception {
        assertEquals(bookService.getBookById(1).getName(), "dao");
    }



    @Test
    public void testE_GetCount() throws Exception {
        assertEquals(bookService.getBookCount(), 1);
    }


}