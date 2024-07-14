package com.farawaytable.bookstore.Online.Bookstore.API.beans;

import jakarta.persistence.*;
import lombok.Data;

@Data // This annotation generates the Getters & Setters for us
@Entity
@Table(name = "users")
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ Column(unique = true, nullable = false)
    private String username;

    @ Column(unique = true, nullable = false)
    private String email;

    @ Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    public enum Role {
        USER, ADMIN
    }
}
