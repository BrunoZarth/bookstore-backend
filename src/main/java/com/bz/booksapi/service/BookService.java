package com.bz.booksapi.service;

import com.bz.booksapi.exception.BadRequestException;
import com.bz.booksapi.model.Book;
import com.bz.booksapi.repository.BookRepository;
import com.bz.booksapi.requests.BookPostRequestBody;
import com.bz.booksapi.requests.BookPutRequestBody;
import com.bz.booksapi.util.RandomString;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class BookService {

    BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository br){
        this.bookRepository = br;
    }

    public Page<Book> bookList(Pageable pageable){
        return this.bookRepository.findAll(pageable);
    }

    public List<Book> findByName(String name){
        return this.bookRepository.findByName(name);
    }

    public List<Book> findByCategory(int category){
        return this.bookRepository.findByCategory(category);
    }

    public List<Book> findByPrice(int price){
        return this.bookRepository.findByPrice(price);
    }

    public Book findByIdOrThrowBadRequestException(String id){
        return bookRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Book ID não encontrado!"));
    }

    public Book addBook(BookPostRequestBody bookPostRequestBody){
        if(!this.findByName(bookPostRequestBody.getName()).isEmpty()){
            throw new BadRequestException("O livro já está no banco de dados.");
        }
        Book book = Book.builder()
                .id(RandomString.getAlphaNumericString(10))
                .category(bookPostRequestBody.getCategory())
                .img(bookPostRequestBody.getImg())
                .name(bookPostRequestBody.getName())
                .price(bookPostRequestBody.getPrice())
                .quantity(bookPostRequestBody.getQuantity())
                .build();

        bookRepository.save(book);
        this.findByIdOrThrowBadRequestException(book.getId());
        return book;
    }

    public Book updateBook(BookPutRequestBody bookPutRequestBody){
        this.findByIdOrThrowBadRequestException(bookPutRequestBody.getId());
        Book book = Book.builder()
                .id(bookPutRequestBody.getId())
                .category(bookPutRequestBody.getCategory())
                .img(bookPutRequestBody.getImg())
                .name(bookPutRequestBody.getName())
                .price(bookPutRequestBody.getPrice())
                .quantity(bookPutRequestBody.getQuantity())
                .build();

        bookRepository.save(book);
        return book;
    }

    public void deleteBookById(String id){
        this.findByIdOrThrowBadRequestException(id);
        bookRepository.deleteById(id);
    }




}
