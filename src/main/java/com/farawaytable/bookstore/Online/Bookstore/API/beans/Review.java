package com.farawaytable.bookstore.Online.Bookstore.API.beans;

import jakarta.persistence.*;
import lombok.Data;

@Data // This annotation generates the Getters & Setters for us
@Entity
@Table(name = "reviews")
public class Review
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @ManyToOne()
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private int rating;

    @Column(length = 1000)
    private String comment;
}
