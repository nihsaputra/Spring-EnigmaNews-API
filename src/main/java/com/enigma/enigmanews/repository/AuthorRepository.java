package com.enigma.enigmanews.repository;

import com.enigma.enigmanews.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, String> {
    Optional<Author> findByUserCredential_Id(String id);
}
