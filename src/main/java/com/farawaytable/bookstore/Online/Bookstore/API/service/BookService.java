package com.farawaytable.bookstore.Online.Bookstore.API.service;

import com.farawaytable.bookstore.Online.Bookstore.API.beans.*;
import com.farawaytable.bookstore.Online.Bookstore.API.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

//import java.util.List;

@Service
public class BookService
{
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    public Book addBook(Book book)
    {
        return bookRepository.save(book);
    }

    // Default implementation without pagination and sorting requirements
    //public List<Book> getAllBooks() { return bookRepository.findAll(); }

    public Page<Book> getAllBooks(int page, int size, String sortBy, String sortDirection) {
        Sort.Direction direction = sortDirection.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort sort = Sort.by(direction, sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        return bookRepository.findAll(pageable);
    }

    public Page<Book> searchBooks(String searchTerm, String searchBy, int page, int size, String sortBy, String sortDirection) {
        Sort.Direction direction = sortDirection.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort sort = Sort.by(direction, sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);

        switch (searchBy.toLowerCase()) {
            case "title":
                return bookRepository.findByTitleContainingIgnoreCase(searchTerm, pageable);
            case "author":
                return bookRepository.findByAuthorContainingIgnoreCase(searchTerm, pageable);
            case "genre":
                return bookRepository.findByGenreContainingIgnoreCase(searchTerm, pageable);
            default:
                return Page.empty();
        }
    }

    public Book getBookById(Long id)
    {
        return bookRepository.findById(id).orElse(null);
    }

    public Book updateBook(Book book, Long id)
    {
        return bookRepository.findById(id).map(bk -> {
            bk.setTitle(book.getTitle());
            bk.setDescription(book.getDescription());
            bk.setAuthor(book.getAuthor());
            bk.setGenre(book.getGenre());
            return bookRepository.save(bk);
        }).orElse(null);
    }

    public void deleteBook(Long id)
    {
        if (!bookRepository.existsById(id)) return;

        bookRepository.deleteById(id);
    }

    public Review addReview(Long bookId, Review review)
    {
        Book book = getBookById(bookId);
        if (book != null) {
            review.setBook(book);
            return reviewRepository.save(review);
        }
        return null;
    }

    public Page<Review> getReviewsForBook(Long bookId, int page, int size, String sortBy, String sortDirection)
    {
        Sort.Direction direction = sortDirection.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort sort = Sort.by(direction, sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);

        return reviewRepository.findByBookId(bookId, pageable);
    }
}
