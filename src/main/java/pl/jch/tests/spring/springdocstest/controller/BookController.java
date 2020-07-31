package pl.jch.tests.spring.springdocstest.controller;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.jch.tests.spring.springdocstest.exception.BookNotFoundException;
import pl.jch.tests.spring.springdocstest.model.Book;
import pl.jch.tests.spring.springdocstest.repository.BookRepository;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/")
    public Collection<Book> getBooks() {
        return this.bookRepository.getBooks();
    }

    @GetMapping("/{id}")
    public Book findById(@PathVariable long id) {
        return this.bookRepository.findById(id)
                .orElseThrow(BookNotFoundException::new);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Book updateBook(@PathVariable String id, @RequestBody Book book) {
        this.bookRepository.add(book);
        return book;
    }

    @GetMapping("/filter")
    public Page<Book> filterBooks(Pageable pageable) {
        return this.bookRepository.getBooks(pageable);
    }
}
