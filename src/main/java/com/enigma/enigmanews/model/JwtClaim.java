package com.enigma.enigmanews.model;

import com.enigma.enigmanews.entity.Role;
import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JwtClaim {

    private String userId;
    private List<String> roles;

}
