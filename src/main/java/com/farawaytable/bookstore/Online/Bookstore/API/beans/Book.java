package com.farawaytable.bookstore.Online.Bookstore.API.beans;

import jakarta.persistence.*;
import lombok.Data;

@Data // This annotation generates the Getters & Setters for us
@Entity
@Table(name = "books")
public class Book
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(length = 1000)
    private String description;

    @ManyToOne()
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    @ManyToOne()
    @JoinColumn(name = "genre_id", nullable = false)
    private Genre genre;
}
