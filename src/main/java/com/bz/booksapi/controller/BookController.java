package com.bz.booksapi.controller;

import com.bz.booksapi.model.Book;
import com.bz.booksapi.requests.BookPostRequestBody;
import com.bz.booksapi.requests.BookPutRequestBody;
import com.bz.booksapi.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/book")
public class BookController {

    BookService bookService;

    @Autowired
    public BookController(BookService bs){
        this.bookService = bs;
    }

    @GetMapping
    public ResponseEntity<Page<Book>> bookList(Pageable page){
        return ResponseEntity.ok(bookService.bookList(page));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Book>> findByName(@PathVariable String name){
        return ResponseEntity.ok(bookService.findByName(name));
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Book>> findByCategory(@PathVariable int category){
        return ResponseEntity.ok(bookService.findByCategory(category));
    }

    @GetMapping("/price/{price}")
    public ResponseEntity<List<Book>> findByPrice(@PathVariable int price){
        return ResponseEntity.ok(bookService.findByPrice(price));
    }

    @PostMapping("/addBook")
    @ResponseStatus(HttpStatus.CREATED)
    private ResponseEntity<Book> addBook(@RequestBody @Valid BookPostRequestBody bookPostRequestBody){
        return new ResponseEntity<>(bookService.addBook(bookPostRequestBody), HttpStatus.CREATED);
    }

    @PutMapping("/updateBook")
    @ResponseStatus(HttpStatus.CREATED)
    private ResponseEntity<Book> updateBook(@RequestBody @Valid BookPutRequestBody bookPutRequestBody){
        return new ResponseEntity<>(bookService.updateBook(bookPutRequestBody), HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteById/{id}")
    private ResponseEntity<Void> deleteById(@PathVariable String id){
        bookService.deleteBookById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
