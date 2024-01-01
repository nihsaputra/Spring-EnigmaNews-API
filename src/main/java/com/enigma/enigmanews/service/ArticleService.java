package com.enigma.enigmanews.service;

import com.enigma.enigmanews.entity.Article;
import com.enigma.enigmanews.model.request.ArticleRequest;
import com.enigma.enigmanews.model.request.SearchArticleRequest;
import com.enigma.enigmanews.model.response.ArticleListResponse;
import com.enigma.enigmanews.model.response.ArticleResponse;

import java.util.List;

public interface ArticleService {
    ArticleResponse create(ArticleRequest request);

    ArticleResponse update(ArticleRequest request, String articleId);

    String delete(String articleId);

}
