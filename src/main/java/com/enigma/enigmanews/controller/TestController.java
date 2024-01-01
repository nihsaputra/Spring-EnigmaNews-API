package com.enigma.enigmanews.controller;

import com.enigma.enigmanews.entity.Article;
import com.enigma.enigmanews.entity.ArticleTag;
import com.enigma.enigmanews.entity.Author;
import com.enigma.enigmanews.entity.Tag;
import com.enigma.enigmanews.model.request.ArticleRequest;
import com.enigma.enigmanews.model.request.SearchArticleRequest;
import com.enigma.enigmanews.model.request.TagRequest;
import com.enigma.enigmanews.model.response.ArticleResponse;
import com.enigma.enigmanews.model.response.WebResponse;
import com.enigma.enigmanews.service.ArticleService;
import com.enigma.enigmanews.service.ArticleTagService;
import com.enigma.enigmanews.service.AuthorService;
import com.enigma.enigmanews.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/test")
public class TestController {

    private final AuthorService authorService;
    private final TagService tagService;
    private final ArticleService articleService;

}
