package com.enigma.enigmanews.service;

import com.enigma.enigmanews.entity.UserCredential;
import com.enigma.enigmanews.model.request.AuthRequest;
import com.enigma.enigmanews.model.response.LoginResponse;
import com.enigma.enigmanews.model.response.RegisterResponse;

public interface AuthService {

    void initSuperAdmin();

    RegisterResponse registerAdmin(AuthRequest request);

    RegisterResponse register(AuthRequest request);

    LoginResponse login(AuthRequest request);

}
