package com.bz.booksapi.requests;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;

import javax.persistence.Column;
@Data
public class BookPostRequestBody {

    //@NotEmpty(message = "Book id cant be empty")
    //@NotNull(message = "Book id cant be null")
    //private String id;

    @NotEmpty(message = "Book name cant be empty")
    @NotNull(message = "Book name cant be null")
    private String name;

    private int price;

    private int quantity;

    private int category;

    private String img;
}
