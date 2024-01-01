package com.enigma.enigmanews.service.Impl;

import com.enigma.enigmanews.entity.Article;
import com.enigma.enigmanews.entity.Author;
import com.enigma.enigmanews.entity.Tag;
import com.enigma.enigmanews.entity.UserCredential;
import com.enigma.enigmanews.model.request.ArticleRequest;
import com.enigma.enigmanews.model.request.ArticleTagRequest;
import com.enigma.enigmanews.model.request.SearchArticleRequest;
import com.enigma.enigmanews.model.request.TagRequest;
import com.enigma.enigmanews.model.response.ArticleListResponse;
import com.enigma.enigmanews.model.response.ArticleResponse;
import com.enigma.enigmanews.model.response.AuthResponse;
import com.enigma.enigmanews.repository.ArticleRepository;
import com.enigma.enigmanews.service.ArticleService;
import com.enigma.enigmanews.service.ArticleTagService;
import com.enigma.enigmanews.service.AuthorService;
import com.enigma.enigmanews.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final AuthorService authorService;
    private final TagService tagService;
    private final ArticleTagService articleTagService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ArticleResponse create(ArticleRequest request) {

        Author author = authorService.findById(request.getAuthor());

        // TRANSACTIONAL ARTICLE
        Article buildArticle = Article.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .author(author)
                .build();
        Article article = articleRepository.saveAndFlush(buildArticle);

       // TRANSACTIONAL TAG
        TagRequest tagRequest = TagRequest.builder()
                .tags(request.getTags())
                .build();
        List<Tag> tags = tagService.createOrUpdate(tagRequest);

        // TRANSACTIONAL ARTICLE_TAG
        ArticleTagRequest buildArticleTag = ArticleTagRequest.builder()
                .article(article)
                .tags(tags)
                .build();
        articleTagService.create(buildArticleTag);

        return ArticleResponse.builder()
                .id(article.getId())
                .title(article.getTitle())
                .content(article.getContent())
                .author(article.getAuthor().getName())
                .tags(request.getTags())
                .build();
    }

    @Override
    public ArticleResponse update(ArticleRequest request, String articleId) {
        Optional<Article> optionalArticle = articleRepository.findById(articleId);
        if (!optionalArticle.isPresent()) throw new  ResponseStatusException(HttpStatus.NOT_FOUND,"not found");


        // TRANSACTIONAL ARTICLE
        Article buildArticle = Article.builder()
                .id(optionalArticle.get().getId())
                .author(optionalArticle.get().getAuthor())
                .content(request.getContent())
                .title(request.getTitle())
                .build();
        Article article = articleRepository.saveAndFlush(buildArticle);


        // TRANSACTIONAL TAG
        TagRequest tagRequest = TagRequest.builder()
                .tags(request.getTags())
                .build();
        List<Tag> tags = tagService.createOrUpdate(tagRequest);

        // TRANSACTIONAL ARTICLE_TAG
        ArticleTagRequest buildArticleTag = ArticleTagRequest.builder()
                .article(article)
                .tags(tags)
                .build();
        articleTagService.update(buildArticleTag,articleId);


        return ArticleResponse.builder()
                .id(article.getId())
                .title(article.getTitle())
                .content(article.getContent())
                .author(article.getAuthor().getName())
                .tags(request.getTags())
                .build();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String delete(String articleId) {
        articleTagService.delete(articleId);
        articleRepository.deleteById(articleId);
        return "OK";
    }

    @Override
    public List<ArticleListResponse> getListArticle(SearchArticleRequest request) {
        List<Article> findAll = articleRepository.findAll();

        List<ArticleListResponse> articleListResponses= new ArrayList<>();
        for (Article article : findAll) {
            ArticleListResponse buildArticle = ArticleListResponse.builder()
                    .id(article.getId())
                    .author(article.getAuthor().getName())
                    .title(article.getTitle())
                    .build();
            articleListResponses.add(buildArticle);

        }
        return articleListResponses;
    }

}
