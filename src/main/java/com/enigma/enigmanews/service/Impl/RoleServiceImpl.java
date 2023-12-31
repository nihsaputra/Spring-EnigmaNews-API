package com.enigma.enigmanews.service.Impl;

import com.enigma.enigmanews.constan.ERole;
import com.enigma.enigmanews.entity.Role;
import com.enigma.enigmanews.repository.RoleRepository;
import com.enigma.enigmanews.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Role getOrSave(ERole role) {
        Optional<Role> optionalRole = roleRepository.findByRole(role);
        if (optionalRole.isPresent()) {
            return optionalRole.get();
        }
        Role buildRole = Role.builder()
                .role(role)
                .build();
        return roleRepository.save(buildRole);

    }
}
