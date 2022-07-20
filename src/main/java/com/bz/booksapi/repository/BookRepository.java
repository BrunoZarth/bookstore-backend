package com.bz.booksapi.repository;

import com.bz.booksapi.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {
    List<Book> findByName(String name);
    List<Book> findByCategory(int category);
    List<Book> findByPrice(int price);

    //Page<Book> findAll(Pageable pageable);
}
