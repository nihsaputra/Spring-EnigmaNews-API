package com.enigma.enigmanews.model.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class AuthRequest {

    private String email;
    private String password;

}
