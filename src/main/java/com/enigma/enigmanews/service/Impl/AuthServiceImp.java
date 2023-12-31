package com.enigma.enigmanews.service.Impl;

import com.enigma.enigmanews.constan.ERole;
import com.enigma.enigmanews.entity.Role;
import com.enigma.enigmanews.entity.UserCredential;
import com.enigma.enigmanews.model.request.AuthRequest;
import com.enigma.enigmanews.model.response.LoginResponse;
import com.enigma.enigmanews.model.response.RegisterResponse;
import com.enigma.enigmanews.repository.UserCredentialRepository;
import com.enigma.enigmanews.security.JwtUtil;
import com.enigma.enigmanews.service.AuthService;
import com.enigma.enigmanews.service.AuthorService;
import com.enigma.enigmanews.service.RoleService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class AuthServiceImp implements AuthService {

    private final UserCredentialRepository userCredentialRepository;
    private final AuthorService authorService;
    private final RoleService roleService;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;


    @PostConstruct
    public void initSuperAdmin() {

        String email = "superadmin@gmail.com";
        String password = "password";
        Optional<UserCredential> findEmail = userCredentialRepository.findByEmail(email);

        if (findEmail.isPresent()) {
            return;
        }

        Role roleSuperAdmin = roleService.getOrSave(ERole.ROLE_SUPER_ADMIN);
        Role roleAdmin = roleService.getOrSave(ERole.ROLE_ADMIN);
        Role roleAuthor = roleService.getOrSave(ERole.ROLE_AUTHOR);
        String hashPassword = passwordEncoder.encode(password);

        UserCredential buildUserCredential = UserCredential.builder()
                .email(email)
                .roles(List.of(roleSuperAdmin,roleAdmin,roleAuthor))
                .password(hashPassword)
                .build();

        UserCredential userCredential = userCredentialRepository.saveAndFlush(buildUserCredential);
        authorService.create(userCredential);

    }

    @Override
    public RegisterResponse registerAdmin(AuthRequest request) {

        Optional<UserCredential> findEmail = userCredentialRepository.findByEmail(request.getEmail());
        if (findEmail.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "email already exists");
        }

        Role roleAuthor = roleService.getOrSave(ERole.ROLE_AUTHOR);
        String hashPassword = passwordEncoder.encode(request.getPassword());

        UserCredential buildUserCredential = UserCredential.builder()
                .email(request.getEmail())
                .roles(List.of(roleAuthor))
                .password(hashPassword)
                .build();

        UserCredential userCredential = userCredentialRepository.saveAndFlush(buildUserCredential);
        authorService.create(userCredential);

        List<String> listRole = userCredential.getRoles().stream().map(role -> role.getRole().name()).toList();

        return RegisterResponse.builder()
                .email(userCredential.getEmail())
                .roles(listRole)
                .build();

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public RegisterResponse register(AuthRequest request) {

        Optional<UserCredential> findEmail = userCredentialRepository.findByEmail(request.getEmail());
        if (findEmail.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "email already exists");
        }

        Role roleAuthor = roleService.getOrSave(ERole.ROLE_AUTHOR);
        String hashPassword = passwordEncoder.encode(request.getPassword());

        UserCredential buildUserCredential = UserCredential.builder()
                .email(request.getEmail())
                .roles(List.of(roleAuthor))
                .password(hashPassword)
                .build();

        UserCredential userCredential = userCredentialRepository.saveAndFlush(buildUserCredential);
        authorService.create(userCredential);

        List<String> listRole = userCredential.getRoles().stream().map(role -> role.getRole().name()).toList();

        return RegisterResponse.builder()
                .email(userCredential.getEmail())
                .roles(listRole)
                .build();

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public LoginResponse login(AuthRequest request) {

        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        UserCredential userCredential =(UserCredential) authenticate.getPrincipal();

        String token = jwtUtil.generateToken(userCredential);

        SecurityContextHolder.getContext().setAuthentication(authenticate);

        List<String> roles = userCredential.getRoles().stream().map(role -> role.getRole().name()).toList();

        return LoginResponse.builder()
                .token(token)
                .roles(roles)
                .build();

    }

}
