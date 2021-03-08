package se.iths.springbootlab2.services;




import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import se.iths.springbootlab2.dtos.BooksDto;
import se.iths.springbootlab2.dtos.TitelDto;
import se.iths.springbootlab2.entities.*;
import se.iths.springbootlab2.mappers.BooksMapper;
import se.iths.springbootlab2.repositories.BooksRepository;



import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class BooksService implements se.iths.springbootlab2.services.Service {



    private final BooksMapper booksMapper;


    private BooksRepository booksRepository;

    public BooksService(BooksRepository booksRepository, BooksMapper booksMapper) {
        this.booksMapper = booksMapper;
        this.booksRepository = booksRepository;
    }

    @Override
    public List<BooksDto> getAllBooks() {
        return booksMapper.mapp(booksRepository.findAll());
    }

    @Override
    public Optional<BooksDto> getOne(String ISBN13) {
        return booksMapper.mapp(booksRepository.findById(ISBN13));
    }

    @Override
    public BooksDto createBook(BooksDto book) {
        if (book.getTitel().isEmpty())
            throw new RuntimeException();

        return booksMapper.mapp(booksRepository.save(booksMapper.mapp(book)));
    }


    @Override
    public void delete(String ISBN13) {
        booksRepository.deleteById(ISBN13);
    }

    @Override
    public BooksDto replace(String ISBN13, BooksDto booksDto) {

        Optional<Books> books = booksRepository.findById(ISBN13);
        if (books.isPresent()) {
            Books updatedBook = books.get();

            updatedBook.setTitel(booksDto.getTitel());
            updatedBook.setLanguage(booksDto.getLanguage());
            updatedBook.setPris(booksDto.getPris());
            updatedBook.setUtgivningsdatum(booksDto.getUtgivningsdatum());

            return booksMapper.mapp(booksRepository.save(updatedBook));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "ISBN13" + ISBN13 + " not found. ");

        }
    }


    @Override
    public BooksDto update(String ISBN13, TitelDto titelDto) {

        Optional<Books> books = booksRepository.findById(ISBN13);
        if (books.isPresent()) {
            Books updateBook = books.get();

            if (titelDto.titel != null) updateBook.setTitel(titelDto.titel);

            return booksMapper.mapp(booksRepository.save(updateBook));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "ISBN13" + ISBN13 + " not found. ");

        }


    }

    @Override
    public List<BooksDto> searchForColumn(String searchTerm) {

        return booksMapper.mapp(booksRepository.findAllByISBN13OrTitelOrLanguage(searchTerm, searchTerm, searchTerm));
    }



    @Override
    public List<BooksDto> getFoos(String isbn13, String titel, String språk, Double pris, String författare,Date utgivningsdatum) {

        List<Books> books = booksRepository.findAll();
        List<Books> dynamicSearch = new ArrayList<>();
        for (Books book : books
        ) {
            if (book.getISBN13() != null && book.getISBN13().equalsIgnoreCase(isbn13))
                dynamicSearch.add(book);
            else if (book.getTitel() != null && book.getTitel().equalsIgnoreCase(titel))
                dynamicSearch.add(book);
            else if (book.getLanguage() != null && book.getLanguage().equalsIgnoreCase(språk))
                dynamicSearch.add(book);
            else if (book.getPris() != null && book.getPris().equals(pris))
                dynamicSearch.add(book);
            else if (book.getFörfattare() != null && (book.getFörfattare().getFörnamn() + " " + book.getFörfattare().getEfternamn()).equalsIgnoreCase(författare))
                dynamicSearch.add(book);
            else  if (book.getUtgivningsdatum()!= null && book.getUtgivningsdatum().equals(utgivningsdatum))
            dynamicSearch.add(book);
        }
        return booksMapper.mapp(dynamicSearch);
    }



    @Override
    public ResponseEntity<List<BooksDto>> searchBooks(Specification<BooksDto> specification) {
        return new ResponseEntity(booksRepository.findAll(Specification.where(specification)), HttpStatus.OK);
    }

}




