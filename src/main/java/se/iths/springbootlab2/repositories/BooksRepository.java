package se.iths.springbootlab2.repositories;


import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import se.iths.springbootlab2.entities.Books;


import java.util.List;

@Repository
public interface BooksRepository extends JpaRepository<Books, String> {

    List<Books> findAllByISBN13OrTitelOrLanguage(String ISBN13, String titel, String language);


    List<Books> findAll(Specification spec);


}
