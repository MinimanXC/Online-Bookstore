package com.farawaytable.bookstore.Online.Bookstore.API.repository;

import com.farawaytable.bookstore.Online.Bookstore.API.beans.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AuthorRepository extends JpaRepository<Author, Long>
{
    // Override default findAll with JPA built-in pagination and sorting
    Page<Author> findAll(Pageable pageable);
}
