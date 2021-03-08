package se.iths.springbootlab2.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Date;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BooksControllerTest {

    @Test
    void callingOneWithValidISBN13ReturnsOneBook() {

        BooksController booksController = new BooksController(new TestService());
        var book = booksController.one("1234567891234");

        assertThat(book.getISBN13()).isEqualTo("1234567891234");
        assertThat(book.getTitel()).isEqualTo("TestTitel");
        assertThat(book.getLanguage()).isEqualTo("TestSpråk");
        assertThat(book.getPris()).isEqualTo(0.0);
        assertThat(book.getUtgivningsdatum()).isEqualTo(Date.valueOf("2020-01-01"));



    }


    @Test
    void callingOneWithInvalidISBN13ThrowsExceptionWithResponseStatus404() {

        BooksController booksController = new BooksController(new TestService());

        var exception = assertThrows(ResponseStatusException.class, () -> booksController.one("1"));
        assertThat(exception.getStatus()).isEqualTo(HttpStatus.NOT_FOUND);
        System.out.println(exception);

    }

}