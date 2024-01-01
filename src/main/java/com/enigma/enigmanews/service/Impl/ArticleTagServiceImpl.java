package com.enigma.enigmanews.service.Impl;


import com.enigma.enigmanews.entity.ArticleTag;
import com.enigma.enigmanews.entity.Tag;
import com.enigma.enigmanews.model.request.ArticleTagRequest;
import com.enigma.enigmanews.model.request.SearchArticleRequest;
import com.enigma.enigmanews.model.response.ArticleListResponse;
import com.enigma.enigmanews.model.response.ArticleResponse;
import com.enigma.enigmanews.repository.ArticleTagRepository;

import com.enigma.enigmanews.service.ArticleTagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleTagServiceImpl implements ArticleTagService {

    private final ArticleTagRepository articleTagRepository;
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void create(ArticleTagRequest request) {
        for (Tag tag : request.getTags()) {
            ArticleTag buildArticleTag = ArticleTag.builder()
                    .article(request.getArticle())
                    .tag(tag)
                    .build();
            articleTagRepository.save(buildArticleTag);
        }
    }

    @Override
    public void update(ArticleTagRequest request, String articleId) {
        delete(articleId);

        for (Tag tag : request.getTags()) {
            ArticleTag buildArticleTag = ArticleTag.builder()
                    .article(request.getArticle())
                    .tag(tag)
                    .build();
            articleTagRepository.save(buildArticleTag);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(String articleId) {
        List<ArticleTag> articleTag = getByArticleId(articleId);
        for (ArticleTag tag : articleTag) {
            articleTagRepository.deleteById(tag.getId());
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<ArticleTag> getByArticleId(String articleId) {
        Optional<List<ArticleTag>> optionalArticleTag = articleTagRepository.findByArticle_Id(articleId);

        if (!optionalArticleTag.isPresent())throw new ResponseStatusException(HttpStatus.NOT_FOUND,"not found");

        return optionalArticleTag.get();
    }

    @Override
    public List<?> getAll(SearchArticleRequest request) {
        List<ArticleTag> findAll = articleTagRepository.findAll();

        List<ArticleListResponse> listResponses = new ArrayList<>();
        for (ArticleTag articleTag : findAll) {
            ArticleListResponse buildListResponse = ArticleListResponse.builder()
                    .id(articleTag.getArticle().getId())
                    .title(articleTag.getArticle().getTitle())
                    .author(articleTag.getArticle().getAuthor().getName())
                    .tags(articleTag.getTag().getName().lines().toList())
                    .build();
             listResponses.add(buildListResponse);
        }
        return listResponses;

    }

}
