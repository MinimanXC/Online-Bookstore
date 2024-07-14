package com.farawaytable.bookstore.Online.Bookstore.API.service;

import com.farawaytable.bookstore.Online.Bookstore.API.beans.*;
import com.farawaytable.bookstore.Online.Bookstore.API.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class AuthorService
{
    @Autowired
    private AuthorRepository authorRepository;

    public Author addAuthor(Author author) { return authorRepository.save(author); }

    public Page<Author> getAllAuthors(int page, int size, String sortBy, String sortDirection) {
        Sort.Direction direction = sortDirection.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort sort = Sort.by(direction, sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        return authorRepository.findAll(pageable);
    }

    public Author getAuthorById(Long id) { return authorRepository.findById(id).orElse(null); }

    public Author updateAuthor(Author author, Long id)
    {
        return authorRepository.findById(id).map(at -> {
            at.setName(author.getName());
            at.setBio(author.getBio());
            return authorRepository.save(at);
        }).orElse(null);
    }

    public void deleteAuthor(Long id)
    {
        if (!authorRepository.existsById(id)) { return; }
        authorRepository.deleteById(id);
    }
}
