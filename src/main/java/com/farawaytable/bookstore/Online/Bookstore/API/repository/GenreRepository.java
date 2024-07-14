package com.farawaytable.bookstore.Online.Bookstore.API.repository;

import com.farawaytable.bookstore.Online.Bookstore.API.beans.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GenreRepository extends JpaRepository<Genre, Long>
{
    // Override default findAll with JPA built-in pagination and sorting
    Page<Genre> findAll(Pageable pageable);
    Genre findByName(String name);
}
