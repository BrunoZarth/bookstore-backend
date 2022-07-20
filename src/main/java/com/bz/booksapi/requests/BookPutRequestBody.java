package com.bz.booksapi.requests;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class BookPutRequestBody {

    @NotEmpty(message = "Book id cant be empty")
    @NotNull(message = "Book id cant be null")
    private String id;

    @NotEmpty(message = "Book name cant be empty")
    @NotNull(message = "Book name cant be null")
    private String name;

    private int price;

    private int quantity;

    private int category;

    private String img;
}
