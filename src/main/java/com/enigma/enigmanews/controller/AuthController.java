package com.enigma.enigmanews.controller;

import com.enigma.enigmanews.model.request.AuthRequest;
import com.enigma.enigmanews.model.response.LoginResponse;
import com.enigma.enigmanews.model.response.RegisterResponse;
import com.enigma.enigmanews.model.response.WebResponse;
import com.enigma.enigmanews.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping(path = "/register")
    public ResponseEntity<?> register(@RequestBody AuthRequest request){
        RegisterResponse register = authService.register(request);
        WebResponse<RegisterResponse> response = WebResponse.<RegisterResponse>builder()
                .status(HttpStatus.CREATED.getReasonPhrase())
                .message("successfuly create new author")
                .data(register)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @PostMapping (path = "/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request){
        LoginResponse login = authService.login(request);
        WebResponse<LoginResponse> response = WebResponse.<LoginResponse>builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .message("successfuly login")
                .data(login)
                .build();
        return ResponseEntity.ok().body(response);
    }

}
