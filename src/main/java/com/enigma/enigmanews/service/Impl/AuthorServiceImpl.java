package com.enigma.enigmanews.service.Impl;

import com.enigma.enigmanews.entity.Author;
import com.enigma.enigmanews.entity.UserCredential;
import com.enigma.enigmanews.repository.AuthorRepository;
import com.enigma.enigmanews.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
