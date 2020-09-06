package pl.jch.tests.spring.springdocstest.repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import pl.jch.tests.spring.springdocstest.model.Book;

import static java.util.stream.Collectors.toList;

@Repository
public class BookRepositoryImpl implements BookRepository {

    private final Map<Long, Book> books = new HashMap<>();

    @Override
    public Optional<Book> findById(long id) {
        return Optional.ofNullable(this.books.get(id));
    }

    @Override
    public void add(Book book) {
        this.books.put(book.getId(), book);
    }

    @Override
    public Collection<Book> getBooks() {
        return this.books.values();
    }

    @Override
    public Page<Book> getBooks(Pageable pageable) {
        final int toSkip = pageable.getPageSize() * pageable.getPageNumber();
        final List<Book> result = this.books.values()
                .stream()
                .skip(toSkip)
                .limit(pageable.getPageSize())
                .collect(toList());

        return new PageImpl<>(result, pageable, books.size());
    }
}
