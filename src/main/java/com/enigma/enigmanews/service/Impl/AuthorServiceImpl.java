package com.enigma.enigmanews.service.Impl;

import com.enigma.enigmanews.entity.Author;
import com.enigma.enigmanews.entity.UserCredential;
import com.enigma.enigmanews.model.response.AuthResponse;
import com.enigma.enigmanews.repository.AuthorRepository;
import com.enigma.enigmanews.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void create(UserCredential request) {
        Author buildAuthor = Author.builder()
                .userCredential(request)
                .build();
        authorRepository.save(buildAuthor);
    }

    @Override
    public Author findByUserCredentialId(String request) {
        return authorRepository.findByUserCredential_Id(request).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad Request"));
    }

    @Override
    public Author findById(String request) {
        return authorRepository.findById(request).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad Request"));
    }

}
