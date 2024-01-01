package com.enigma.enigmanews.model.request;

import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TagRequest {
    private List<String> tags;
}
