package com.farawaytable.bookstore.Online.Bookstore.API.controller;

import com.farawaytable.bookstore.Online.Bookstore.API.beans.*;
import com.farawaytable.bookstore.Online.Bookstore.API.repository.GenreRepository;
import com.farawaytable.bookstore.Online.Bookstore.API.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/genre")
public class GenreController 
{
    // Example on how to utilise Generic Service in a Controller
//    @Autowired
//    private GenericService genericService;
//    @Autowired
//    private GenreRepository genreRepository;
//
//    @PostMapping
//    public Genre createBook(@RequestBody Genre genre) {
//        return genericService.create(genre, genreRepository);
//    }

    @Autowired
    private GenreService genreService;

    @GetMapping
    public ResponseEntity<Page<Genre>> getAllGenres(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection){

        Page<Genre> Genres = genreService.getAllGenres(page, size, sortBy, sortDirection);
        return ResponseEntity.ok(Genres);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Genre> getGenreById(@PathVariable Long id)
    {
        return ResponseEntity.ok(genreService.getGenreById(id));
    }

    @PostMapping
    public Genre addGenre(@RequestBody Genre Genre)
    {
        return genreService.addGenre(Genre);
    }

    @PutMapping("/{id}")
    public Genre updateGenre(@RequestBody Genre Genre, @PathVariable Long id)
    {
        return genreService.updateGenre(Genre, id);
    }

    @DeleteMapping("/{id}")
    public void deleteGenre(@PathVariable Long id)
    {
        genreService.deleteGenre(id);
    }
}
