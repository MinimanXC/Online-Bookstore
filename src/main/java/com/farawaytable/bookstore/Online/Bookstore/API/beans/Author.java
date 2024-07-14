package com.farawaytable.bookstore.Online.Bookstore.API.beans;

import jakarta.persistence.*;
import lombok.Data;

@Data // This annotation generates the Getters & Setters for us
@Entity
@Table(name = "authors")
public class Author
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(length = 1000)
    private String bio;
}
