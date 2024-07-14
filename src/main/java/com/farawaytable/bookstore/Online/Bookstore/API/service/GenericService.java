package com.farawaytable.bookstore.Online.Bookstore.API.service;

import com.farawaytable.bookstore.Online.Bookstore.API.beans.*;
import com.farawaytable.bookstore.Online.Bookstore.API.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.io.Serializable;

// CURRENTLY NOT IN USE!
// Experimental Generic Service class to replace the multiple service classes with similar CRUD implementations
// See CRUDs of Books, Authors, Genres in Functional Requirements
// At most have BookService extend this class to add Search & Review business logic
// Not an advisible implmentation in real world context but optimal for current scope
// See GenreController for example implementation
@Service
public class GenericService
{
    // Create / POST
    public <T, ID extends Serializable> T create(T entity, JpaRepository<T, ID> repository) {
        return repository.save(entity);
    }

    // Read All / GET All
    public <T, ID extends Serializable> Page<T> getAll(int page, int size, String sortBy, String sortDirection, JpaRepository<T, ID> repository) {
        Sort.Direction direction = sortDirection.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort sort = Sort.by(direction, sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        return repository.findAll(pageable);
    }

    // Read / GET
    public <T, ID extends Serializable> T getById(ID id, JpaRepository<T, ID> repository) {
        return repository.findById(id).orElse(null);
    }

    // Update / PUT
    public <T, ID extends Serializable> T update(T entity, ID id, JpaRepository<T, ID> repository) {
        if (repository.existsById(id)) {
            return repository.save(entity);
        }
        return null;
    }

    // Delete / DELETE
    public <T, ID extends Serializable> void delete(ID id, JpaRepository<T, ID> repository) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        }
    }
}
