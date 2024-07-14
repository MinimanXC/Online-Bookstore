package com.farawaytable.bookstore.Online.Bookstore.API.beans;

import jakarta.persistence.*;
import lombok.Data;

@Data // This annotation generates the Getters & Setters for us
@Entity
@Table(name = "genres")
public class Genre
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;
}
