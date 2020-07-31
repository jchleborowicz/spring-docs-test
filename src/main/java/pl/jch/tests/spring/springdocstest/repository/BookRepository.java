package pl.jch.tests.spring.springdocstest.repository;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.jch.tests.spring.springdocstest.model.Book;

public interface BookRepository {

    Optional<Book> findById(long id);

    void add(Book book);

    Collection<Book> getBooks();

    Page<Book> getBooks(Pageable pageable);
}
