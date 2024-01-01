package com.enigma.enigmanews.model.request;

import com.enigma.enigmanews.entity.Article;
import com.enigma.enigmanews.entity.Tag;
import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticleTagRequest {

    private Article article;
    private List<Tag> tags;

}
