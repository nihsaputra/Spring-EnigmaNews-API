package com.enigma.enigmanews.model.response;

import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterResponse {

    private String email;
    private List<String> roles;

}
