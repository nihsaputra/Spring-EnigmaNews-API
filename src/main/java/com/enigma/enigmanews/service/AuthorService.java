package com.enigma.enigmanews.service;

import com.enigma.enigmanews.entity.Author;
import com.enigma.enigmanews.entity.UserCredential;
import com.enigma.enigmanews.model.response.AuthResponse;

public interface AuthorService {

    void create(UserCredential request);
    Author findByUserCredentialId(String request);
    Author findById(String request);

}
