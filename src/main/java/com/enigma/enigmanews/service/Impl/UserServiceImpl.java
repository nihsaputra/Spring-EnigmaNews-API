package com.enigma.enigmanews.service.Impl;

import com.enigma.enigmanews.entity.UserCredential;
import com.enigma.enigmanews.repository.UserCredentialRepository;
import com.enigma.enigmanews.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserCredentialRepository userCredentialRepository;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userCredentialRepository.findByEmail(email).orElseThrow(() -> new ResponseStatusException(HttpStatus.FORBIDDEN,"Access Denied"));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public UserCredential loadUserById(String userId) {
        return userCredentialRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Access Denied"));
    }
}
