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
public class GenreService
{
    @Autowired
    private GenreRepository genreRepository;

    public Genre addGenre(Genre genre)
    {
        if (genreAlreadyExists(genre)) { return null; }
        return genreRepository.save(genre);
    }

    public Page<Genre> getAllGenres(int page, int size, String sortBy, String sortDirection) {
        Sort.Direction direction = sortDirection.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort sort = Sort.by(direction, sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        return genreRepository.findAll(pageable);
    }

    public Genre getGenreById(Long id) { return genreRepository.findById(id).orElse(null); }

    public Genre updateGenre(Genre genre, Long id)
    {
        return genreRepository.findById(id).map(gr -> {
            gr.setName(gr.getName());
            return genreRepository.save(gr);
        }).orElse(null);
    }

    public void deleteGenre(Long id)
    {
        if (!genreRepository.existsById(id)) { return; }
        genreRepository.deleteById(id);
    }

    private boolean genreAlreadyExists(Genre genre)
    {
        return genreRepository.findByName(genre.getName()) != null;
    }
}
