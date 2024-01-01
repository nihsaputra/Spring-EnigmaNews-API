package com.enigma.enigmanews.model.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchArticleRequest {

    private String author;
    private String Tags;

}
