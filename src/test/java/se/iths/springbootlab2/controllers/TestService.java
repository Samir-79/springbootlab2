package se.iths.springbootlab2.controllers;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import se.iths.springbootlab2.dtos.BooksDto;
import se.iths.springbootlab2.dtos.TitelDto;
import se.iths.springbootlab2.entities.Författare;
import se.iths.springbootlab2.services.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public class TestService implements Service {

    Författare writer;




    @Override
    public List<BooksDto> getAllBooks() {
        return List.of(new BooksDto("1234567891235", "TestTitel1", "TestSpråk1", 0.0, Date.valueOf("2021.01.01"),writer),
                new BooksDto("1234567894567", "TestTitel2", "TestSpråk", 0.0, Date.valueOf("2021.01.12"),writer));
    }

    @Override
    public Optional<BooksDto> getOne(String ISBN13) {
        if (ISBN13.equals("1234567891234"))
            return Optional.of(new BooksDto(("1234567891234"), "TestTitel", "TestSpråk", 0.0, Date.valueOf("2020-01-01"),writer));

        return Optional.empty();
    }

    @Override
    public void delete(String ISBN13) {

    }

    @Override
    public BooksDto replace(String ISBN13, BooksDto booksDto) {
        return null;
    }

    @Override
    public BooksDto update(String ISBN13, TitelDto titelDto) {
        return null;
    }

    @Override
    public BooksDto createBook(BooksDto book) {
        return null;
    }

    @Override
    public List<BooksDto> searchForColumn(String searchTerm) {
        return null;
    }



    @Override
    public List<BooksDto> getFoos(String isbn13, String titel, String språk, Double pris,String författare,Date utgivningsdatum) {
        return null;
    }

    @Override
    public ResponseEntity<List<BooksDto>> searchBooks(Specification<BooksDto> specification) {
        return null;
    }
}
