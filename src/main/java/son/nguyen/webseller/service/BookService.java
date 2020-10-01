package son.nguyen.webseller.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import son.nguyen.webseller.model.Book;

import java.util.List;

public interface BookService {

    Book save(Book book);

    void delete(Book book);

    Book findOne(String id);

    Iterable<Book> findAll();

    Page<Book> findByAuthor(String author, Pageable pageable);

    List<Book> findByTitle(String title);

}
