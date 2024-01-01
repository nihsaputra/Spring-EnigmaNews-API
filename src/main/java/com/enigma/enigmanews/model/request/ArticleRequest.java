package com.enigma.enigmanews.model.request;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticleRequest {

    private String title;
    private String content;
    private String author;
    private List<String> tags;

}
