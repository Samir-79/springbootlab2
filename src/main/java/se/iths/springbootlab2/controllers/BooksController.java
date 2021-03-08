package se.iths.springbootlab2.controllers;


import com.sipios.springsearch.anotation.SearchSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import se.iths.springbootlab2.dtos.BooksDto;
import se.iths.springbootlab2.dtos.TitelDto;
import se.iths.springbootlab2.services.Service;

import java.sql.Date;
import java.util.List;

@RestController
public class BooksController {

    private Service service;

    @Autowired
    public BooksController(Service service) {
        this.service = service;
    }

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello World";
    }

    @GetMapping("/Böcker")
    public List<BooksDto> all() {
        return service.getAllBooks();
    }

    @GetMapping("/Böcker/{ISBN13}")
    public BooksDto one(@PathVariable String ISBN13) {
        return service.getOne(ISBN13)

                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "ISBN13" + ISBN13 + " not found."));

    }

    @PostMapping("/Böcker")
    @ResponseStatus(HttpStatus.CREATED)
    public BooksDto create(@RequestBody BooksDto book) {
        return service.createBook(book);
    }

    @DeleteMapping("/Böcker/{ISBN13}")
    public void delete(@PathVariable String ISBN13) {
        service.delete(ISBN13);
    }

    @PutMapping("/Böcker/{ISBN13}")
    public BooksDto replace(@RequestBody BooksDto booksDto, @PathVariable String ISBN13) {
        return service.replace(ISBN13, booksDto);

    }

    @PatchMapping("/Böcker/{ISBN13}")
    public BooksDto update(@RequestBody TitelDto titelDto, @PathVariable String ISBN13) {
        return service.update(ISBN13, titelDto);
    }

    @GetMapping("/Böcker/searchColumn/{search}")
    public List<BooksDto> searchforColumn(@PathVariable String search) {
        return service.searchForColumn(search);
    }


    @GetMapping(value = "Böcker/foos")
    public List<BooksDto> addFoo(

            @RequestParam(name="isbn13",required = false)String isbn13 , @RequestParam(name = "titel",required = false) String titel,
            @RequestParam(name = "language",required = false) String språk, @RequestParam(name = "pris",required = false) Double pris,
            @RequestParam(name = "writer",required = false) String författare,
            @RequestParam(name = "utgivningsdatum",required = false) Date utgivningsdatum) {
        return service.getFoos(isbn13, titel, språk, pris, författare, utgivningsdatum);
    }

    @GetMapping("/Böcker/value")
    public ResponseEntity<List<BooksDto>> searchBooks(@SearchSpec Specification<BooksDto> specification) {

        return service.searchBooks(specification);



    }


}
