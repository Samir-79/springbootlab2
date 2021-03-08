package se.iths.springbootlab2.mappers;

import org.springframework.stereotype.Component;
import se.iths.springbootlab2.dtos.BooksDto;
import se.iths.springbootlab2.entities.Books;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class BooksMapper {



    public BooksMapper() {

    }

    public BooksDto mapp(Books books) {

        return new BooksDto(books.getISBN13(), books.getTitel(), books.getLanguage(),
                books.getPris(), books.getUtgivningsdatum(),books.getFörfattare());

    }

    public Books mapp(BooksDto booksDto) {
        return new Books(booksDto.getISBN13(), booksDto.getTitel(), booksDto.getLanguage(),
                booksDto.getPris(), booksDto.getUtgivningsdatum(),booksDto.getFörfattare());
    }

    public Optional<BooksDto> mapp(Optional<Books> optionalBooks) {
        if (optionalBooks.isEmpty())
            return Optional.empty();
        return Optional.of(mapp(optionalBooks.get()));
    }


    public List<BooksDto> mapp(List<Books> all) {
        return all
                .stream()
                .map(this::mapp)
                .collect(Collectors.toList());
    }

}
