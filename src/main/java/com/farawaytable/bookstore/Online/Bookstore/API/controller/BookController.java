package com.farawaytable.bookstore.Online.Bookstore.API.controller;

import com.farawaytable.bookstore.Online.Bookstore.API.beans.*;
import com.farawaytable.bookstore.Online.Bookstore.API.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController
{
    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity<Page<Book>> getAllBooks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection) {

        Page<Book> books = bookService.getAllBooks(page, size, sortBy, sortDirection);
        return ResponseEntity.ok(books);
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable("id") Long id) { return bookService.getBookById(id); }

    @PostMapping
    public Book addBook(@RequestBody Book book) { return bookService.addBook(book); }

    @PutMapping("/{id}")
    public Book updateBook(@RequestBody Book book, @PathVariable("id") Long id) { return bookService.updateBook(book, id); }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable("id") Long id) { bookService.deleteBook(id); }

    @PostMapping("/{bookId}/reviews")
    public Review addReview(@PathVariable("bookId") Long bookId, @RequestBody Review review) { return bookService.addReview(bookId, review); }

    @GetMapping("/{bookId}/reviews")
    public ResponseEntity<Page<Review>> getReviewsForBook(@RequestParam Long bookId,
                                          @RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "10") int size,
                                          @RequestParam(defaultValue = "id") String sortBy,
                                          @RequestParam(defaultValue = "asc") String sortDirection) {
        Page<Review> reviews = bookService.getReviewsForBook(bookId, page, size, sortBy, sortDirection);
        return ResponseEntity.ok(reviews);
    }

    //TODO: Search function
//    @GetMapping("/search")
//    public ResponseEntity<Page<Book>> searchBooks(){}
}
