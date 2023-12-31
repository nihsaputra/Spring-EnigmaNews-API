package com.enigma.enigmanews.model.response;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class LoginResponse {

    private String token;
    private List<String> roles;

}
