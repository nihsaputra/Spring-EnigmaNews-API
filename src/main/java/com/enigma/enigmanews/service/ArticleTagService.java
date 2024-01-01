package com.enigma.enigmanews.service;


import com.enigma.enigmanews.entity.ArticleTag;
import com.enigma.enigmanews.model.request.ArticleTagRequest;
import com.enigma.enigmanews.model.request.SearchArticleRequest;
import com.enigma.enigmanews.model.response.ArticleListResponse;

import java.util.List;

public interface ArticleTagService {
    void create(ArticleTagRequest request);

    void update(ArticleTagRequest request, String articleId);

    void delete(String articleId);

    List<ArticleTag> getByArticleId(String articleId);

    List<?> getAll(SearchArticleRequest request);
}
