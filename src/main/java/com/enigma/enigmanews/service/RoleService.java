package com.enigma.enigmanews.service;

import com.enigma.enigmanews.constan.ERole;
import com.enigma.enigmanews.entity.Role;

public interface RoleService {
    Role getOrSave(ERole role);
}
