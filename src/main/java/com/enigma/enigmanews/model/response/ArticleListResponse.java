package com.enigma.enigmanews.model.response;

import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticleListResponse {

    private String id;
    private String title;
    private String author;
    private List<String> tags;

}
