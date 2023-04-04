package com.example.bookmanagement.controller;

import com.example.bookmanagement.entity.Book;
import com.example.bookmanagement.mapper.BookMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;

import java.util.List;

@RestController
@Tag(name = "图书管理",description = "图书管理API")
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookMapper bookMapper;

    @Operation(summary = "查询所有图书",description = "成功返回含有全部书籍的list")
    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Book> getAllBooks() {
        List<Book> books = bookMapper.selectList(null);
        return books;
    }
    @Operation(summary = "根据ID查询单个图书",description = "成功返回状态码200和对应书本信息")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Book getBookById(@PathVariable("id") Long id) {
        Book book = bookMapper.selectById(id);
        return book;
    }
    @Operation(summary = "添加图书",description = "创建成功201请求有误400")
    @PostMapping("")
    @ResponseStatus(HttpStatus.OK)
    public Book createBook(@RequestBody Book book) {
        bookMapper.insert(book);
        return book;
    }
    @Operation(summary = "更新图书",description = "成功200记录不存在404")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Book> updateBook(@PathVariable("id") Long id, @RequestBody Book book) {
        Book book1 = bookMapper.selectById(id);
        if (book1 != null) {
            book1.setTitle(book.getTitle());
            book1.setAuthor(book.getAuthor());
            book1.setPrice(book.getPrice());
            bookMapper.updateById(book1);
            return ResponseEntity.ok(book1);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @Operation(summary = "删除图书",description = "成功200记录不存在404")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteBook(@PathVariable("id") Long id) {
        if (bookMapper.selectById(id) != null) {
            bookMapper.deleteById(id);
        } else {
            throw new NotFoundException("Book not found");
        }
    }


}
