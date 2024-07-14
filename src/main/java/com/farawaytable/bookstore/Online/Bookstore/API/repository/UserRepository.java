package com.farawaytable.bookstore.Online.Bookstore.API.repository;

import com.farawaytable.bookstore.Online.Bookstore.API.beans.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>
{
    Optional<User> findByUsername(String username);
    User findByEmail(String email);
}
