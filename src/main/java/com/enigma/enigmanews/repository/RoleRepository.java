package com.enigma.enigmanews.repository;

import com.enigma.enigmanews.constan.ERole;
import com.enigma.enigmanews.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

    Optional<Role> findByRole(ERole role);

}
