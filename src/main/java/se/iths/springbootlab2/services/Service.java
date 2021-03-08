package se.iths.springbootlab2.services;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import se.iths.springbootlab2.dtos.BooksDto;
import se.iths.springbootlab2.dtos.TitelDto;


import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface Service {


    List<BooksDto> getAllBooks();

    Optional<BooksDto> getOne(String ISBN13);

    void delete(String ISBN13);

    BooksDto replace(String ISBN13, BooksDto booksDto);

    BooksDto update(String ISBN13, TitelDto titelDto);

    BooksDto createBook(BooksDto book);

    List<BooksDto> searchForColumn(String searchTerm);



    List<BooksDto> getFoos(String isbn13, String titel, String language, Double pris, String författare, Date utgivningsdatum);

    ResponseEntity<List<BooksDto>> searchBooks(Specification<BooksDto> specification);


}
