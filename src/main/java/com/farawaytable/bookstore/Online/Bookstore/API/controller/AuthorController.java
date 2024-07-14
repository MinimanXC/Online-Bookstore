package com.farawaytable.bookstore.Online.Bookstore.API.controller;

import com.farawaytable.bookstore.Online.Bookstore.API.beans.*;
import com.farawaytable.bookstore.Online.Bookstore.API.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/author")
public class AuthorController
{
    @Autowired
    private AuthorService authorService;

    @GetMapping
    public ResponseEntity<Page<Author>> getAllAuthors(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection){

        Page<Author> authors = authorService.getAllAuthors(page, size, sortBy, sortDirection);
        return ResponseEntity.ok(authors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable Long id)
    {
            return ResponseEntity.ok(authorService.getAuthorById(id));
    }

    @PostMapping
    public Author addAuthor(@RequestBody Author author)
    {
        return authorService.addAuthor(author);
    }

    @PutMapping("/{id}")
    public Author updateAuthor(@RequestBody Author author, @PathVariable Long id)
    {
        return authorService.updateAuthor(author, id);
    }
    
    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable Long id)
    {
        authorService.deleteAuthor(id);
    }
}
